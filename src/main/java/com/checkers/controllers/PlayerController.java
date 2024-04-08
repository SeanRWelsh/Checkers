package com.checkers.controllers;


import com.checkers.dtos.PlayerHomeDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.checkers.models.Player;
import com.checkers.repositories.PlayerRepository;
import org.springframework.web.server.ResponseStatusException;

@RestController

@RequestMapping("/player")
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerHomeDTO> getPlayer(@PathVariable Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Player with id " + id + " not found."));
        return ResponseEntity.ok(new PlayerHomeDTO(player));
    }

    @PostMapping
    public ResponseEntity<PlayerHomeDTO> signUp(@Valid @RequestBody Player player) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        player.setPassword(encoder.encode(player.getPassword()));
        Player savedPlayer = playerRepository.save(player);
        return ResponseEntity.status(HttpStatus.CREATED).body(new PlayerHomeDTO(savedPlayer));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PlayerHomeDTO> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Player updatedPlayer = playerRepository.patchPlayer(id, player);
        return ResponseEntity.ok(new PlayerHomeDTO(updatedPlayer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        Player playerToDelete = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Player with id " + id + " not found."));
        playerRepository.delete(playerToDelete);
        return ResponseEntity.noContent().build();
    }

}
