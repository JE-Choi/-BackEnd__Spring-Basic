package com.jechoi.core.autowired;

import com.jechoi.core.AutoAppConfig;
import com.jechoi.core.discount.DiscountPolicy;
import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AllBeanTest {
    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int fixedDiscountPrice = discountService.discount(member, 10000, "discountPolicyFixedImpl");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(fixedDiscountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "discountPolicyRateImpl");
        assertThat(rateDiscountPrice).isEqualTo(2000);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }
}
