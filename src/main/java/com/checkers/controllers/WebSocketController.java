package com.checkers.controllers;

import com.checkers.dtos.GameDTO;
import com.checkers.dtos.MoveDTO;
import com.checkers.service.GameService;
import com.checkers.service.StartGameService;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    private final GameService gameService;
    private final StartGameService startGameService;

    public WebSocketController(GameService gameService, StartGameService startGameService) {
        this.gameService = gameService;
        this.startGameService = startGameService;
    }

    @MessageMapping("/game/{gameId}")
    @SendTo("/topic/{gameId}")
    public GameDTO makeMove(MoveDTO move, Principal principal) {

        System.out.println(" user is " + principal.getName());
        return gameService.makeMove(move, principal);
    }

    @MessageExceptionHandler(IllegalArgumentException.class)
    @SendToUser(value = "/queue/errors", broadcast = false)
    public String handleException(IllegalArgumentException exception) {
        return exception.getMessage();
    }

    @MessageMapping("/startGame")
    public void startGame(Principal principal) {
        startGameService.queue(principal.getName());
    }

}
