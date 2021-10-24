package com.jechoi.core.singleton;

public class StatefulService {
    private int price; // 상태를 유지하는 필드

    public void order(final String name, final int price) {
        System.out.println(String.format("name = %s, price = %d", name, price));
        this.price = price; // 여기가 문제

    }

    public int getPrice() {
        return this.price;
    }
}
