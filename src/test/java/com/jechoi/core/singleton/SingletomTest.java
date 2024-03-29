package com.jechoi.core.singleton;

import com.jechoi.core.AppConfig;
import com.jechoi.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletomTest {
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 조회: 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회: 호출할 때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것을 확인
        Assertions.assertEquals(false, memberService1 == memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        // 참조값이 같은 것을 확인
        Assertions.assertEquals(true, SingletonService.getInstance() == SingletonService.getInstance());
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = configApplicationContext.getBean("memberService", MemberService.class);
        MemberService memberService2 = configApplicationContext.getBean("memberService", MemberService.class);

        // 참조값이 같은것을 확인
        Assertions.assertEquals(true, memberService1 == memberService2);
    }
}
