package com.example.demo1;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Simulation {
    /**
     * This method is called when the simulation is started.
     * It creates the Pane object for the simulation display, the World object for the simulation, and the ToolbarManager object for the simulation.
     * @param primaryStage the Stage object for the simulation
     */
    public static void start(Stage primaryStage) {
        primaryStage.setTitle("Ants World app");

        // Create the Pane for simulation
        Pane pane = initializePane();
        primaryStage.setScene(new Scene(pane, 1200, 700));
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        // initialize the InfoStage for information window
        InfoStage infoStage = new InfoStage();

        // Create the World object
        World worldGraph = initializeWorld();

        // Create rest of the UI elements
        ToolbarManager toolbarManager = initializeToolbarManager();

        primaryStage.show();
        primaryStage.setHeight(765);
        primaryStage.setResizable(false);
        infoStage.show();
    }

    /**
     * This method is called when the simulation is started.
     * It creates the Pane object for the simulation display.
        * @return the Pane object for the simulation display.
     */
    public static Pane initializePane() {
        Pane pane = new Pane();
        Background background = new Background(new BackgroundFill(Color.CORNSILK, null, null));
        pane.setBackground(background);
        PaneManager.getInstance().setPane(pane);
        return pane;
    }

    /**
     * This method is called when the simulation is started.
     * It creates the World object for the simulation.
     * @return the World object for the simulation.
     */
    public static World initializeWorld() {
        World worldGraph = new World();
        worldGraph.drawOnPane();
        PaneManager.getInstance().setWorldGraph(worldGraph);
        return worldGraph;
    }

    /**
     * This method is called when the simulation is started.
     * It creates the ToolbarManager object for the simulation.
     * @return the ToolbarManager object for the simulation.
     */
    public static ToolbarManager initializeToolbarManager() {
        Anthill[] anthills = PaneManager.getInstance().getWorldGraph().getAnthills();
        ToolbarManager toolbarManager = new ToolbarManager(anthills[0], anthills[1]);
        HBox toolbarContainer = toolbarManager.getToolbarContainer();
        toolbarManager.bindWidthToPaneWidth(PaneManager.getInstance().getPane());
        toolbarContainer.setTranslateY(PaneManager.getInstance().getPane().getHeight() - toolbarManager.getHeight());
        PaneManager.getInstance().getPane().getChildren().add(toolbarContainer);
        return toolbarManager;
    }
}
