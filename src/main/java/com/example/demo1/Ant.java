package com.example.demo1;

import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.*;

/**
 * The base class for all Ants in the simulation.
 */
public abstract class Ant implements Runnable, AntAttacker {
    private volatile boolean isAlive = true;
    private final String antName;
    private final Color antColor;
    private final int antStrength;
    private int antHealth;
    private final Anthill antHome;
    private Rectangle antRect;
    private int larvaeCarried = 0;
    private TransitionCallback onTransitionFinished;
    private Spot currentNode;
    private boolean comingBack = false;
    private final AntStateListener listener = PaneManager.getInstance().getInfoStage();

    /**
     * Constructs an Ant with the specified attributes.
     *
     * @param antHome    The anthill to which the ant belongs.
     * @param color      The color of the ant.
     * @param name       The name of the ant.
     * @param strength   The strength of the ant, representing how many larvae it can carry.
     * @param health     The initial health of the ant.
     */
    public Ant(Anthill antHome, Color color, String name, int strength, int health) {
        this.antHome = antHome;
        this.antColor = color;
        this.antName = name;
        this.antStrength = strength; // how many larvae it can carry
        this.antHealth = health;

        currentNode = antHome;
        spawn();
    }

    /**
     * Initializes the graphical representation of the ant and sets up event handlers.
     */
    private void spawn() {
        int ANT_RECT_WIDTH = 10;
        int ANT_RECT_HEIGHT = 10;
        Rectangle rect = new Rectangle();

        rect.setOnMouseClicked(mouseEvent -> {
            PaneManager.getInstance().getInfoStage().setAnt(Ant.this);
            findPathToAnthill();
        });

        rect.setOnMouseEntered(event -> {
            // Enlarge the rectangle on hover
            rect.setScaleX(1.5);
            rect.setScaleY(1.5);
            rect.setCursor(Cursor.HAND);
        });

        // Event handler for mouse exit (hover off)
        rect.setOnMouseExited(event -> {
            // Return the rectangle to its normal size
            rect.setScaleX(1.0);
            rect.setScaleY(1.0);
            rect.setCursor(Cursor.DEFAULT);
        });

        rect.setWidth(ANT_RECT_WIDTH);
        rect.setHeight(ANT_RECT_HEIGHT);
        rect.setFill(antColor);
        // add shadow effect to the ant
        rect.setEffect(new DropShadow(5, Color.BLACK));

        rect.setX(antHome.getX() - Math.round((float) ANT_RECT_WIDTH / 2));
        rect.setY(antHome.getY() - Math.round((float) ANT_RECT_HEIGHT / 2));

        this.antRect = rect;
        PaneManager.getInstance().getPane().getChildren().add(rect);
    }

    /**
     * Moves the ant to a random neighboring spot.
     */
    public synchronized void move() {
        currentNode.removeAnt(this);
        ArrayList<Spot> neighbors = currentNode.getNeighbors();
        Spot targetSpot = neighbors.get((int) (Math.random() * neighbors.size()));
        moveTo(targetSpot);
    }

    /**
     * Moves the ant to the specified spot.
     *
     * @param targetSpot The spot to which the ant should move.
     */
    public synchronized void moveTo(Spot targetSpot) {
        currentNode.removeAnt(this);
        double distance = Math.sqrt(Math.pow(targetSpot.getX() - currentNode.getX(), 2) + Math.pow(targetSpot.getY() - currentNode.getY(), 2));

        int velocity = 100;
        double time = distance / velocity;

        TranslateTransition transition = new TranslateTransition(Duration.seconds(time), antRect);

        transition.setByX(targetSpot.getX() - currentNode.getX());
        transition.setByY(targetSpot.getY() - currentNode.getY());

        transition.setOnFinished(event -> {
            currentNode = targetSpot;
            targetSpot.addAnt(this);

            if (onTransitionFinished != null) {
                onTransitionFinished.execute(currentNode, targetSpot, this);
            }
        });

        Platform.runLater(transition::play);
        try {
            Thread.sleep((long) (Math.ceil(time * 1000) + 1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initiates the thread for the ant's behavior, including movement and larvae handling.
     */
    @Override
    public void run() {
        while(isAlive) {
            if (this.getLarvaeCarried() == this.antStrength) {
                returnToAnthill();
            }
            move();
        }
    }

    /**
     * Drops the larvae carried by the ant on the spot.
     */
    public void dropLarvae() {
        // drops larvae on spot
        // must be synchronised, so that mutual exclusion is ensured
        synchronized (currentNode) {
            if (getLarvaeCarried() > 0) {
                currentNode.setLarvae(currentNode.getLarvae() + getLarvaeCarried());
                setLarvaeCarried(0);
            }
        }
    }

    /**
     * Picks up one larva from the spot and adds it to the ant.
     */
    public void collectLarvae() {
        // picks up larvae from spot and adds it to the ant
        // must be synchronised, so that mutual exclusion is ensured
        synchronized (currentNode) {
            if (currentNode.getLarvae() > 0 && !(currentNode instanceof Anthill)) {
                currentNode.setLarvae(currentNode.getLarvae() - 1);
                setLarvaeCarried(getLarvaeCarried() + 1);
            }
        }
    }


    /**
     * Handles the death of the ant, removing it from the simulation.
     * The larvae it carries are dropped on its current spot.
     */
    public void die() {
        currentNode.removeAnt(this);
        isAlive = false;
        PaneManager.getInstance().getPane().getChildren().remove(antRect);
        currentNode.removeAnt(this);

        // if the ant dies, the larvae it was carrying are dropped on the spot
        dropLarvae();
    }

    /**
     * Handles the removal of an ant from the simulation. The larvae it carries
     * disappear with it.
     */
    public void disappear() {
        currentNode.removeAnt(this);
        isAlive = false;
        PaneManager.getInstance().getPane().getChildren().remove(antRect);
    }

    /**
     * Finds the shortest path from the current spot to the anthill using Dijkstra's algorithm.
     *
     * @return The list of spots representing the path to the anthill.
     */
    public ArrayList<Spot> findPathToAnthill() {
        Map<Spot, Float> distances = new HashMap<>();
        Map<Spot, Spot> previousSpots = new HashMap<>();
        PriorityQueue<Pair<Spot, Float>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getValue));

        distances.put(currentNode, 0f);
        queue.add(new Pair<>(currentNode, 0f));

        while (!queue.isEmpty()) {
            Pair<Spot, Float> pair = queue.poll();
            Spot spot = pair.getKey();
            float distance = pair.getValue();

            if (distance > distances.get(spot)) {
                continue;
            }

            for (Spot neighbor : spot.getNeighbors()) {
                float newDistance = distance + Spot.distance(spot, neighbor);

                if (newDistance < distances.getOrDefault(neighbor, Float.MAX_VALUE)) {
                    distances.put(neighbor, newDistance);
                    previousSpots.put(neighbor, spot); // Record the previous spot directly
                    queue.add(new Pair<>(neighbor, newDistance));
                }
            }
        }
        // Find the path to the anthill
        ArrayList<Spot> pathToAnthill = new ArrayList<>();
        Spot current = antHome;

        while (current != null) {
            pathToAnthill.add(current);
            current = previousSpots.get(current);
        }

        Collections.reverse(pathToAnthill);

        return pathToAnthill;
    }

    /**
     * Moves the ant along the path to the anthill.
     */
    public void returnToAnthill() {
        ArrayList<Spot> pathToAnthill = findPathToAnthill();
        for (Spot spot : pathToAnthill) {
            moveTo(spot);
        }
        setComingBack(false);
    }

    /**
     * Checks if the ant is alive.
     *
     * @return True if the ant is alive, false otherwise.
     */
    public boolean isAlive() { return isAlive; }

    /**
     * Checks if the ant is currently returning to its anthill.
     *
     * @return True if the ant is returning, false otherwise.
     */
    public boolean isComingBack() { return comingBack; }

    public Color getColor() { return antColor; }
    public String getName() { return antName; }
    public int getStrength() { return antStrength; }
    public int getHealth() { return antHealth; }
    public int getLarvaeCarried() { return larvaeCarried; }

    public void setOnTransitionFinished(TransitionCallback callback) {
        this.onTransitionFinished = callback;
    }
    public void setComingBack(boolean comingBack) {
        this.comingBack = comingBack;
    }
    public void setLarvaeCarried(int larvaeCarried) {
        this.larvaeCarried = larvaeCarried;
        listener.onLarvaeCarriedChanged(this);
    }

    public void setHealth(int health) {
        this.antHealth = health;
        listener.onHealthChanged(this);
    }

    /**
     * Generates a description of the ant. Subclasses should override this method to provide
     * specific details about the type of ant.
     *
     * @return A string describing the ant.
     */
    public abstract String description();
    public abstract boolean canAttack();
}