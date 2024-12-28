package com.martins.interfaces;

public interface Engine {

    static final double PRICE = 70000; // This is a constant, so it should be declared as final

    void start();
    void stop();
    void acc();
}
