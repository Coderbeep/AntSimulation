package com.example.demo1;

/**
 * Represents a Drone ant in the simulation.
 * Extends the BlueAnt class with additional behaviors.
 */
public class Drone extends BlueAnt {
    /**
     * Constructs a new Drone instance.
     * @param antHome The anthill where the ant lives.
     * @param name The name of the ant.
     * @param strength The number of larvae the ant can carry.
     * @param health The health of the ant.
     */
    public Drone(Anthill antHome, String name, int strength, int health) {
        super(antHome, name, strength, health);

        setOnTransitionFinished((currentNode, targetSpot, ant) -> {
        });
    }

    /**
     * Overrides the run method from the BlueAnt class.
     * Implements the main behavior of the Drone ant.
     */
    @Override
    public void run() {
        while(isAlive()) {
            move();
        }
        System.out.println("Drone " + getName() + " died.");
    }

    /**
     * Overrides the description method from the BlueAnt class.
     * Provides a description of the Drone ant.
     * @return A string describing the Drone ant.
     */
    @Override
    public String description() {
        return "Cannon fodder.";
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}
