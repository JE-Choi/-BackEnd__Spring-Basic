package com.jechoi.core.order;

import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;
import com.jechoi.core.member.MemberService;
import com.jechoi.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    final MemberService memberService = new MemberServiceImpl();
    final OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order itemA = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertEquals(9000, itemA.calculatePrice());
        Assertions.assertEquals(10000, itemA.getItemPrice());
        Assertions.assertEquals(1000, itemA.getDiscountPrice());

    }
}
