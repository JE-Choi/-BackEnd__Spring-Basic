package com.jechoi.core.order;

import com.jechoi.core.AppConfig;
import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;
import com.jechoi.core.member.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        this.orderService = ac.getBean("orderService", OrderService.class);
        this.memberService = ac.getBean("memberService", MemberService.class);
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order itemA = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertEquals(9000, itemA.calculatePrice());
        Assertions.assertEquals(10000, itemA.getItemPrice());
        Assertions.assertEquals(1000, itemA.getDiscountPrice());

    }
}
