package com.example.demo1;

public interface AntStateListener {
    /**
     * Called when the health of an ant changes.
     * @param ant The ant whose health changed.
     */
    void onHealthChanged(Ant ant);
    /**
     * Called when the number of larvae carried by an ant changes.
     * @param ant The ant whose number of larvae changed.
     */
    void onLarvaeCarriedChanged(Ant ant);
}