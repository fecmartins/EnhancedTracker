package com.martins.interfaces;

public class NiceCar {

    private Engine engine;
    private Media player = new CDPlayer();

    /**
     * Constructor for the NiceCar class.
     * Initializes the engine with an instance of EletricEngine.
     */
    public NiceCar() {
        this.engine = new petrolEngine();
    }

    /**
     * Constructor for the NiceCar class.
     * Initializes the engine with the provided Engine instance.
     *
     * @param engine the Engine instance to be used by this NiceCar
     */
    public NiceCar(Engine engine) {
        this.engine = engine;
    }

    public Engine getEngine() {
        return engine;
    }

    public void start() {
        engine.start();
    }

    public void stop() {
        engine.stop();
    }

    public void startMedia() {
        player.start();
    }

    public void stopMedia() {
        player.stop();
    }

    public void upgradeEngine() {
        this.engine = new EletricEngine();
    }

}
