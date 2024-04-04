package com.checkers.dtos;

import com.checkers.models.Game;
import com.checkers.models.Player;
import com.checkers.models.enums.Status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameDTO {
    private long id;
    private Status status;
    private LocalDateTime startTime;
    private LocalDateTime updatedAt;
    private Set<PlayerDTO> players;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.status = game.getStatus();
        this.startTime = game.getStartTime();
        this.updatedAt = game.getUpdatedAt();
        this.players = new HashSet<>();
        for(Player player: game.getGamePlayers()){
            this.players.add(new PlayerDTO(player));
        }

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerDTO> players) {
        this.players = players;
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
