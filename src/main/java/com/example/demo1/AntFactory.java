package com.example.demo1;


/**
 * Enum representing different types of ants.
 */
enum AntType {
    Soldier, Collector, Blunderer, Worker, Drone
}

/**
 * Factory class for creating ants.
 */
public class AntFactory {

    private Anthill redAntHill;
    private Anthill blueAntHill;

    /**
     * Constructor for AntFactory.
     * @param redAntHill The red anthill.
     * @param blueAntHill The blue anthill.
     */
    public AntFactory(Anthill redAntHill, Anthill blueAntHill) {
        this.redAntHill = redAntHill;
        this.blueAntHill = blueAntHill;
    }

    /**
     * Creates an ant of a specified type.
     * @param antType The type of ant to create from the AntType enum.
     * @return The created ant.
     */
    public Ant createAnt(AntType antType) {
        switch (antType) {
            case Soldier: {
                String name = "Soldier" + (int)(Math.random() * 100000);
                int strength = (int)(Math.random() * 3 + 1);
                int health = (int)(Math.random() * 3 + 1);
                return new Soldier(redAntHill, name, strength, health);}
            case Collector: {
                String name = "Collector" + (int)(Math.random() * 100000);
                int strength = (int)(Math.random() * 4 + 1);
                int health = (int)(Math.random() * 2 + 1);
                return new Collector(redAntHill, name, strength, health);}
            case Blunderer: {
                String name = "Blunderer" + (int)(Math.random() * 100000);
                int strength = (int)(Math.random() * 4 + 1);
                int health = (int)(Math.random() * 2 + 1);
                return new Blunderer(redAntHill, name, strength, health);}
            case Worker: {
                String name = "Worker" + (int)(Math.random() * 100000);
                int strength = (int)(Math.random() * 5 + 1);
                int health = (int)(Math.random() * 3 + 1);
                return new Worker(blueAntHill, name, strength, health);}
            case Drone: {
                String name = "Drone" + (int)(Math.random() * 100000);
                int strength = (int)(Math.random() * 2);
                int health = (int)(Math.random() * 1 + 1);
                return new Drone(blueAntHill, name, strength, health);}
            default:
                return null;
        }
    }
}