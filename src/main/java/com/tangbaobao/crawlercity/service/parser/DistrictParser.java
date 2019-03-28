package com.tangbaobao.crawlercity.service.parser;

import com.tangbaobao.crawlercity.domain.District;
import com.tangbaobao.crawlercity.domain.OriginResult;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author tangxuejun
 * 封装为可存储的
 * @version 2019-03-25 22:46
 */
@Service
public class DistrictParser {
    public List<District> parseToDistrict(OriginResult originResult) {
        if (originResult != null) {
            return originResult.getDistricts();
        }
        return Collections.emptyList();
    }
}
