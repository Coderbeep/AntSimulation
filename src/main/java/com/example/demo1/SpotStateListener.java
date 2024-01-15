package com.example.demo1;

/**
 * This interface is used to listen to changes in the state of a Spot.
 */
public interface SpotStateListener {
    /**
     * This method is called when the amount of larvae in a Spot changes.
     * @param spot the Spot whose state has changed
     */
    void onSpotLarvaeChange(Spot spot);
}
