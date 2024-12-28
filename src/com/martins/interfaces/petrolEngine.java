package com.martins.interfaces;

public class petrolEngine implements Engine {
    @Override
    public void start() {
        System.out.println("Petrol Engine started");
    }
    @Override
    public void stop() {
        System.out.println("Petrol Engine stopped");
    }

    @Override
    public void acc() {
        System.out.println("Accelerating the petrol engine car");
    }

}
