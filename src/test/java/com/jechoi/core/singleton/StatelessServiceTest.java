package com.jechoi.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatelessServiceTest {


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체에서 상태없애기(지역변수 사용)")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatelessService bean1 = ac.getBean(StatelessService.class);
        StatelessService bean2 = ac.getBean(StatelessService.class);
        // ThreadA: A사용자 10000원 주문
        int userAprice = bean1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBprice = bean2.order("userB", 20000);

        // ThreadA: 사용자A 주문금액 조회
        assertEquals(10000, userAprice);
        assertEquals(20000, userBprice);
    }

    static class TestConfig {
        @Bean
        public StatelessService statefulService() {
            return new StatelessService();
        }
    }

}