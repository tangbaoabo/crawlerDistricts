package com.tangbaobao.crawlercity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tangxuejun
 * @version 2019-03-25 22:03
 */
@Getter
@AllArgsConstructor
public enum CrawlerDistrictCode {
    //3000-4000调用外部API出错
    NO_RESULT(3000, "结果无数据"),
    REQUEST_ERROR(3001, "调用API出错"),
    JSON_FORMAT_ERROR(3002, "JSON解析错误"),
    QUERY_CONDITION_ERROR(3003, "请检查你的请求参数"),


    ;

    private int code;
    private String msg;

}
