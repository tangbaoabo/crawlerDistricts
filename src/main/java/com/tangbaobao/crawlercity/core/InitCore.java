package com.tangbaobao.crawlercity.core;

import com.tangbaobao.crawlercity.service.fetcher.Fetcher;
import com.tangbaobao.crawlercity.service.parser.DistrictJsonValidate;
import com.tangbaobao.crawlercity.service.parser.DistrictParser;
import com.tangbaobao.crawlercity.service.parser.QueryConversion;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tangxuejun
 * 为核心调度器组装成员
 * @version 2019-03-26 13:42
 */
@Service
@Getter
@Setter
public class InitCore {

    private final Fetcher fetcher;
    private final DistrictParser districtParser;
    private final DistrictJsonValidate districtJsonValidate;
    private final QueryConversion queryConversion;

    @Autowired
    public InitCore(Fetcher fetcher, DistrictParser districtParser, DistrictJsonValidate districtJsonValidate, QueryConversion queryConversion) {
        this.fetcher = fetcher;
        this.districtParser = districtParser;
        this.districtJsonValidate = districtJsonValidate;
        this.queryConversion = queryConversion;
    }
}
