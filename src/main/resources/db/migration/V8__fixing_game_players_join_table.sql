DROP TABLE IF EXISTS game_players;

CREATE TABLE game_players (
    player_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    CONSTRAINT pk_game_players PRIMARY KEY (player_id, game_id),
    CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES games(id),
    CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES players(id)
);

CREATE INDEX idx_game_players_game_id ON game_players (game_id);
CREATE INDEX idx_game_players_player_id ON game_players (player_id);