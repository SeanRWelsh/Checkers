package com.checkers.repositories;

import com.checkers.models.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {

    // need to fix query sending back all active games only need to send back the
    // first one
    @Query("""
            select g
            from Game g
            join g.gamePlayers p
            where g.status = 'IN_PROGRESS' and p.id = ?1
            order by g.startTime desc
            """)
    Long findMostRecentGame(Long userId);
    // Should probably just return the game directly instead of an id and then do
    // another query for the game.
}
