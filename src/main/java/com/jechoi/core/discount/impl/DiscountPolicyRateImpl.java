package com.jechoi.core.discount.impl;

import com.jechoi.core.discount.DiscountPolicy;
import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;

public class DiscountPolicyRateImpl implements DiscountPolicy {
    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
