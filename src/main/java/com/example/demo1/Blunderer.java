package com.example.demo1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a Blunderer ant in the simulation.
 * Extends the RedAnt class with additional behaviors.
 */
public class Blunderer extends RedAnt {
    /**
     * Constructs a new Blunderer instance.
     * @param antHome The anthill where the ant lives.
     * @param name The name of the ant.
     * @param strength The number of larvae the ant can carry.
     * @param health The health of the ant.
     */
    public Blunderer(Anthill antHome, String name, int strength, int health) {
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
     * Implements the main behavior of the Blunderer ant.
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
     * Overrides the returnToAnthill method from the RedAnt class.
     * Implements the behavior of the Blunderer ant returning to the anthill.
     */
    @Override
    public void returnToAnthill() {
        ArrayList<Spot> pathToAnthill = findPathToAnthill();
        for (Spot spot : pathToAnthill) {
            moveTo(spot);
            int chance = new Random().nextInt(100);
            if (chance < 15) {
                dropLarvae();
            }
        }
        setComingBack(false);
    }

    /**
     * Overrides the description method from the RedAnt class.
     * Provides a description of the Blunderer ant.
     * @return A string describing the Blunderer ant.
     */
    @Override
    public String description() {
        return "Looks for larvae and, after taking as many as it has strength, goes back to anthill. Has a chance to drop larvae on the way back.";
    }

    @Override
    public boolean canAttack() {
        return false;
    }
}