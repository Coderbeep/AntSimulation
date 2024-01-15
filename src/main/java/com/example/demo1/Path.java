package com.example.demo1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * This class represents a path between two points in a 2D space.
 * The points are represented by the {@link Spot} class.
 */
public class Path {
    private Spot start;
    private Spot end;


    /**
     * Constructs a new Path object with the specified start and end points.
     * @param start the starting point of the path
     * @param end the ending point of the path
     */
    public Path(Spot start, Spot end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Calculates and returns the distance between the start and end points of the path.
     * @return the distance between the start and end points
     */
    public double getDistance() {
        return Math.sqrt(Math.pow(start.getX() - end.getX(), 2) + Math.pow(start.getY() - end.getY(), 2));
    }

    /**
     * Draws the path on the specified Pane.
     * @param pane the Pane on which to draw the path
     */
    public void drawOnPane(Pane pane) {
        Line line = new Line(start.getX(), start.getY(), end.getX(), end.getY());
        line.setStroke(Color.BLACK);
        line.setStrokeWidth(2);
        pane.getChildren().add(line);
    }

    public Spot getStart() {return start;}
    public Spot getEnd() {return end;}

    public void setStart(Spot start) {this.start = start;}
    public void setEnd(Spot end) {this.end = end;}
}
