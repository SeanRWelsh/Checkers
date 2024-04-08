package com.checkers.service;

import com.checkers.models.Game;
import com.checkers.models.Piece;
import com.checkers.models.Player;
import com.checkers.models.enums.PieceColor;
import com.checkers.repositories.GameRepository;
import com.checkers.repositories.PieceRepository;
import com.checkers.repositories.PlayerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GameCreationService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final PieceRepository pieceRepository;

    public GameCreationService(PlayerRepository playerRepository, GameRepository gameRepository,
                               PieceRepository pieceRepository){
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
        this.pieceRepository = pieceRepository;
    }

    public Game createGame(long player1_id, long player2_id){
        Player player1 = playerRepository.findById(player1_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Player with id " + player1_id + " not found."));
        Player player2 = playerRepository.findById(player2_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Player with id " + player2_id + " not found."));
        Game newGame = new Game();
        newGame.addPlayer(player1);
        newGame.addPlayer(player2);
        Game savedGame = gameRepository.save(newGame);
        createPieces(savedGame, player1, player2);
        return savedGame;
    }

    private void createPieces(Game game, Player player1, Player player2){
        PieceColor pieceColor = PieceColor.RED;
        Player player = player1;
        int column = 0;
        for(int row = 0; row<=7; row++){
            if(row%2 != 0) column = 1;
            while(column<= 7){
                Piece piece = new Piece(pieceColor, false, row, column, game, player);
                game.addPiece(pieceRepository.save(piece));
                column+=2;
            }
            column = 0;
            if(row == 2){
                row = 4;
                pieceColor = PieceColor.BLACK;
                player = player2;
            }
        }
    }
}
