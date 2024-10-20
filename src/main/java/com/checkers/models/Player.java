package com.checkers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name cannot be blank.")
    @Size(max = 50)
    private String name;

    @Column(name = "username", unique = true)
    @Size(max = 50)
    private String username;

    @NotNull
    private String password;

    @NotNull
    @ColumnDefault("USER")
    private String roles;

    @Email
    @NotNull
    @Size(max = 80)
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @PositiveOrZero
    @ColumnDefault("0")
    private int wins;

    @NotNull
    @PositiveOrZero
    @ColumnDefault("0")
    private int losses;

    @NotNull
    @PositiveOrZero
    @ColumnDefault("0")
    private int moves;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GamePlayer> gamePlayers = new ArrayList<>();

    public Player() {
        this.roles = "USER"; // Set default role here
        this.wins = 0; // Default value
        this.losses = 0; // Default value
        this.moves = 0; // Default value
    }

    public Long getId() {
        return id;
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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GamePlayer> getGamePlayers() {
        return this.gamePlayers;
    }

    public void setGamePlayers(List<GamePlayer> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public void addGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayers.add(gamePlayer);
        gamePlayer.setPlayer(this); // Set the player in the GamePlayer
    }

    public void removeGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayers.remove(gamePlayer);
        gamePlayer.setPlayer(null); // Unset the player in the GamePlayer
    }

    @Override
    public String toString() {
        return "player id is " + this.id + " player username is " + this.username + " player has " + this.wins + " wins"
                +
                " and has played in " + this.gamePlayers.size() + " games.";
    }
}