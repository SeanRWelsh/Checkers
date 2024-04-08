package com.checkers.dtos;

import com.checkers.models.Game;
import com.checkers.models.Piece;
import com.checkers.models.Player;

public class MoveDTO {

    private long id;
    int sourceRow;
    int sourceColumn;
    int destinationRow;
    int destinationColumn;
    private Game game;
    private Player player;
    private Piece piece;

    public MoveDTO(){}
    public MoveDTO(long id, int sourceRow, int sourceColumn, int destinationRow, int destinationColumn, Game game,
                   Player player, Piece piece){
        this.id = id;
        this.sourceRow = sourceRow;
        this.sourceColumn = sourceColumn;
        this.destinationRow = destinationRow;
        this.destinationColumn = destinationColumn;
        this.game = game;
        this.player = player;
        this.piece = piece;
    }

    public long getId() {
        return id;
    }

    public int getSourceRow(){
        return this.sourceRow;
    }

    public int getSourceColumn(){
        return this.sourceColumn;
    }


    public int getDestinationRow(){
        return this.destinationRow;
    }

    public int getDestinationColumn(){
        return this.destinationColumn;
    }

    public Game getGame() {
        return game;
    }

    public Player getPlayer() {
        return player;
    }

    public Piece getPiece() {
        return piece;
    }
}
