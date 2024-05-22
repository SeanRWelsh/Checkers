package com.checkers.dtos;

import com.checkers.models.Game;
import com.checkers.models.Piece;
import com.checkers.models.Player;
import com.checkers.models.enums.PieceColor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

public class PieceDTO {
    private long id;
    private PieceColor color;
    private Boolean king;
    private int row;
    private int column;
    private PlayerGameDTO player;

    public PieceDTO(Piece piece){
        this.id = piece.getId();
        this.color = piece.getColor();
        this.king = piece.getKing();
        this.row = piece.getRow();
        this.column = piece.getColumn();
        this.player = new PlayerGameDTO(piece.getPlayer());
    }

    public long getId() {
        return id;
    }

    public PlayerGameDTO getPlayer() {
        return player;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Boolean getKing() {
        return king;
    }

    public PieceColor getColor() {
        return color;
    }
}
