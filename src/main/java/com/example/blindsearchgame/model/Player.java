package com.example.blindsearchgame.model;

public class Player {
    private int id;
    private double x;
    private double y;
    private double distanceToReward;
    private String color;

    private static final String[] COLORS = {"red", "green", "blue"};

    public Player(int id, double x, double y, double distanceToReward) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.distanceToReward = distanceToReward;
        this.color = COLORS[id];
    }

    public int getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistanceToReward() {
        return distanceToReward;
    }

    public void setDistanceToReward(double distanceToReward) {
        this.distanceToReward = distanceToReward;
    }

    public String getColor() {
        return color;
    }
}
