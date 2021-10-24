package com.jechoi.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {


    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체에는 상태가 존재하면 안됨")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService bean1 = ac.getBean(StatefulService.class);
        StatefulService bean2 = ac.getBean(StatefulService.class);
        // ThreadA: A사용자 10000원 주문
        bean1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        bean2.order("userB", 20000);

        // ThreadA: 사용자A 주문금액 조회
        int price = bean1.getPrice();
        assertEquals(20000, price);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}