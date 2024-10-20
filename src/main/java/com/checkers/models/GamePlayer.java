package com.checkers.models;

import com.checkers.models.enums.PieceColor;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "game_players")
public class GamePlayer {

    @EmbeddedId
    private GamePlayerId id;

    @ManyToOne
    @MapsId("playerId") // This maps to player_id in the composite key
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @MapsId("gameId") // This maps to game_id in the composite key
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PieceColor color;

    // Default constructor
    public GamePlayer() {
    }

    public GamePlayer(Player player, Game game, PieceColor color) {
        this.player = player;
        this.game = game;
        this.color = color;
        this.id = new GamePlayerId(player.getId(), game.getId()); // Set the composite key
    }

    // Getters and Setters
    public GamePlayerId getId() {
        return id;
    }

    public void setId(GamePlayerId id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }
}