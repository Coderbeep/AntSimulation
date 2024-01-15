package com.example.demo1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * This class represents a LeafSpot in the simulation. In such spots ants cannot fight.
 */
public class LeafSpot extends Spot {
    /**
     * Constructs a new LeafSpot object with the specified coordinates.
     * @param x the x coordinate of the spot
     * @param y the y coordinate of the spot
     */
    public LeafSpot(int x, int y) {
        super(x, y);
    }

    /**
     * Draws the spot on the specified Pane.
     * @param pane the Pane on which to draw the spot
     */
    @Override
    public void drawOnPane(Pane pane) {
        Circle circle = new Circle(this.getX(), this.getY(), 10, Color.GREEN);
        circle.setEffect(new javafx.scene.effect.DropShadow(10, Color.BLACK));
        Text text = new Text(this.getX(), this.getY(), Integer.toString(getId()));
        text.setFont(javafx.scene.text.Font.font("Verdana", 23));
        text.setFill(Color.LAWNGREEN);
        pane.getChildren().add(circle);
        pane.getChildren().add(text);
    }
}
