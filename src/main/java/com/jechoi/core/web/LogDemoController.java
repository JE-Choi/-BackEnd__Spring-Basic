package com.jechoi.core.web;

import com.jechoi.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final Provider<MyLogger> myLoggerProvider;

    @RequestMapping(value = "log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        // 아래 로직은 인터셉터에서 공통관리하면 좋음.
        final MyLogger myLogger = this.myLoggerProvider.get();
        myLogger.setRequestURL(requestURL);

        myLogger.log("LogDemoController test");
        logDemoService.logic("testId");
        return "OK";
    }
}
