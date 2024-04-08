package com.checkers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "moves")
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    int sourceRow;

    @NotNull
    int sourceColumn;

    @NotNull
    int destinationRow;

    @NotNull
    int destinationColumn;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "piece_id")
    private Piece piece;

    public Move(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSourceRow(){
        return this.sourceRow;
    }

    public void setSourceRow(int sourceRow){
        this.sourceRow = sourceRow;
    }
    public int getSourceColumn(){
        return this.sourceColumn;
    }

    public void setSourceColumn(int sourceColumn){
        this.sourceColumn = sourceColumn;
    }

    public int getDestinationRow(){
        return this.destinationRow;
    }

    public void setDestinationRow(int destinationRow){
        this.destinationRow = destinationRow;
    }

    public int getDestinationColumn(){
        return this.destinationColumn;
    }

    public void setDestinationColumn(int destinationColumn){
        this.destinationColumn = destinationColumn;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
