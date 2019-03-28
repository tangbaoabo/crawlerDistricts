package com.tangbaobao.crawlercity.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author tangxuejun
 * 返回实体结构
 * @version 2019-03-26 12:44
 */
@Getter
@ToString
@Setter
public class OriginResult {

    /**
     * 返回状态码：
     * 1表示成功，0表示失败
     */
    private String status;
    /**
     * 状态信息：
     * when status = 1 ,info = "ok"
     * else show the error of response
     */
    private String info;
    /**
     * 返回状态说明:
     * 10000代表正确，详情参阅info状态表
     */
    private String infoCode;
    private String count;
    //区域列表集合
    private List<District> districts;
}
