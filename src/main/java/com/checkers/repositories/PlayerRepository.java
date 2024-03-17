package com.checkers.repositories;

import org.springframework.data.repository.CrudRepository;
import com.checkers.entities.Player;

public interface PlayerRepository extends CrudRepository<Player, Long> {

}
