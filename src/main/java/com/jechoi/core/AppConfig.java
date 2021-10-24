package com.jechoi.core;

import com.jechoi.core.discount.DiscountPolicy;
import com.jechoi.core.discount.impl.DiscountPolicyRateImpl;
import com.jechoi.core.member.MemberRepository;
import com.jechoi.core.member.MemberService;
import com.jechoi.core.member.MemberServiceImpl;
import com.jechoi.core.member.MemoryMemberRepository;
import com.jechoi.core.order.OrderService;
import com.jechoi.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 실제 동작에 필요한 구현객체 주입
 * : 이로써 서비스 로직은 '실행에만 집중할 수 있다. '
 * 역할이 드러나야 함.
 */
@Configuration
public class AppConfig {
    @Bean // 스프링 컨테이너에 등록됨.
    public MemberService memberService() {
        // 생성자 주입: 밖에서 생성해서 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new DiscountPolicyRateImpl();
    }

}
