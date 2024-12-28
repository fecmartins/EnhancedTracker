package com.martins.interfaces;

public class Car implements Engine, Media, Brake {

    @Override
    public void start() {
        System.out.println("Starting the car");
    }

    @Override
    public void stop() {
        System.out.println("Stopping the car");
    }

    @Override
    public void acc() {
        System.out.println("Accelerating the car");
    }

    @Override
    public void brake() {
        System.out.println("Braking the car");
    }

}
