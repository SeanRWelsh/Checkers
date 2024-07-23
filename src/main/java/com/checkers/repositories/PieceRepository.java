package com.checkers.repositories;

import com.checkers.models.Game;
import com.checkers.models.Piece;
import com.checkers.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PieceRepository extends JpaRepository<Piece, Long> {

    @Transactional
    default void movePiece(Long id, int row, int column) {
        Optional<Piece> optionalPiece = findById(id);
        if (optionalPiece.isPresent()) {
            Piece pieceToUpdate = optionalPiece.get();
            pieceToUpdate.setColumn(column);
            pieceToUpdate.setRow(row);
        }
    }

}
