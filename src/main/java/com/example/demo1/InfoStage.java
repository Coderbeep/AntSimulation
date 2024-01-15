package com.example.demo1;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.SplitPane;

/**
 * This class represents a stage that displays information about ants and their activities.
 * It implements the AntStateListener and SpotStateListener interfaces to listen for changes in ant and spot states.
 */
public class InfoStage extends Stage implements AntStateListener, SpotStateListener {
    private final Pane infoPane;
    private final Pane anthillPane;
    private Ant selectedAnt;
    private int redLarvaeCount = 0;
    private int blueLarvaeCount = 0;

    /**
     * Sets the currently selected ant and updates the info pane.
     * @param ant the ant to select
     */
    public void setAnt(Ant ant) {
        this.selectedAnt = ant;
        updateInfoPane();
    }

    /**
     * Gets the currently selected ant.
     * @return the currently selected ant
     */
    public Ant getAnt() {
        return selectedAnt;
    }

    /**
     * Constructs a new InfoStage object.
     * Initializes the stage, sets its properties, creates the info and anthill panes, and adds them to a split pane.
     */
    public InfoStage() {
        super();

        this.setTitle("Ant Information");
        this.setResizable(false);
        this.setWidth(600);
        this.setHeight(400);

        infoPane = createInfoPane();
        anthillPane = createAnthillPane();

        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(anthillPane, infoPane);
        splitPane.setDividerPositions(0.25);

        PaneManager paneManager = PaneManager.getInstance();
        paneManager.setInfoStage(this);

        Scene scene = new Scene(splitPane);
        this.setScene(scene);
    }

    /**
     * Initializes the Pane that displays information about the anthill larvae amounts.
     * @return the initialized Pane
     */
    private Pane createAnthillPane() {
        // Clear existing content from anthillPane

        // Create and add labels to anthillPane
        HBox larvaeCountBox = new HBox(10);
        larvaeCountBox.setAlignment(Pos.CENTER);

        // Blue Larvae Count Box
        VBox blueLarvaeBox = new VBox(5);
        blueLarvaeBox.setAlignment(Pos.CENTER);

        Label blueLabel = new Label("Blue Larvae");
        blueLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label blueCountLabel = new Label(String.valueOf(blueLarvaeCount));
        blueCountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        blueLarvaeBox.getChildren().addAll(blueLabel, blueCountLabel);

        // Red Larvae Count Box
        VBox redLarvaeBox = new VBox(5);
        redLarvaeBox.setAlignment(Pos.CENTER);

        Label redLabel = new Label("Red Larvae");
        redLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label redCountLabel = new Label(String.valueOf(redLarvaeCount));
        redCountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        redLarvaeBox.getChildren().addAll(redLabel, redCountLabel);

        larvaeCountBox.getChildren().addAll(blueLarvaeBox, redLarvaeBox);

        // Create a VBox for the anthill statistics
        VBox anthillContainer = new VBox(10);
        anthillContainer.setAlignment(Pos.CENTER);
        anthillContainer.setStyle("-fx-background-color: #F5F5F5;");

        anthillContainer.getChildren().addAll(larvaeCountBox);

        return anthillContainer;
    }

    /**
     * Initializes the Pane that displays information about the selected ant.
     * @return the initialized Pane
     */
    private Pane createInfoPane() {
        VBox container = new VBox(10);
        container.setAlignment(Pos.CENTER);
        container.setStyle("-fx-background-color: #F5F5F5;");

        Label titleLabel = new Label("Ant Information");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        container.getChildren().addAll(titleLabel);

        // Create an info below the "Ant Information" that says "Click on the ant to see its information"
        Label infoLabel = new Label("Click on the ant to see its information");
        infoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        container.getChildren().addAll(infoLabel);

        // Return the generic Pane
        return container;
    }

    /**
     * Updates the info pane when an ant loses health.
     * @param ant the ant that lost health
     */
    @Override
    public void onHealthChanged(Ant ant) {
        if (ant == selectedAnt) {
            updateInfoPane();
        }
    }

    /**
     * Updates the info pane when the larvae amount of an anthill changes.
     * @param spot the anthill whose larvae amount changed
     */
    @Override
    public void onSpotLarvaeChange(Spot spot) {
        updateAnthillPane((Anthill) spot);
    }

    /**
     * Updates the info pane when an ant picks up larvae.
     * @param ant the ant that picked up larvae
     */
    @Override
    public void onLarvaeCarriedChanged(Ant ant) {
        if (ant == selectedAnt) {
            updateInfoPane();
        }
    }

    /**
     * Updates the info pane raised by an ant's event.
     */
    public void updateInfoPane() {
        // Clear existing content from infoPane
        infoPane.getChildren().clear();

        // Create and add labels to infoPane
        Label nameLabel = new Label(selectedAnt.getName());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label typeLabel = new Label("Type: " + selectedAnt.getClass().getSimpleName());
        Label strengthLabel = new Label("Strength: " + selectedAnt.getStrength());
        Label healthLabel = new Label("Health: " + selectedAnt.getHealth());
        Label larvaeLabel = new Label("Larvae: " + selectedAnt.getLarvaeCarried());

        Label descLabel = new Label(selectedAnt.description());
        descLabel.setWrapText(true);
        descLabel.setPadding(new Insets(10, 10, 10, 10));

        // Add labels to infoPane
        infoPane.getChildren().addAll(nameLabel, typeLabel,
                strengthLabel,
                healthLabel, larvaeLabel, descLabel);

        Button removeAntButton = new Button("Remove Ant");
        removeAntButton.setOnAction(e -> onAntRemove());

        infoPane.getChildren().add(removeAntButton);
    }

    /**
     * Updates the info pane when an ant is removed.
     */
    public void onAntRemove() {
        // Clear existing content from infoPane
        infoPane.getChildren().clear();
        selectedAnt.disappear();

        // Create infoPane
        Label titleLabel = new Label("Ant Information");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label infoLabel = new Label("Click on the ant to see its information");
        infoLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        infoPane.getChildren().addAll(titleLabel, infoLabel);


    }

    /**
     * Updates the anthill pane when an anthill's larvae amount changes.
     * @param anthill the anthill whose larvae amount changed
     */
    public void updateAnthillPane(Anthill anthill) {
        // Clear existing content from anthillPane
        anthillPane.getChildren().clear();
        if (anthill.getType() == Anthill.Type.RED) {
            redLarvaeCount = anthill.getLarvae();
        } else {
            blueLarvaeCount = anthill.getLarvae();
        }

        // Create and add labels to anthillPane
        HBox larvaeCountBox = new HBox(10);
        larvaeCountBox.setAlignment(Pos.CENTER);

        // Blue Larvae Count Box
        VBox blueLarvaeBox = new VBox(5);
        blueLarvaeBox.setAlignment(Pos.CENTER);

        Label blueLabel = new Label("Blue Larvae");
        blueLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label blueCountLabel = new Label(String.valueOf(blueLarvaeCount));
        blueCountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        blueLarvaeBox.getChildren().addAll(blueLabel, blueCountLabel);

        // Red Larvae Count Box
        VBox redLarvaeBox = new VBox(5);
        redLarvaeBox.setAlignment(Pos.CENTER);

        Label redLabel = new Label("Red Larvae");
        redLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label redCountLabel = new Label(String.valueOf(redLarvaeCount));
        redCountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        redLarvaeBox.getChildren().addAll(redLabel, redCountLabel);

        larvaeCountBox.getChildren().addAll(blueLarvaeBox, redLarvaeBox);

        // Add the larvaeCountBox to anthillPane
        anthillPane.getChildren().addAll(larvaeCountBox);
    }
}
