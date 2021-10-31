package com.jechoi.core.order;

import com.jechoi.core.discount.impl.DiscountPolicyFixedImpl;
import com.jechoi.core.member.Grade;
import com.jechoi.core.member.Member;
import com.jechoi.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
        memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new DiscountPolicyFixedImpl());
        Order itemA = orderService.createOrder(1L, "itemA", 10000);

        assertThat(itemA.getDiscountPrice()).isEqualTo(1000);
    }

}