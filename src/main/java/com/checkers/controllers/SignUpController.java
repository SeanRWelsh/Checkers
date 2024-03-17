package com.checkers.controllers;

import com.checkers.dtos.PlayerDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.checkers.entities.Player;
import com.checkers.repositories.PlayerRepository;

@RestController
@RequestMapping("/sign-up")
public class SignUpController {
    private final PlayerRepository playerRepository;

    public SignUpController(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @PostMapping
    public ResponseEntity<PlayerDTO> signUp(@Valid @RequestBody Player player){

        playerRepository.save((player));
        return ResponseEntity.status(HttpStatus.CREATED).body(new PlayerDTO(player));
    }


}