package com.example.blindsearchgame.controller;

import com.example.blindsearchgame.model.Game;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private Game game;

    public GameController() {
        game = new Game();
    }

    @GetMapping("/status")
    public Game getStatus() {
        return game;
    }

    @PostMapping("/move")
    public Game movePlayer(@RequestParam("playerId") int playerId, @RequestParam("angle") double angle) {
        game.movePlayer(playerId, angle);
        return game;
    }
}
