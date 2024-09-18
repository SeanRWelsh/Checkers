CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(80) NOT NULL UNIQUE,
    password varchar(200) NOT NULL,
    roles varchar(80) NOT NULL DEFAULT 'USER',
    wins INT NOT NULL DEFAULT 0,
    losses INT NOT NULL DEFAULT 0,
    moves INT NOT NULL DEFAULT 0
);

CREATE TABLE games (
    id SERIAL PRIMARY KEY,
    status VARCHAR(60),
    start_time TIMESTAMP,
    updated_at TIMESTAMP,
    winner_id INT,
    player_turn INT,
    FOREIGN KEY (winner_id) REFERENCES players(id),
    FOREIGN KEY (player_turn) REFERENCES players(id)
);

 CREATE TABLE game_players(
   player_id INT,
   game_id INT,
   FOREIGN KEY (player_id) REFERENCES players(id),
   FOREIGN KEY (game_id) REFERENCES games(id),
   PRIMARY KEY (player_id, game_id)
 );

CREATE TABLE pieces (
    id SERIAL PRIMARY KEY,
    color VARCHAR(5),
    king BOOLEAN,
    row INT,
    col INT,
    game_id INT NOT NULL,
    player_id INT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
    );

CREATE TABLE moves (
    id SERIAL PRIMARY KEY,
    game_id INT,
    player_id INT,
    piece_id INT,
    source_row INT,
    source_column INT,
    destination_row INT,
    destination_column INT,
    moved_at TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES games(id) ON DELETE CASCADE,
    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
    FOREIGN KEY (piece_id) REFERENCES pieces(id) ON DELETE CASCADE
    );

