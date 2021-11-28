package com.jechoi.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println(String.format("[%s] [%s] %s", uuid, requestURL, message));
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        System.out.println(String.format("[%s] request scope bean create: %s", uuid, this));
    }

    @PreDestroy
    public void close() {
        System.out.println(String.format("[%s] request scope bean close: %s", uuid, this));
    }
}
