package com.jechoi.core.discount.impl;

import com.jechoi.core.annotation.MainDiscountPolicy;
import com.jechoi.core.discount.DiscountPolicy;
import com.jechoi.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class DiscountPolicyFixedImpl implements DiscountPolicy {
    private int discountFixAmount = 1000; // 고정할인

    @Override
    public int discount(Member member, int price) {
        switch (member.getGrade()){
            case VIP:
                return discountFixAmount;
            default:
                return 0;
        }
    }
}
