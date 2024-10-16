package com.checkers.repositories;

import com.checkers.models.Game;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("""
            select g
            from Game g
            join g.gamePlayers p
            where g.status = 'IN_PROGRESS' and p.id = ?1
            order by g.startTime desc
            """)
    List<Game> findMostRecentGame(Long userId, Pageable pageable);

    // leaving this in just incase I decide to move back to a native query
    // @Query(value = """
    // select game_id
    // from game_players gp
    // join games g on gp.game_id = g.id where g.winner_id is null and player_id =
    // ?1
    // order by g.start_time desc
    // limit 1
    // """,
    // nativeQuery = true)
    // Long findMostRecentGame(Long userId);
    // Should probably just return the game directly instead of an id and then do
    // another query for the game.
}
