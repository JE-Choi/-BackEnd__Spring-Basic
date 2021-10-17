package com.jechoi.core;

import com.jechoi.core.discount.DiscountPolicy;
import com.jechoi.core.discount.FixDiscountPolicy;
import com.jechoi.core.discount.RateDiscountPolicy;
import com.jechoi.core.member.MemberRepository;
import com.jechoi.core.member.MemberService;
import com.jechoi.core.member.MemberServiceImpl;
import com.jechoi.core.member.MemoryMemberRepository;
import com.jechoi.core.order.OrderService;
import com.jechoi.core.order.OrderServiceImpl;

/**
 * 실제 동작에 필요한 구현객체 주입
 * : 이로써 서비스 로직은 '실행에만 집중할 수 있다. '
 * 역할이 드러나야 함.
 */
public class AppConfig {
    public MemberService memberService() {
        // 생성자 주입: 밖에서 생성해서 주입
        return new MemberServiceImpl(memberRepository());
    }
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
    }

}
