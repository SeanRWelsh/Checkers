package com.checkers.dtos;

import com.checkers.entities.Player;

public class PlayerDTO {
    final private Long id;
    private String name;
    private String userName;
    private int wins;
    private int losses;
    private int moves;
    private String email;

    public PlayerDTO(Player player){
        this.id = player.getId();
        this.name = player.getName();
        this.wins = player.getWins();
        this.losses = player.getLosses();
        this.moves = player.getMoves();
        this.userName = player.getUserName();
        this.email = player.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public String getUserName(){return this.userName;}
    public void setUserName(String userName){this.userName = userName;}

    public String getEmail(){return this.email;}
    public void setEmail(String email){this.email = email;}

    public int getWins(){return this.wins;}
    public void setWins(int wins){this.wins = wins;}

    public int getLosses(){return this.losses;}
    public void setLosses(int losses){this.losses = losses;}

    public int getMoves(){return this.moves;}
    public void setMoves(int moves){this.moves = moves;}


}
