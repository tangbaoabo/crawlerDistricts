package com.tangbaobao.crawlercity.scheduler;

import com.tangbaobao.crawlercity.domain.QueryCondition;

import java.util.Deque;

/**
 * @author tangxuejun
 * 调度器
 * @version 2019-03-27 21:42
 */
public interface Scheduler {

    Deque<QueryCondition> startQueue();

    void initScheduler(Deque<QueryCondition> conditions);

    public void submitTask(QueryCondition queryCondition);
}
