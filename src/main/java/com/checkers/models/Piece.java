package com.checkers.models;

import com.checkers.models.enums.PieceColor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pieces")
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PieceColor color;

    @NotNull
    private Boolean king;

    private int row;

    @Column(name = "col")
    private int column;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    public Piece(){}
    public Piece(PieceColor color, Boolean king, int row, int column, Game game, Player player){
        this.color = color;
        this.king = king;
        this.row = row;
        this.column = column;
        this.game = game;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Boolean getKing() {
        return king;
    }

    public void setKing(Boolean king) {
        this.king = king;
    }

    public PieceColor getColor() {
        return color;
    }
}
