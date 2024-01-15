package com.example.demo1;

import javafx.scene.paint.Color;

/**
 * Represents a blue ant in the simulation.
 * Extends the generic Ant class with a color attribute set to ROYALBLUE.
 */
public abstract class BlueAnt extends Ant {
    /**
     * Constructs a new BlueAnt instance.
     * @param antHome The anthill where the ant lives.
     * @param name The name of the ant.
     * @param strength The number of larvae the ant can carry.
     * @param health The health of the ant.
     */
    public BlueAnt(Anthill antHome, String name, int strength, int health) {
        super(antHome, Color.ROYALBLUE, name, strength, health);
    }
}