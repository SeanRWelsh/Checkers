package com.checkers.controllers;

import com.checkers.dtos.GameDTO;
import com.checkers.models.Game;
import com.checkers.models.Player;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PlayerRepository;
import com.checkers.securityConfiguration.SecurityUser;
import com.checkers.service.GameService;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameRepository gameRepository;
    private final GameService gameService;
    private final PlayerRepository playerRepository;

    public GameController(GameRepository gameRepository, GameService gameService, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.gameService = gameService;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/resume")
    public ResponseEntity<GameDTO> getGame(@AuthenticationPrincipal SecurityUser user) {
        Player player = playerRepository.findPlayerByUsername(user.getUsername());
        Long gameId = gameRepository.findMostRecentGame(player.getId());
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with id " + gameId + " not found."));
        return ResponseEntity.status(HttpStatus.OK).body(new GameDTO(game));
    }

    @PostMapping
    public ResponseEntity<GameDTO> createGame(@RequestBody @NotNull CreateGameRequest createGameRequest) {
        Game newGame = gameService.createGame(createGameRequest.player1_id, createGameRequest.player2_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GameDTO(newGame));
    }

    public record CreateGameRequest(long player1_id, long player2_id) {
    }
}