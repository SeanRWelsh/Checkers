ALTER TABLE pieces ADD COLUMN player_id BIGINT NOT NULL;
ALTER TABLE pieces ADD CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES players(id);
CREATE INDEX idx_pieces_player_id ON pieces (player_id);

ALTER TABLE games ADD COLUMN winner_id BIGINT NULL;
ALTER TABLE games ADD CONSTRAINT fk_winner_id FOREIGN KEY (winner_id) REFERENCES players(id);
CREATE INDEX idx_games_winner_id ON games (winner_id);