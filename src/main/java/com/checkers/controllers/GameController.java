package com.checkers.controllers;

import com.checkers.dtos.GameDTO;
import com.checkers.models.Game;
import com.checkers.models.Player;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PlayerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/game")
public class GameController {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public GameController(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<GameDTO> getGame(@PathVariable Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with id " + gameId + " not found."));
        return ResponseEntity.status(HttpStatus.OK).body(new GameDTO(game));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<GameDTO> createGame(@RequestBody CreateGameRequest createGameRequest,
            HttpServletRequest request) {
        Player player1 = playerRepository.findById(createGameRequest.player1_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Player with id " + createGameRequest.player1_id + " not found."));
        Player player2 = playerRepository.findById(createGameRequest.player2_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Player with id " + createGameRequest.player2_id + " not found."));
        Game newGame = new Game();
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        Game savedGame = gameRepository.save(newGame);

        return ResponseEntity.status(HttpStatus.CREATED).body(new GameDTO(newGame));
    }

    public record CreateGameRequest(long player1_id, long player2_id) {

    }
}
