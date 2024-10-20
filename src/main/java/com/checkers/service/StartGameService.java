package com.checkers.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.checkers.dtos.GameDTO;
import com.checkers.models.Game;
import com.checkers.models.GamePlayer;
import com.checkers.models.Piece;
import com.checkers.models.Player;
import com.checkers.models.enums.PieceColor;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class StartGameService {
    private final Queue<String> queue;
    private final SimpMessagingTemplate messagingTemplate;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public StartGameService(SimpMessagingTemplate messagingTemplate, PlayerRepository playerRepository,
            GameRepository gameRepository) {
        this.messagingTemplate = messagingTemplate;
        this.queue = new LinkedList<>();
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Transactional
    public void queue(String userName) {
        if (queue.isEmpty()) {
            queue.add(userName);
            this.messagingTemplate.convertAndSendToUser(userName, "/startGame", "waiting for other players");
        } else {
            String player1 = queue.remove();
            String player2 = userName;

            Game newGame = createGame(player1, player2);
            this.messagingTemplate.convertAndSendToUser(player1, "/queue/startGame", new GameDTO(newGame));
            this.messagingTemplate.convertAndSendToUser(player2, "/queue/startGame", new GameDTO(newGame));
        }

    }

    public Game createGame(String player1Username, String player2Username) {
        Player player1 = playerRepository.findPlayerByUsername(player1Username);
        Player player2 = playerRepository.findPlayerByUsername(player2Username);
        Game newGame = new Game();

        // Create GamePlayer instances for both players
        GamePlayer gamePlayer1 = new GamePlayer(player1, newGame, PieceColor.RED);
        GamePlayer gamePlayer2 = new GamePlayer(player2, newGame, PieceColor.BLACK);

        // Add GamePlayer instances to the game
        newGame.addGamePlayer(gamePlayer1);
        newGame.addGamePlayer(gamePlayer2);

        newGame.setPlayerTurn(player1);
        createPieces(newGame, gamePlayer1, gamePlayer2);

        return gameRepository.save(newGame);
    }

    private void createPieces(Game newGame, GamePlayer gamePlayer1, GamePlayer gamePlayer2) {
        Set<Piece> pieces = new HashSet<>();
        GamePlayer player = gamePlayer1;
        int column = 0;

        for (int row = 0; row <= 7; row++) {
            if (row % 2 != 0)
                column = 1;
            while (column <= 7) {
                Piece piece = new Piece(player.getColor(), false, row, column, player.getGame(), player.getPlayer());
                pieces.add(piece);
                column += 2;
            }
            column = 0;
            if (row == 2) {
                row = 4;
                player = gamePlayer2;
            }
        }
        newGame.setPieces(pieces);

    }

}
