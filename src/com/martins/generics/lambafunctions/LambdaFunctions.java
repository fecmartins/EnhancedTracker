package com.martins.generics.lambafunctions;

public class LambdaFunctions {

    public static void main(String[] args) {
        // Lambda function to add two numbers
        MathOperation addition = (int a, int b) -> a + b;

        // Lambda function to multiply two numbers
        MathOperation multiplication = (int a, int b) -> a * b;

        // Lambda function to divide two numbers
        MathOperation division = (int a, int b) -> a / b;

        // Lambda function to find the square of a number
        MathOperation square = (int a, int b) -> a * a;

        System.out.println("10 + 5 = " + operate(10, 5, addition));
        System.out.println("10 * 5 = " + operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + operate(10, 5, division));
        System.out.println("Square of 10 = " + operate(10, 0, square));
    }

    private static int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    interface MathOperation {
        int operation(int a, int b);
    }


}
