package com.example.demo1;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * The class for the ToolbarManager object. It manages the toolbar for the simulation.
 * The toolbar is responsible for creating new ants and adding them to the simulation.
 */
public class ToolbarManager {
    private ToolBar toolBar;
    private HBox toolbarContainer;
    private Anthill redAnthill;
    private Anthill blueAnthill;

    /**
     * Constructs a ToolbarManager object with the specified attributes.
     * @param redAnthill the red anthill
     * @param blueAnthill the blue anthill
     */
    public ToolbarManager(Anthill redAnthill, Anthill blueAnthill) {
        this.redAnthill = redAnthill;
        this.blueAnthill = blueAnthill;
        initializeToolbar();
    }

    /**
     * Initializes the toolbar.
     */
    private void initializeToolbar() {
        toolBar = new ToolBar();
        AntFactory antFactory = new AntFactory(redAnthill, blueAnthill);

        Button button1 = new Button("Soldier");
        button1.setOnAction(e -> {
            Ant ant = antFactory.createAnt(AntType.Soldier);
            Thread thread = new Thread(ant);
            thread.start();
        });

        Button button2 = new Button("Collector");
        button2.setOnAction(e -> {
            Ant ant = antFactory.createAnt(AntType.Collector);
            Thread thread = new Thread(ant);
            thread.start();
        });

        Button button3 = new Button("Blunderer");
        button3.setOnAction(e -> {
            Ant ant = antFactory.createAnt(AntType.Blunderer);
            Thread thread = new Thread(ant);
            thread.start();
        });

        Button button4 = new Button("Worker");
        button4.setOnAction(e -> {
            Ant ant = antFactory.createAnt(AntType.Worker);
            Thread thread = new Thread(ant);
            thread.start();
        });

        Button button5 = new Button("Drone");
        button5.setOnAction(e -> {
            Ant ant = antFactory.createAnt(AntType.Drone);
            Thread thread = new Thread(ant);
            thread.start();
        });

        toolBar.getItems().addAll(button1, button2, button3, button4, button5);

        toolbarContainer = new HBox(toolBar);
    }

    /**
     * Gets the toolbar.
     * @return the toolbar
     */
    public HBox getToolbarContainer() {
        return toolbarContainer;
    }

    /**
     * Binds the width of the toolbar to the width of the pane.
     * @param pane the pane
     */
    public void bindWidthToPaneWidth(Pane pane) {
        toolBar.prefWidthProperty().bind(pane.widthProperty());
    }

    /**
     * Gets the height of the toolbar.
     * @return the height of the toolbar
     */
    public double getHeight() {
        return toolBar.getHeight();
    }
}