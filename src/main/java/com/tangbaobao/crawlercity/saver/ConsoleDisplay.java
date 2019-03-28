package com.tangbaobao.crawlercity.saver;

import com.tangbaobao.crawlercity.domain.District;
import org.springframework.stereotype.Component;

/**
 * @author tangxuejun
 * 控制台打印所有结果
 * @version 2019-03-27 21:05
 */

@Component
public class ConsoleDisplay implements Saver {
    private int count = 0;

    @Override
    public void save(District district) {
        System.out.println("#：" + (++count) + district);
    }
}
