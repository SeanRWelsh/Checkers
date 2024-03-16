package com.checkers.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(max = 50)
    private String name;
    @Column(name = "user_name")
    @Size(max = 50)
    private String userName;
    @Email
    @NotNull
    @Size(max = 80)
    private String email;
    @NotNull
    private int wins;
    @NotNull
    private int losses;
    @NotNull
    private int moves;

    public User(){}

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
