package com.example.blindsearchgame.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Component
public class Game {
    private List<Player> players;
    private double rewardX;
    private double rewardY;
    private boolean gameFinished;
    private int currentPlayer;
    private static final double INITIAL_DISTANCE = 10;

    public Game() {
        players = new ArrayList<>();
        Random rand = new Random();
        rewardX = rand.nextDouble() * 50 - 25;
        rewardY = rand.nextDouble() * 50 - 25;


        for (int i = 0; i < 3; i++) {

            double angle = Math.toRadians(rand.nextInt(360));
            double playerX = setCord(rewardX+INITIAL_DISTANCE*Math.cos(angle));
            double playerY = setCord(rewardY+INITIAL_DISTANCE*Math.sin(angle));
            double playerDistance =calculateDistance(rewardX,playerX,rewardY,playerY);
            players.add(new Player(i,playerX,playerY,playerDistance));
        }
        gameFinished = false;
        currentPlayer = 0;
    }

    public void movePlayer(int playerId, double angle) {
        Player player = players.get(playerId);
        if (gameFinished || playerId != currentPlayer) {
            return;
        }
        angle = Math.toRadians(angle);
        double newX = player.getX() + Math.cos(angle);
        double newY = player.getY() + Math.sin(angle);

        newX = setCord(newX);
        newY = setCord(newY);
        player.setX(newX);
        player.setY(newY);
        double distance = calculateDistance(newX,rewardX,newY,rewardY);
        player.setDistanceToReward(distance);
        if (distance <= 1) {
            gameFinished = true;
        } else {
            currentPlayer = (currentPlayer + 1) % 3;
        }
    }


    private double calculateDistance(double x1,double x2,double y1,double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private double setCord(double value) {
        return Math.max(-50, Math.min(50, value));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public double getRewardX() {
        return rewardX;
    }

    public double getRewardY() {
        return rewardY;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public static class MoveRequest {
        private int playerId;
        private double angle;

        public int getPlayerId() {
            return playerId;
        }

        public void setPlayerId(int playerId) {
            this.playerId = playerId;
        }

        public double getAngle() {
            return angle;
        }

        public void setAngle(double angle) {
            this.angle = angle;
        }
    }
}
