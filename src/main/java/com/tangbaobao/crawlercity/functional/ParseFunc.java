package com.tangbaobao.crawlercity.functional;

import com.tangbaobao.crawlercity.domain.District;
import com.tangbaobao.crawlercity.domain.QueryCondition;

/**
 * @author tangxuejun
 * @version 2019-03-25 22:34
 */
@FunctionalInterface
public interface ParseFunc {
    /**
     * 解析查询的函数接口
     *
     * @param queryUrl 输入参数
     * @return {@link District}返回的值
     */
    District parseDistrict(QueryCondition queryUrl);
}
