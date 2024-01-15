package com.example.demo1;

import javafx.scene.paint.Color;

/**
 * Represents a red ant in the simulation.
 * Extends the generic Ant class with a color attribute set to CRIMSON.
 */
public abstract class RedAnt extends Ant {
    /**
     * Constructs a new RedAnt instance.
     * @param antHome The anthill where the ant lives.
     * @param name The name of the ant.
     * @param strength The number of larvae the ant can carry.
     * @param health The health of the ant.
     */
    public RedAnt(Anthill antHome, String name, int strength, int health) {
        super(antHome, Color.CRIMSON, name, strength, health);
    }
}
