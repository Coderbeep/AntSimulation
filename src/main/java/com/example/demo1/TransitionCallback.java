package com.example.demo1;

/**
 * This interface is used to execute the behaviour of an Ant when it reaches a Spot.
 */
public interface TransitionCallback {
    /**
     * This method is called when an Ant reaches a Spot.
     * @param currentSpot the Spot on which the Ant is currently located
     * @param targetSpot the Spot on which the Ant is moving
     * @param ant the Ant that is moving
     */
    void execute(Spot currentSpot, Spot targetSpot, Ant ant);
}
