package com.tangbaobao.crawlercity.service.fetcher;

/**
 * @author tangxuejun
 * @version 2019-03-26 13:24
 */
public interface Fetcher {
    String fetchStrFromUrl(String url) throws Exception;
}
