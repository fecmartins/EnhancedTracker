package com.martins.interfaces;

public class Main {

    public static void main(String[] args) {
//        Car car = new Car();
//        car.start();
//        car.stop();
//        car.acc();
//        car.brake();

        NiceCar niceCar = new NiceCar();

        niceCar.start();
        niceCar.stop();
        niceCar.startMedia();
        niceCar.stopMedia();
        niceCar.upgradeEngine();
        niceCar.start();

    }
}
