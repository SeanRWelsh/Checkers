package com.checkers.models;

import com.checkers.models.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.ColumnDefault;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @ColumnDefault("IN_PROGRESS")
    private Status status;

    @Column(name = "start_time")
    @NotNull
    private LocalDateTime startTime;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "game_players",
            joinColumns = {@JoinColumn(name = "game_id")},
            inverseJoinColumns = {@JoinColumn(name = "player_id")})
    private List<Player> gamePlayers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;

    @ManyToOne
    @JoinColumn(name = "player_turn")
    private Player playerTurn;

    @OneToMany(mappedBy="game", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
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

    public List<Player> getGamePlayers() {
        return this.gamePlayers;
    }

    public void addPlayer(Player player) {
        System.out.println("adding player to Game " + player);
            this.gamePlayers.add(player);
            player.getGamesPlayed().add(this);
    }
    public void addPiece(Piece piece) {
        this.pieces.add(piece);
    }

    public void removePiece(Piece piece){
        this.pieces.remove(piece);
    }

    public void removePlayer(Player player) {
        this.gamePlayers.remove(player);
        player.getGamesPlayed().remove(this);
    }

    public Player getPlayerTurn(){
        return playerTurn;
    }

    public void setPlayerTurn(Player player){
        this.playerTurn = player;
    }

    public void setGamePlayers(List<Player> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }



    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("game id ").append(getId()).append(" players: ");
        for (Player gamePlayer : gamePlayers) {
            stringBuilder.append(gamePlayer.getId()).append(", ");
            stringBuilder.append(gamePlayer.getUsername()).append(", ");
        }
        return stringBuilder.toString();
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
}
