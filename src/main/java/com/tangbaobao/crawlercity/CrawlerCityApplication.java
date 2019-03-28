package com.tangbaobao.crawlercity;

import com.tangbaobao.crawlercity.boot.AppBoot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerCityApplication {

    private static AppBoot boot = null;

    @Autowired
    public CrawlerCityApplication(AppBoot boot) {
        CrawlerCityApplication.boot = boot;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CrawlerCityApplication.class, args);
        boot.runCore();

    }

}
