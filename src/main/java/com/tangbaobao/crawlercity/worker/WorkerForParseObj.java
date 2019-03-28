package com.tangbaobao.crawlercity.worker;

import com.tangbaobao.crawlercity.core.InitCore;
import com.tangbaobao.crawlercity.domain.District;
import com.tangbaobao.crawlercity.domain.OriginResult;
import com.tangbaobao.crawlercity.domain.QueryCondition;
import com.tangbaobao.crawlercity.enums.DistrictLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author tangxuejun
 * @version 2019-03-27 20:13
 */
@Slf4j
@Component
public class WorkerForParseObj implements Worker<District> {
    private final InitCore initCore;

    @Autowired
    public WorkerForParseObj(InitCore initCore) {
        this.initCore = initCore;
    }

    /**
     * 调用链路工作者：
     * 1：将用户生成的Query对象解析为服务端需要的URL
     * </p>
     * 2：将格式良好的URL，通过HTTPCLient请求，获取Response
     * </p>
     * 3：校验JSON格式，并校验返服务端返回的相关信息是否和预期的一致
     * </p>
     * 4：解析为目标对象
     *
     * @param queryCondition
     * @return
     * @throws Exception
     */
    public District worker(QueryCondition queryCondition) throws Exception {
        String url = initCore.getQueryConversion().queryToStr(queryCondition);

        String originJson = initCore.getFetcher().fetchStrFromUrl(url);

        OriginResult originResult = initCore.getDistrictJsonValidate().validateResult(originJson);

        District district = initCore.getDistrictParser().parseToDistrict(originResult).get(0);

        //默认打印Country，province，City这三个级别的数据
        if (district.getLevel() == DistrictLevel.COUNTRY
                || district.getLevel() == DistrictLevel.PROVINCE
                || district.getLevel() == DistrictLevel.CITY) {
            log.warn("开始爬取={}的数据", district.getName());

        }
        return district;
    }
}
