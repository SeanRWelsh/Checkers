CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    user_name VARCHAR(50),
    email VARCHAR(80) NOT NULL,
    wins INT NOT NULL DEFAULT 0,
    losses INT NOT NULL DEFAULT 0,
    moves INT NOT NULL DEFAULT 0

);

CREATE TABLE games (
    id SERIAL PRIMARY KEY,
    player_1 INT NOT NULL,
    player_2 INT NOT NULL,
    status VARCHAR(60),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    FOREIGN KEY (player_1) REFERENCES users(id),
    FOREIGN KEY (player_2) REFERENCES users(id)
);

CREATE TABLE pieces (
    id SERIAL PRIMARY KEY,
    color VARCHAR(5),
    king BOOLEAN,
    row INT,
    col INT,
    game_id INT NOT NULL,
    FOREIGN KEY (game_id) REFERENCES games(id)
    );

CREATE TABLE moves (
    id SERIAL PRIMARY KEY,
    game_id INT,
    user_id INT,
    piece_id INT,
    source_row INT,
    source_column INT,
    destination_row INT,
    destination_column INT,
    moved_at TIMESTAMP,
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (piece_id) REFERENCES pieces(id)
    );