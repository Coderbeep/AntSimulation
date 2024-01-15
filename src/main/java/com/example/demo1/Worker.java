package com.example.demo1;

/**
 * Represents a Worker ant in the simulation.
 * Extends the BlueAnt class with additional behaviors.
 */
public class Worker extends BlueAnt {
    public Worker(Anthill antHome, String name, int strength, int health) {
        super(antHome, name, strength, health);

        // Set the specific callback for SoldierAnt
        setOnTransitionFinished((currentNode, targetSpot, ant) -> {
            if (currentNode instanceof FightableSpot) {
                ((FightableSpot) currentNode).handleFight();
            }

            if (currentNode instanceof Anthill) {
                ant.dropLarvae();
            }

            if (ant.getStrength() > ant.getLarvaeCarried()) {
                ant.collectLarvae();
            }
        });
    }

    /**
     * Overrides the run method from the BlueAnt class.
     * Implements the main behavior of the Worker ant.
     */
    @Override
    public void run() {
        while(isAlive()) {
            if (isComingBack() || getLarvaeCarried() > 0) {
                returnToAnthill();
            }
            move();
        }
    }

    /**
     * Overrides the description method from the BlueAnt class.
     * Provides a description of the Worker ant.
     * @return A string describing the Worker ant.
     */
    @Override
    public String description() {
        return "Looks for larvae and can attack red ants. After getting larvae or attacking, it has to go back to the anthill.";
    }

    @Override
    public boolean canAttack() {
        return true;
    }
}
