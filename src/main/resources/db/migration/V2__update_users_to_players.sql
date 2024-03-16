ALTER TABLE users RENAME TO players;

ALTER TABLE games
DROP CONSTRAINT games_player_1_fkey,
DROP CONSTRAINT games_player_2_fkey,
ADD CONSTRAINT games_player_1_fkey FOREIGN KEY (player_1) REFERENCES players(id),
ADD CONSTRAINT games_player_2_fkey FOREIGN KEY (player_2) REFERENCES players(id);

ALTER TABLE moves
RENAME COLUMN user_id TO player_id;

ALTER TABLE moves
DROP CONSTRAINT moves_user_id_fkey,
ADD CONSTRAINT moves_player_id FOREIGN KEY (player_id) REFERENCES players(id);