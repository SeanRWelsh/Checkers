package com.checkers.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.checkers.models.Game;
import com.checkers.models.Piece;
import com.checkers.models.Player;
import com.checkers.models.enums.PieceColor;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PlayerRepository;

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

    public void queue(String userName) {
        if (queue.isEmpty()) {
            queue.add(userName);
            this.messagingTemplate.convertAndSendToUser(userName, "/startGame", "waiting for other players");
        } else {
            String player1 = queue.remove();
            String player2 = userName;

            Game newGame = createGame(player1, player2);
            this.messagingTemplate.convertAndSendToUser(player1, "/startGame", newGame);
            this.messagingTemplate.convertAndSendToUser(player2, "/startGame", newGame);
        }

    }

    public Game createGame(String player1Username, String player2Username) {
        Player player1 = playerRepository.findPlayerByUsername(player1Username);
        Player player2 = playerRepository.findPlayerByUsername(player2Username);
        Game newGame = new Game();
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        newGame.setPlayerTurn(player1);
        createPieces(newGame, player1, player2);

        return gameRepository.save(newGame);
    }

    private void createPieces(Game game, Player player1, Player player2) {
        PieceColor pieceColor = PieceColor.RED;
        Player player = player1;
        int column = 0;

        for (int row = 0; row <= 7; row++) {
            if (row % 2 != 0)
                column = 1;
            while (column <= 7) {
                Piece piece = new Piece(pieceColor, false, row, column, game, player);
                game.addPiece(piece);
                column += 2;
            }
            column = 0;
            if (row == 2) {
                row = 4;
                pieceColor = PieceColor.BLACK;
                player = player2;
            }
        }
    }

}
