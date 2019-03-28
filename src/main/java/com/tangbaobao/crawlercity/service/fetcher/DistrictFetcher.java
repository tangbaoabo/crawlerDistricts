package com.tangbaobao.crawlercity.service.fetcher;

import com.tangbaobao.crawlercity.exception.CrawlerDistrictException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.tangbaobao.crawlercity.enums.CrawlerDistrictCode.NO_RESULT;

/**
 * @author tangxuejun
 * 基于httpClient的Fetcher
 * @version 2019-03-25 21:53
 */
@Service
public class DistrictFetcher implements Fetcher {
    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    /**
     * 获取地区字符串
     *
     * @return
     */
    @Override
    public String fetchStrFromUrl(String url) throws IOException, CrawlerDistrictException {
        HttpGet httpget = new HttpGet(url);
        String result;
        try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpget)) {
            //如果返回正常则直接返回结果
            if (response != null && response.getStatusLine().getStatusCode()
                    == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                return result;
            }
        }
        throw new CrawlerDistrictException(NO_RESULT);
    }
}
