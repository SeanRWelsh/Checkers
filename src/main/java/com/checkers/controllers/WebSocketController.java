package com.checkers.controllers;

import com.checkers.dtos.GameDTO;
import com.checkers.dtos.MoveDTO;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PieceRepository;
import com.checkers.service.GameService;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
public class WebSocketController {
    private final GameService gameService;

    public WebSocketController(GameService gameService){
        this.gameService = gameService;
    }

    @MessageMapping("/game/{gameId}")
    @SendTo("/topic/{gameId}")
    public GameDTO makeMove(MoveDTO move) {
            return gameService.makeMove(move);
    }

    @MessageExceptionHandler(IllegalArgumentException.class)
    @SendToUser(value = "/queue/errors", broadcast = false)
    public String handleException(IllegalArgumentException exception) {
        return exception.getMessage();
    }

}
