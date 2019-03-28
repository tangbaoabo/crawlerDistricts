package com.tangbaobao.crawlercity.saver;

import com.tangbaobao.crawlercity.dao.DistrictRepository;
import com.tangbaobao.crawlercity.domain.District;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * @author tangxuejun
 * @version 2019-03-27 16:35
 */
@Service
@Slf4j
public class DistrictSaver implements Saver {
    private final DistrictRepository repository;
    private int count = 0;

    @Autowired
    public DistrictSaver(DistrictRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(District district) {
        log.info("现在已经爬取={},district={}", ++count, district);
        if (!Pattern.matches("[\\d]+", district.getCityCode())) {
            district.setCityCode("");
        }
        repository.save(district);
    }


}

