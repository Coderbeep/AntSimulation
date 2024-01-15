package com.example.demo1;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * This class represents a StoneSpot in the simulation. In such spots ants can fight.
 */
public class StoneSpot extends Spot implements FightableSpot {
    /**
     * Constructs a new StoneSpot object with the specified coordinates.
     * @param x the x coordinate of the spot
     * @param y the y coordinate of the spot
     */
    public StoneSpot(int x, int y) {
        super(x, y);
    }

    @Override
    public void drawOnPane(Pane pane) {
        Circle circle = new Circle(this.getX(), this.getY(), 20, Color.GRAY);

        Text text = new Text(this.getX(), this.getY(), Integer.toString(getId()));
        text.setFont(javafx.scene.text.Font.font("Verdana", 23));
        text.setFill(Color.LAWNGREEN);
        pane.getChildren().add(circle);
        pane.getChildren().add(text);
    }

    @Override
    public boolean isFightAllowed() {
        return true;
    }

    public void handleFight() {
        AntFightManager.handleFights(this);
    }
}
