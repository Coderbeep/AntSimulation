package com.example.demo1;

import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

/**
 * Class representing an anthill in the simulation.
 */
public class Anthill extends Spot {
    /**
     * Enum representing the type of anthill.
     */
    public enum Type {
        RED(Color.RED), BLUE(Color.BLUE);
        private final Color color;

        Type(Color color) {
            this.color = color;
        }

        /**
         * Getter for the color of the anthill type.
         * @return The color of the anthill type.
         */
        public Color getColor() {
            return color;
        }
    }

    private Type type;
    private int radius = 25;
    private final SpotStateListener listener = PaneManager.getInstance().getInfoStage();

    /**
     * Constructor for the Anthill class.
     * @param x The x-coordinate of the anthill.
     * @param y The y-coordinate of the anthill.
     * @param type The type of the anthill.
     */
    public Anthill(int x, int y, Type type) {
        super(x, y);
        this.type = type;
    }

    /**
     * Draws the anthill on a given pane.
     * @param pane The pane on which to draw the anthill.
     */
    @Override
    public void drawOnPane(Pane pane) {
        Color fillColor = type.getColor();
        Circle circle = new Circle(this.getX(), this.getY(), radius, fillColor);
        pane.getChildren().add(circle);
    }

    /**
     * Sets the number of larvae in the anthill and updates the listener.
     * @param larvae The number of larvae to set.
     */
    @Override
    public void setLarvae(int larvae) {
        super.setLarvae(larvae);
        listener.onSpotLarvaeChange(this);
    }

    /**
     * Getter for the type of the anthill.
     * @return The type of the anthill.
     */
    public Type getType() {return type;}

    /**
     * Getter for the radius of the anthill.
     * @return The radius of the anthill.
     */
    public int getRadius() {return radius;}

    /**
     * Setter for the type of the anthill.
     * @param type The type to set.
     */
    public void setType(Type type) {this.type = type;}

    /**
     * Setter for the radius of the anthill.
     * @param radius The radius to set.
     */
    public void setRadius(int radius) {this.radius = radius;}
}
