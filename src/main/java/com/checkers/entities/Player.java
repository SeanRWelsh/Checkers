package com.checkers.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter a name.")
    @Size(max = 50)
    private String name;
    @Column(name = "user_name")
    @Size(max = 50)
    private String userName;
    @Email
    @Size(max = 80)
    private String email;
    @NotNull
    @Positive
    private int wins;
    @NotNull
    @Positive
    private int losses;
    @NotNull
    @Positive
    private int moves;

    public Player(){}

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
