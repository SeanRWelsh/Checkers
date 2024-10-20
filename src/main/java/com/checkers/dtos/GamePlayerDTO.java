package com.checkers.dtos;

import com.checkers.models.GamePlayer;
import com.checkers.models.enums.PieceColor;

public class GamePlayerDTO {
    private String username;
    private PieceColor color;

    public GamePlayerDTO(GamePlayer gamePlayer) {
        this.username = gamePlayer.getPlayer().getUsername();
        this.color = gamePlayer.getColor();
    }

    public PieceColor getColor() {
        return color;
    }

    public String getUsername() {
        return username;
    }

}
