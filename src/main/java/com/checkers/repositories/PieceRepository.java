package com.checkers.repositories;

import com.checkers.models.Piece;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceRepository extends JpaRepository<Piece, Long> {
}
