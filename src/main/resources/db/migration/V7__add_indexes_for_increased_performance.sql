CREATE INDEX idx_messages_player_id ON messages (player_id);
CREATE INDEX idx_messages_game_id ON messages (game_id);
CREATE INDEX idx_game_players_game_id ON game_players (game_id);
CREATE INDEX idx_game_players_player_id ON game_players (player_id);
CREATE INDEX idx_pieces_game_id ON pieces (game_id);
CREATE INDEX idx_moves_game_id ON moves (game_id);
CREATE INDEX idx_moves_player_id ON moves (player_id);
CREATE INDEX idx_moves_piece_id ON moves (piece_id);