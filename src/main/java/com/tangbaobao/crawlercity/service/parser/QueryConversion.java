package com.tangbaobao.crawlercity.service.parser;

import com.tangbaobao.crawlercity.domain.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author tangxuejun
 * Query和请求URL互相转换器
 * @version 2019-03-26 09:39
 */
@Service
@Slf4j
public class QueryConversion {
    @Value("${amap.key}")
    private String key;

    @Value("${amap.url_prefix}")
    private String url_prefix;


    /**
     * https://restapi.amap.com/v3/config/district
     * ?keywords=210000
     * &subDistrict=1
     * &key=d920349477ba502b313c354d564335b4
     * &filter=210000
     * <p>
     * <p>
     * 用n个if判断真的好烦。。。还没想到更好的办法
     */
    //TODO replace if to another style
    private int count = 0;

    public String queryToStr(QueryCondition queryCondition) {
        StringBuilder sb = new StringBuilder(url_prefix).append(key);
        if (StringUtils.isNotEmpty(queryCondition.getKeywords())) {
            sb.append("&keywords=").append(queryCondition.getKeywords());
        }
        if (StringUtils.isNotEmpty(queryCondition.getSubDistrict())) {
            sb.append("&subDistrict=").append(queryCondition.getSubDistrict());
        }
        if (queryCondition.getPage() != 0) {
            sb.append("&page=").append(queryCondition.getPage());
        }
        if (queryCondition.getOffset() != 0) {
            sb.append("&offset=").append(queryCondition.getOffset());
        }
        if (StringUtils.isNotEmpty(queryCondition.getExtensions())) {
            sb.append("&extensions=").append(queryCondition.getExtensions());
        }
        if (queryCondition.getFilter() != 0) {
            sb.append("&filter=").append(queryCondition.getFilter());
        }
        if (StringUtils.isNotEmpty(queryCondition.getCallback())) {
            sb.append("&callback=").append(queryCondition.getCallback());
        }
        if (StringUtils.isNotEmpty(queryCondition.getOutput())) {
            sb.append("&output=").append(queryCondition.getOutput());
        }
        String url = sb.toString().toLowerCase();
        log.info("请求服务器={}次,generated URL={}", ++count, url);
        //将URL全部转换为小写

        return url;


    }

    public static QueryCondition componentQueryObj(QueryCondition src, String adCode, String sub) {
        return QueryCondition.builder()
                .key(src.getKey())
                .keywords(adCode)
                .filter(Integer.parseInt(adCode))
                //换adCode
                .subDistrict(sub)
                .build();

    }
}
