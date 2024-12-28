package com.martins.generics.lambafunctions;

import java.lang.management.OperatingSystemMXBean;
import java.util.ArrayList;

public class CustomLambda {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

//        list.forEach(item -> {
//            System.out.println(item * 2);
//        });

        operation sumnumber = (a, b) -> a + b;
        operation divide = (a, b) -> a / b;
        operation multiply = (a, b) -> a * b;
        operation minus = (a, b) -> a - b;

        CustomLambda customLambda = new CustomLambda();
        System.out.println(customLambda.operate(10, 5, sumnumber));
        System.out.println(customLambda.operate(10, 5, divide));
        System.out.println(customLambda.operate(10, 5, multiply));
        System.out.println(customLambda.operate(10, 5, minus));

    }

    private int operate(int a, int b, operation op) {
        return op.operation(a, b);
    }
}

interface operation {
    int operation(int a, int b);
}