package com.checkers.models;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class GamePlayerId implements Serializable {

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "game_id")
    private Long gameId;

    // Default constructor
    public GamePlayerId() {
    }

    public GamePlayerId(Long playerId, Long gameId) {
        this.playerId = playerId;
        this.gameId = gameId;
    }

    // Getters and Setters
    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    // Override equals() and hashCode() for proper functioning
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof GamePlayerId))
            return false;
        GamePlayerId that = (GamePlayerId) o;
        return playerId.equals(that.playerId) && gameId.equals(that.gameId);
    }

    @Override
    public int hashCode() {
        return 31 * playerId.hashCode() + gameId.hashCode();
    }
}
