package com.checkers.dtos;

import com.checkers.models.Player;

public class PlayerHomeDTO {
    private Long id;
    private String name;
    private String username;
    private int wins;
    private int losses;
    private int moves;
    private String email;

    public PlayerHomeDTO() {
    }

    public PlayerHomeDTO(Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.wins = player.getWins();
        this.losses = player.getLosses();
        this.moves = player.getMoves();
        this.username = player.getUsername();
        this.email = player.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getWins() {
        return this.wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getMoves() {
        return this.moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "player username is " + this.username + " player has " + this.wins + " wins";
    }

}
