package com.jechoi.core.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    /**
     * 계산된 가격
     */
    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
