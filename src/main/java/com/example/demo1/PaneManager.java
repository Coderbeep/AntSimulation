package com.example.demo1;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * This class is used to manage the Pane on which the simulation is drawn.
 * It is implemented as a singleton.
 */
public class PaneManager {
    private static PaneManager instance;
    private Pane pane;
    private InfoStage infoStage;
    private World worldGraph;

    private PaneManager() {}

    /**
     * Returns the instance of the PaneManager.
     * @return the instance of the PaneManager
     */
    public static PaneManager getInstance() {
        if (instance == null) {
            instance = new PaneManager();
        }
        return instance;
    }

    /**
     * Sets the Pane on which the simulation is drawn.
     * @param pane the Pane on which the simulation is drawn
     */
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    /**
     * Returns the Pane on which the simulation is drawn.
     * @return the Pane on which the simulation is drawn
     */
    public Pane getPane() {
        return pane;
    }

    /**
     * Sets the InfoStage object.
     * @param infoStage the InfoStage object
     */
    public void setInfoStage(InfoStage infoStage) { this.infoStage = infoStage; }

    /**
     * Returns the InfoStage object.
     * @return the InfoStage object
     */
    public InfoStage getInfoStage() { return infoStage; }

    /**
     * Sets the World object.
     * @param worldGraph the World object
     */
    public void setWorldGraph(World worldGraph) { this.worldGraph = worldGraph; }

    /**
     * Returns the World object.
     * @return the World object
     */
    public World getWorldGraph() { return worldGraph; }
}
