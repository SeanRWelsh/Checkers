package com.checkers.dtos;

import com.checkers.models.Player;

public class PlayerGameDTO{
    private Long id;
    private String username;

    public PlayerGameDTO(Player player) {
        this.id = player.getId();
        this.username = player.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}


