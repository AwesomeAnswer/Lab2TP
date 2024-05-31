package com.example.blindsearchgame.model;

import java.util.Random;

public class Game {
    private static final double INITIAL_DISTANCE = 10;
    private static final String[] COLORS = {"red", "green", "blue"};

    private Player[] players;
    private boolean gameFinished;
    private double rewardX;
    private double rewardY;

    public Game() {
        Random rand = new Random();
        rewardX = rand.nextDouble() * 50 - 25; // random x in range [-25, 25]
        rewardY = rand.nextDouble() * 50 - 25; // random y in range [-25, 25]
        players = new Player[3];

        for (int i = 0; i < players.length; i++) {
            double angle = Math.toRadians(rand.nextInt(360));
            players[i] = new Player();
            players[i].setId(i);
            players[i].setX(clamp(rewardX + INITIAL_DISTANCE * Math.cos(angle)));
            players[i].setY(clamp(rewardY + INITIAL_DISTANCE * Math.sin(angle)));
            players[i].setColor(COLORS[i]);
            updateDistance(players[i]);
        }
        gameFinished = false;
    }

    private void updateDistance(Player player) {
        double distance = Math.sqrt(Math.pow(player.getX() - rewardX, 2) + Math.pow(player.getY() - rewardY, 2));
        player.setDistanceToReward(distance);
    }

    public void movePlayer(int playerId, double angle) {
        if (gameFinished) return;
        Player player = players[playerId];
        player.setX(clamp(player.getX() + Math.cos(angle)));
        player.setY(clamp(player.getY() + Math.sin(angle)));
        updateDistance(player);
        if (player.getDistanceToReward() <= 1) {
            gameFinished = true;
        }
    }

    private double clamp(double value) {
        return Math.max(-50, Math.min(50, value));
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public double getRewardX() {
        return rewardX;
    }

    public double getRewardY() {
        return rewardY;
    }
}
