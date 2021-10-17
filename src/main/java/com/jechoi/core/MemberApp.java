package com.jechoi.core;

import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;
import com.jechoi.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        // 스프링은 ApplicationContext으로 시작된다. => 이게 스프링 컨네이너임. (@Bean이런거 모두 관리함.)
        // Creating shared instance of singleton bean 'appConfig'로 등록됨.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find member = " + findMember.getName());
        System.out.println(member.getName() == findMember.getName());
    }
}
