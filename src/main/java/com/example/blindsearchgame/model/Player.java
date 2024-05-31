package com.example.blindsearchgame.model;

public class Player {
    private int id;
    private double x;
    private double y;
    private double distanceToReward;
    private String color;

    public Player() {}

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setColor(String color) {
        this.color = color;
    }
}
