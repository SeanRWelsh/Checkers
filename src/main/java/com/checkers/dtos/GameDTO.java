package com.checkers.dtos;

import com.checkers.models.Game;
import com.checkers.models.GamePlayer;
import com.checkers.models.Piece;
import com.checkers.models.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameDTO {
    private final long id;
    private Status status;
    private PlayerGameDTO winner;
    private LocalDateTime startTime;
    private LocalDateTime updatedAt;
    private Set<GamePlayerDTO> players;
    private List<PieceDTO> pieces;
    private String player_turn;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.status = game.getStatus();
        if (game.getWinner() != null)
            this.winner = new PlayerGameDTO(game.getWinner());
        this.startTime = game.getStartTime();
        this.updatedAt = game.getUpdatedAt();
        this.players = new HashSet<>();
        for (GamePlayer player : game.getGamePlayers()) {
            this.players.add(new GamePlayerDTO(player));
        }
        this.pieces = new ArrayList<>();
        for (Piece piece : game.getPieces()) {
            this.pieces.add(new PieceDTO(piece));
        }
        this.player_turn = game.getPlayerTurn().getUsername();
    }

    public long getId() {
        return id;
    }

    public Status getStatus() {
        return this.status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Set<GamePlayerDTO> getPlayers() {
        return players;
    }

    public PlayerGameDTO getWinner() {
        return winner;
    }

    public String getPlayer_turn() {
        return player_turn;
    }

    public List<PieceDTO> getPieces() {
        return pieces;
    }

    @Override
    public String toString() {
        return "GameDTO{" +
                "id=" + id +
                ", status=" + status +
                ", startTime=" + startTime +
                ", updatedAt=" + updatedAt +
                ", players=" + players +
                '}';
    }

}
