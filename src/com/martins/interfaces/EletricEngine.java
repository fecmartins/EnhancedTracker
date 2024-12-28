package com.martins.interfaces;

public class EletricEngine implements Engine {

    public void start() {
        System.out.println("Eletric Engine started");
    }

    public void stop() {
        System.out.println("Eletric Engine stopped");
    }

    @Override
    public void acc() {
        System.out.println("Accelerating the eletric engine car");
    }
}
