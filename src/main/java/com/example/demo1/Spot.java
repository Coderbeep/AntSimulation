package com.example.demo1;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Represents a spot in the simulation, which can contain ants and larvae.
 * Each spot has a unique id, coordinates, and a list of neighboring spots.
 */
public abstract class Spot {
    private final Object larvaeLock = new Object();
    public static int idCounter = 0;
    public static final int SPOT_RADIUS = 8;
    private int x = 0;
    private int y = 0;
    private int id;
    private int larvae;
    private Spot parent = null;
    private ArrayList<Ant> ants = new ArrayList<>();
    private int degree = 0;
    private ArrayList<Spot> neighbors = new ArrayList<>();

    /**
     * Adds an ant to the list of ants at this spot.
     * @param ant The ant to add.
     */
    public void addAnt(Ant ant) {
        synchronized (ants) {
            ants.add(ant);
        }
    }

    /**
     * Removes an ant from the list of ants at this spot.
     * @param ant The ant to remove.
     */
    public void removeAnt(Ant ant) {
        synchronized (ants) {
            ants.remove(ant);
        }
    }

    public Spot(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.id = idCounter; // Assign the current value of idCounter to id
        idCounter++;

        if (!(this instanceof Anthill)) {
            this.larvae = (int) (Math.random() * 100);
        }

    }

    /**
     * Static method to calculate the distance between two spots.
     * @param spot1 The first spot
     * @param spot2 The second spot
     * @return The distance between the two spots (float)
     */
    public static float distance(Spot spot1, Spot spot2) {
        return (float) Math.sqrt(Math.pow(spot1.getX() - spot2.getX(), 2) + Math.pow(spot1.getY() - spot2.getY(), 2));
    }

    /**
     * Draws the spot on the given pane.
     * @param pane The pane to draw the spot on.
     */
    public void drawOnPane(Pane pane) {
        Circle circle = new Circle(this.getX(), this.getY(), 8, Color.BLACK);
        // add text to the circle with the id of the spot
        Text text = new Text(this.getX(), this.getY(), Integer.toString(id));
        text.setFont(javafx.scene.text.Font.font("Verdana", 23));
        text.setFill(Color.LAWNGREEN);
        pane.getChildren().add(circle);
        pane.getChildren().add(text);
    }

    public ArrayList<Spot> getNeighbors() { return neighbors;}
    public int getDegree() { return degree; }
    public ArrayList<Ant> getAnts() { return ants; }
    public Spot getParent() { return parent; }
    public int getId() { return id;}
    public int getX() { return x; }
    public int getY() { return y; }
    public int getLarvae() { return larvae; }

    public void setNeighbors(ArrayList<Spot> neighbors) { this.neighbors = neighbors; }
    public void setDegree(int degree) { this.degree = degree; }
    public void setAnts(ArrayList<Ant> ants) { this.ants = ants; }
    public void setParent(Spot parent) { this.parent = parent; }
    public void setId(int id) { this.id = id; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setLarvae(int larvae) {
        this.larvae = larvae;
    }
}
