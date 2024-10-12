package com.checkers.dtos;

public class MoveDTO {

    private long id;
    private int sourceRow;
    private int sourceColumn;
    private int destinationRow;
    private int destinationColumn;
    private Long gameId;
    private Long playerId;
    private Long pieceId;

    public MoveDTO() {
    }

    public MoveDTO(long id, int sourceRow, int sourceColumn, int destinationRow, int destinationColumn, Long gameId,
            Long playerId, Long pieceId) {
        this.id = id;
        this.sourceRow = sourceRow;
        this.sourceColumn = sourceColumn;
        this.destinationRow = destinationRow;
        this.destinationColumn = destinationColumn;
        this.gameId = gameId;
        this.playerId = playerId;
        this.pieceId = pieceId;
    }

    public long getId() {
        return id;
    }

    public int getSourceRow() {
        return this.sourceRow;
    }

    public int getSourceColumn() {
        return this.sourceColumn;
    }

    public int getDestinationRow() {
        return this.destinationRow;
    }

    public int getDestinationColumn() {
        return this.destinationColumn;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getPieceId() {
        return pieceId;
    }
}
