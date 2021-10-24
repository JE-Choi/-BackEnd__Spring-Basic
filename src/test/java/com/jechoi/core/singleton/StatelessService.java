package com.jechoi.core.singleton;

public class StatelessService {
    public int order(final String name, final int price) {
        System.out.println(String.format("name = %s, price = %d", name, price));
        return price; // 여기가 문제

    }
}
