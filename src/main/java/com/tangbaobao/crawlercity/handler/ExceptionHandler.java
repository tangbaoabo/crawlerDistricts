package com.tangbaobao.crawlercity.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * @author tangxuejun
 * 异常处理
 * @version 2019-03-28 10:43
 */
@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handleBindException(Exception e) {
        return e.getMessage();
    }
}
