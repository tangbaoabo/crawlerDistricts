package com.tangbaobao.crawlercity.exception;

import com.tangbaobao.crawlercity.enums.CrawlerDistrictCode;

/**
 * @author tangxuejun
 * @version 2019-03-25 22:01
 */
public class CrawlerDistrictException extends Exception {
    private CrawlerDistrictCode crawlerDistrictCode;

    public CrawlerDistrictException(CrawlerDistrictCode crawlerDistrictCode) {
        this.crawlerDistrictCode = crawlerDistrictCode;
    }

    public CrawlerDistrictException() {
        super();
    }

    public CrawlerDistrictException(String message, CrawlerDistrictCode crawlerDistrictCode) {
        super(message, new CrawlerDistrictException(crawlerDistrictCode));
    }

    public CrawlerDistrictException(String message) {
        super(message);
    }

    public CrawlerDistrictException(String message, Throwable cause) {
        super(message, cause);
    }
}
