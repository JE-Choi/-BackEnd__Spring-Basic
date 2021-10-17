package com.jechoi.core.beanfind;

import com.jechoi.core.AppConfig;
import com.jechoi.core.discount.DiscountPolicy;
import com.jechoi.core.member.MemberRepository;
import com.jechoi.core.member.MemberServiceImpl;
import com.jechoi.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SamaBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 중복 오류가 발생한다.")
    void findBeanByTypeDuplicate() {
        assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(MemoryMemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름으로 지정하면 된다.")
    void findBeanByName() {
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        assertEquals(true, memberRepository1 instanceof MemoryMemberRepository);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        Map<String, MemoryMemberRepository> beansOfType = ac.getBeansOfType(MemoryMemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + ", value=" + beansOfType.get(key));
        }
        System.out.println("beansOfType = " + beansOfType);
        assertEquals(2, beansOfType.size());
    }

    /**
     * NoUniqueBeanDefinitionException 발생
     */
    @Configuration
    static class SamaBeanConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }
}

