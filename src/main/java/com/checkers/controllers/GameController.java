package com.checkers.controllers;

import com.checkers.dtos.GameDTO;
import com.checkers.models.Game;
import com.checkers.models.Player;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PlayerRepository;
import com.checkers.securityConfiguration.SecurityUser;
import com.checkers.service.GameService;

import jakarta.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public GameController(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping("/resume")
    public ResponseEntity<GameDTO> getGame(@AuthenticationPrincipal SecurityUser user) {
        Player player = playerRepository.findPlayerByUsername(user.getUsername());
        // Limit not in JPQL used pageable to limit return size to a List of 1. Could
        // have done a native query to use limit but then would have to query for the
        // game id then query for the game. also easy to modify this in the future to
        // send back a list of games they may want to resume.
        Pageable pageable = PageRequest.of(0, 1);
        Game game = gameRepository.findMostRecentGame(player.getId(), pageable).get(0);
        return ResponseEntity.status(HttpStatus.OK).body(new GameDTO(game));
    }

    public record CreateGameRequest(long player1_id, long player2_id) {
    }
}