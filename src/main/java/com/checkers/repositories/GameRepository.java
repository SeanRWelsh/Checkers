package com.checkers.repositories;

import com.checkers.models.Game;
import com.checkers.models.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

}
