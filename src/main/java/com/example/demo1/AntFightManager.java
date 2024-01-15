package com.example.demo1;

import java.util.ArrayList;

/**
 * Manager class responsible for handling fights between ants.
 */
public class AntFightManager {
    /**
     * Handles fights between ants at a given spot.
     * @param spot The spot where the fight is taking place.
     */
    public static void handleFights(Spot spot) {
        synchronized (spot.getAnts()) {
            ArrayList<Ant> ants = spot.getAnts();

            if (ants.size() > 1) {
                for (int i = 0; i < ants.size(); i++) {
                    Ant ant = ants.get(i);
                    if (!ant.canAttack()) {
                        continue;
                    }
                    for (int j = i + 1; j < ants.size(); j++) {
                        Ant opponent = ants.get(j);

                        if (ant.getColor() != opponent.getColor()) {
                            System.out.println("Ant that attacks: " + ant.getName());
                            opponent.setHealth(opponent.getHealth() - 1);
                            System.out.println("Ant " + opponent.getName() + " lost 1 health point. ----- FIGHT: " + ant.getName() + " vs " + opponent.getName() + " at spot " + spot.getId());
                            if (opponent.getHealth() == 0) {
                                opponent.die();
                            }
                            spot.getAnts().remove(ant); // After giving the hit, the ant goes away
                            ant.setComingBack(true);
                            return; // End the fight for this ant
                        }
                    }
                }
            }
        }
    }
}