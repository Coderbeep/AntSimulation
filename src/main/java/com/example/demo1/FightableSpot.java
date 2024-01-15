package com.example.demo1;

/**
 * Defines the contract for objects where fights can take place.
 */
public interface FightableSpot {
    /**
     * Checks if fighting is allowed at this spot.
     * @return true if fighting is allowed, false otherwise.
     */
    boolean isFightAllowed();

    /**
     * Handles the fight event at this spot.
     */
    void handleFight();
}
