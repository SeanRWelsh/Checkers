package com.checkers.controllers;

import com.checkers.dtos.GameDTO;
import com.checkers.dtos.MoveDTO;
import com.checkers.models.Game;
import com.checkers.models.Move;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PieceRepository;
import com.checkers.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class WebSocketController {
    private final GameRepository gameRepository;
    private final PieceRepository pieceRepository;
    private final GameService gameService;

    public WebSocketController(GameRepository gameRepository, PieceRepository pieceRepository, GameService gameService){
        this.gameRepository = gameRepository;
        this.pieceRepository = pieceRepository;
        this.gameService = gameService;
    }
//    @MessageMapping("/game/{gameId}")
//    @SendTo("/topic/{gameId}")
//    public String makeMove(String message){
//        System.out.println("I made it to makeMove" + message);
//        return message;
//    }

    @MessageMapping("/game/{gameId}")
    @SendTo("/topic/{gameId}")
    public GameDTO makeMove(MoveDTO move){
        System.out.println("in makeMove");
        Game game = gameRepository.findById(move.getGameId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with id " + move.getGameId() + " not found."));
        if(gameService.validateMove(game, move)){
            pieceRepository.movePiece(move.getPieceId(), move.getDestinationRow(), move.getDestinationColumn());
            game = gameRepository.save(game);
        }

        return new GameDTO(game);
    }

}
