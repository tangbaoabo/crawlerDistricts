package com.tangbaobao.crawlercity.core;

import com.tangbaobao.crawlercity.domain.District;
import com.tangbaobao.crawlercity.domain.QueryCondition;
import com.tangbaobao.crawlercity.enums.DistrictLevel;
import com.tangbaobao.crawlercity.saver.Saver;
import com.tangbaobao.crawlercity.scheduler.Scheduler;
import com.tangbaobao.crawlercity.service.parser.QueryConversion;
import com.tangbaobao.crawlercity.worker.Worker;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangxuejun
 * <p>
 * 基于广度遍历算法的实现
 * @version 2019-03-26 13:37
 */
@Slf4j
public class CrawlerDistrictCore {
    private final Worker<District> worker;

    private final Saver saver;

    private Scheduler scheduler;

    /**
     * 全局计数器
     */
    private AtomicInteger countItem = new AtomicInteger();


    public CrawlerDistrictCore(Saver saver, Worker<District> worker, Scheduler scheduler) {
        this.saver = saver;
        this.worker = worker;
        this.scheduler = scheduler;
    }


    /**
     * core engine
     *
     * @param seeds
     * @throws Exception
     */
    public void run(QueryCondition... seeds) throws Exception {
        log.debug("程序开始运行");
        LocalDateTime startTime = LocalDateTime.now();
        //初始化调度器
        Deque<QueryCondition> queryConditionQueue = scheduler.startQueue();

        scheduler.initScheduler(queryConditionQueue);

        Arrays.stream(seeds).forEach(scheduler::submitTask);

        int count = 0;
        if (queryConditionQueue.size() > 0) {
            QueryCondition queryCondition = queryConditionQueue.poll();
            District result = worker.worker(queryCondition);
            //将结果存储
            count += recursiveDist(result);

            //获取更多请求
            List<District> districts = result.getDistricts();

            districts.forEach(x -> producer(queryCondition, x));
        }
        log.info("程序运行结束,运行时长为={}秒，总共获取到数据={}条", Duration.between(startTime, LocalDateTime.now()).getSeconds(), count);
    }

    /**
     * 生产出新的请求条件,并提交到队列
     * <p>
     * 地区一共有5个级别，由于限制调用次数，
     * 所以不能递归调用每一个节点
     * 所以在City级别一下获取2级(也就是获取到街道)
     * 在City级别之上获取下一级
     *
     * @param queryCondition
     * @param district
     */
    public void producer(QueryCondition queryCondition, District district) {
        if (district.getLevel() == DistrictLevel.CITY) {
            scheduler.submitTask(QueryConversion.componentQueryObj(queryCondition, district.getAdCode(), "2"));
        } else if (district.getLevel() == DistrictLevel.COUNTRY
                || district.getLevel() == DistrictLevel.PROVINCE) {
            scheduler.submitTask(QueryConversion.componentQueryObj(queryCondition, district.getAdCode(), queryCondition.getExtensions()));

        }
    }


    /**
     * 递归解析拉取结果
     *
     * @param district
     */
    private int recursiveDist(District district) {
        //第一层级以下的
        district.getDistricts()
                .stream()
                .filter(Objects::nonNull)
                .map(x -> componentForParentCode(x, district.getAdCode()))
                //调用存储结果
                .peek(saver::save)
                .forEach(this::recursiveDist);
        return countItem.addAndGet(district.getDistricts().size());
    }

    /**
     * 组装父级城市code
     *
     * @param newDist      新的节点
     * @param parentAcCode 父节点的地区Code
     * @return 组装后带有父节点的地区
     */
    private District componentForParentCode(District newDist, String parentAcCode) {
        newDist.setParentAdCode(parentAcCode);
        return newDist;
    }

}
