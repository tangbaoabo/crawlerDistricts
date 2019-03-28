package com.tangbaobao.crawlercity.scheduler;

import com.tangbaobao.crawlercity.domain.QueryCondition;
import org.springframework.stereotype.Component;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author tangxuejun
 * 简单队列调度器
 * @version 2019-03-27 20:36
 */
@Component
public class QueueScheduler implements Scheduler {
    //请求队列:注意现在是单线程的代码，如果多个线程运行结果会有问题
    private Deque<QueryCondition> queryConditionList;

    @Override
    public Deque<QueryCondition> startQueue() {
        return new LinkedList<>();
    }

    @Override
    public void submitTask(QueryCondition queryCondition) {
        queryConditionList.add(queryCondition);
    }

    @Override
    public void initScheduler(Deque<QueryCondition> collection) {
        this.queryConditionList = collection;
    }




}
