package com.checkers.controllers;

import com.checkers.dtos.PlayerDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.checkers.entities.Player;
import com.checkers.repositories.PlayerRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController

@RequestMapping("/player")
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> signUp(@Valid @RequestBody Player player){
        playerRepository.save((player));
        return ResponseEntity.status(HttpStatus.CREATED).body(new PlayerDTO(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> getPlayer(@PathVariable Long id){
        try {
            Optional<Player> playerToDelete = playerRepository.findById(id);
            playerRepository.delete(playerToDelete.orElseThrow(IllegalArgumentException::new));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player with id " + id + " not found.", e);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id,  @RequestBody PlayerDTO player) {
        Player updatedPlayer = playerRepository.patchPlayer(id, player);
        return ResponseEntity.ok(new PlayerDTO(updatedPlayer));

    }


}

