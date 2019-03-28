package com.tangbaobao.crawlercity.service.parser;

import com.alibaba.fastjson.JSON;
import com.tangbaobao.crawlercity.domain.OriginResult;
import com.tangbaobao.crawlercity.exception.CrawlerDistrictException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import static com.tangbaobao.crawlercity.enums.CrawlerDistrictCode.*;

/**
 * @author tangxuejun
 * @version 2019-03-26 10:33
 */
@Service
public class DistrictJsonValidate {

    //返回状态为1为成功
    private static final String GET_SUCCESS = "1";
    //详细信息成功状态
    private static final String INFO_CODE_SUCCESS = "10000";


    public OriginResult validateResult(String originJson) throws CrawlerDistrictException {
        if (!JSON.isValid(originJson)) {
            throw new CrawlerDistrictException(JSON_FORMAT_ERROR);
        }
        //提取信息和判断返回
        OriginResult originResult = JSON.parseObject(originJson, OriginResult.class);
        if (!originResult.getStatus().equals(GET_SUCCESS)) {
            throw new CrawlerDistrictException(REQUEST_ERROR);
        }
        if (CollectionUtils.isEmpty(originResult.getDistricts())) {
            throw new CrawlerDistrictException(QUERY_CONDITION_ERROR);
        }
        //判断得到的信息
        if (!originResult.getInfoCode().equals(INFO_CODE_SUCCESS)) {
            throw new CrawlerDistrictException(originResult.getInfo(), REQUEST_ERROR);
        }
        return originResult;
    }
}
