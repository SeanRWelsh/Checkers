ALTER TABLE games ADD COLUMN player_turn BIGINT;
ALTER TABLE games ADD CONSTRAINT fk_player_turn FOREIGN KEY (player_turn) REFERENCES players(id);

CREATE INDEX idx_games_player_turn ON games (player_turn);