package com.example.demo1;

/**
 * Represents a Soldier ant in the simulation.
 * Extends the RedAnt class with additional behaviors.
 */
public class Soldier extends RedAnt {
    /**
     * Constructs a new Soldier instance.
     * @param antHome The anthill where the ant lives.
     * @param name The name of the ant.
     * @param strength The number of larvae the ant can carry.
     * @param health The health of the ant.
     */
    public Soldier(Anthill antHome, String name, int strength, int health) {
        super(antHome, name, strength, health);

        setOnTransitionFinished((currentNode, targetSpot, ant) -> {
            if (currentNode instanceof FightableSpot) {
                ((FightableSpot) currentNode).handleFight();
            }
        });
    }

    /**
     * Overrides the run method from the RedAnt class.
     * Implements the main behavior of the Soldier ant.
     */
    @Override
    public void run() {
        while(isAlive()) {
            if (isComingBack()) {
                returnToAnthill();
            }
            move();
        }
    }

    /**
     * Overrides the description method from the RedAnt class.
     * Provides a description of the Soldier ant.
     * @return A string describing the Soldier ant.
     */
    @Override
    public String description() {
        return "Attacks blue ants and goes back to anthill after giving a hit.";
    }

    @Override
    public boolean canAttack() {
        return true;
    }
}
