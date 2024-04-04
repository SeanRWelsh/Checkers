ALTER TABLE games DROP CONSTRAINT IF EXISTS games_player_1_fkey;
ALTER TABLE games DROP CONSTRAINT IF EXISTS games_player_2_fkey;
ALTER TABLE games DROP COLUMN IF EXISTS player_1;
ALTER TABLE games DROP COLUMN IF EXISTS player_2;

CREATE TABLE game_players (
  id SERIAL PRIMARY KEY,
  game_id BIGINT NOT NULL,
  player_id BIGINT NOT NULL,
  CONSTRAINT fk_game_id FOREIGN KEY (game_id) REFERENCES games(id),
  CONSTRAINT fk_player_id FOREIGN KEY (player_id) REFERENCES players(id)
);