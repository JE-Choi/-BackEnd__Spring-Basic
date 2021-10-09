package com.jechoi.core.discount;

import com.jechoi.core.member.Member;

public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     * Note: member가 아니라 getGrade값만 보내도 됨.
     */
    int discount(Member member, int price);
}
