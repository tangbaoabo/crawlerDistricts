package com.tangbaobao.crawlercity.worker;

import com.tangbaobao.crawlercity.domain.QueryCondition;

/**
 * @author tangxuejun
 * @version 2019-03-27 20:28
 */
public interface Worker<T> {
    T worker(QueryCondition queryCondition) throws Exception;
}
