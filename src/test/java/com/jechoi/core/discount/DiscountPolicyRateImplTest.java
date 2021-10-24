package com.jechoi.core.discount;

import com.jechoi.core.discount.impl.DiscountPolicyRateImpl;
import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DiscountPolicyRateImplTest {

    DiscountPolicyRateImpl discountPolicy = new DiscountPolicyRateImpl();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.") // Junit5부터 지원
    void vip_o(){
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        Assertions.assertEquals(1000, discount);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다. ") // Junit5부터 지원
    void vip_x(){
        //given
        Member member = new Member(2L, "member", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        Assertions.assertNotEquals(1000, discount);
        Assertions.assertEquals(0, discount);
    }
}