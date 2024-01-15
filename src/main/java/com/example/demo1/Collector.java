package com.example.demo1;

/**
 * Represents a Collector ant in the simulation.
 * Extends the RedAnt class with additional behaviors.
 */
public class Collector extends RedAnt {
    /**
     * Constructs a new Collector instance.
     * @param antHome The anthill where the ant lives.
     * @param name The name of the ant.
     * @param strength The number of larvae the ant can carry.
     * @param health The health of the ant.
     */
    public Collector(Anthill antHome, String name, int strength, int health) {
        super(antHome, name, strength, health);

        setOnTransitionFinished((currentNode, targetSpot, ant) -> {
            if (currentNode instanceof Anthill) {
                ant.dropLarvae();
            }

            if (ant.getStrength() > ant.getLarvaeCarried()) {
                ant.collectLarvae();
            }
        });
    }

    /**
     * Overrides the run method from the RedAnt class.
     * Implements the main behavior of the Collector ant.
     */
    @Override
    public void run() {
        while(isAlive()) {
            if (getLarvaeCarried() == getStrength()) {
                returnToAnthill();
            }
            move();
        }
    }

    /**
     * Overrides the description method from the RedAnt class.
     * Provides a description of the Collector ant.
     * @return A string describing the Collector ant.
     */
    @Override
    public String description() {
        return "Looks for larvae and, after taking as many as it has strength, goes back to anthill.";
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}
