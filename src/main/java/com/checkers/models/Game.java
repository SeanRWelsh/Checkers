package com.checkers.models;

import com.checkers.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "start_time")
    @NotNull
    private LocalDateTime startTime;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;

    @ManyToOne
    @JoinColumn(name = "player_turn")
    private Player playerTurn;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Piece> pieces;

    public Game() {
        this.startTime = LocalDateTime.now();
        this.status = Status.IN_PROGRESS;
        this.pieces = new HashSet<>();
    }

    public long getId() {
        return this.id;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<GamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        System.out.println("adding player to Game " + gamePlayer);
        this.gamePlayers.add(gamePlayer);
        gamePlayer.setGame(this); // Set the game in the GamePlayer
    }

    public void removeGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayers.remove(gamePlayer);
        gamePlayer.setGame(null); // Unset the game in the GamePlayer
    }

    public Player getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(Player player) {
        this.playerTurn = player;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("game id ").append(getId()).append(" players: ");
        for (GamePlayer gamePlayer : gamePlayers) {
            stringBuilder.append(gamePlayer.getPlayer().getId()).append(", ");
            stringBuilder.append(gamePlayer.getPlayer().getUsername()).append(", ");
        }
        stringBuilder.append(" players turn " + getPlayerTurn());
        return stringBuilder.toString();
    }
}