package com.tangbaobao.crawlercity.boot;

import com.tangbaobao.crawlercity.core.CrawlerDistrictCore;
import com.tangbaobao.crawlercity.domain.District;
import com.tangbaobao.crawlercity.domain.QueryCondition;
import com.tangbaobao.crawlercity.saver.ConsoleDisplay;
import com.tangbaobao.crawlercity.saver.Saver;
import com.tangbaobao.crawlercity.scheduler.QueueScheduler;
import com.tangbaobao.crawlercity.scheduler.Scheduler;
import com.tangbaobao.crawlercity.worker.Worker;
import com.tangbaobao.crawlercity.worker.WorkerForParseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author tangxuejun
 * <p>
 * 爬虫入口启动函数
 * 基于可配置的实现
 * <p>
 * 优点：每个核心的组件都有接口实现，要实现不同的用处只需注入不同的实现类即可
 * @version 2019-03-27 20:18
 */
@Controller
public class AppBoot {

    //根节点
    private final static String CHINA_ROOT_CODE = "100000";
    //拉去几级城市
    private final static String SUB_DISTRICT = "1";

    private final Saver districtSaver;
    private final Worker<District> worker;
    private final Scheduler scheduler;

    @Autowired
    public AppBoot(ConsoleDisplay districtSaver, WorkerForParseObj worker, QueueScheduler scheduler) {
        this.districtSaver = districtSaver;
        this.worker = worker;
        this.scheduler = scheduler;
    }

    public void runCore() throws Exception {
        //配置核心运行的配件
        CrawlerDistrictCore core
                = new CrawlerDistrictCore(
                districtSaver,//配置mysql存储的
                worker,//配置基础worker
                scheduler//配置基于LinkedQueue的调度器
        );
        //组装入口查询条件
        QueryCondition seed = QueryCondition.builder()
                //中国的地区代码
                .keywords(CHINA_ROOT_CODE)
                //拉取子地区一级
                .subDistrict(SUB_DISTRICT)
                .build();
        //拉去地区入口
        core.run(seed);
    }
}
