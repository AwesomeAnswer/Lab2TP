package com.example.blindsearchgame.config;

import com.example.blindsearchgame.model.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {

    @Bean
    public Game game() {
        return new Game();
    }
}
