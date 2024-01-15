package com.example.demo1;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main class for the AntsWorldGraph application.
 */

public class AntApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the application.
     * @param primaryStage The primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
        Simulation.start(primaryStage);
    }
}