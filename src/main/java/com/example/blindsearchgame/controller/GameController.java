package com.example.blindsearchgame.controller;

import com.example.blindsearchgame.model.Game;
import com.example.blindsearchgame.model.Game.MoveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private final Game game;

    @Autowired
    public GameController(Game game) {
        this.game = game;
    }

    @MessageMapping("/move")
    @SendTo("/topic/game")
    public Game move(MoveRequest moveRequest) {
        game.movePlayer(moveRequest.getPlayerId(), moveRequest.getAngle());
        return game;
    }

    @MessageMapping("/status")
    @SendTo("/topic/game")
    public Game status() {
        return game;
    }
}
