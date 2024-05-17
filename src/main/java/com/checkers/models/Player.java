package com.checkers.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
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
    @Column(name = "user_name")
    @Size(max = 50)
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String roles;
    @Email
    @NotNull
    @Size(max = 80)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "game_players",
    joinColumns = @JoinColumn(name = "player_id"),
    inverseJoinColumns = @JoinColumn(name= "game_id"))
    private Set<Game> gamesPlayed = new HashSet<>();


    public Player() {
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

    public Set<Game> getGamesPlayed() {
        return this.gamesPlayed;
    }

    public void setGamesPlayed(Set<Game> gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void addGame(Game game) {
            gamesPlayed.add(game);
            game.getGamePlayers().add(this);
    }

    public void removeGame(Game game) {
        gamesPlayed.remove(game);
        game.getGamePlayers().remove(this);
    }

    @Override
    public String toString() {
        return "player username is " + this.username + " player has " + this.wins + " wins" +
                " and has played in " + this.gamesPlayed.size();
    }

}
