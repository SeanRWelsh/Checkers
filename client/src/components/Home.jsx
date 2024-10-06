function Home() {
  return (
    <div className="instructions">
      <h1>How to Play Checkers</h1>
      <div className="instructionsBox">
        <h2>Objective:</h2>
        <p>
          The goal is to capture all of your opponent's pieces or block them so
          they cannot make any moves.
        </p>
      </div>

      <h2>Game Setup:</h2>
      <div className="instructionsBox">
        <h3>Board:</h3>
        <p>
          Checkers is played on an 8x8 square board (64 squares) with
          alternating dark and light squares.
        </p>
      </div>

      <div className="instructionsBox">
        <h3>Pieces:</h3>
        <p>
          Each player starts with 12 pieces. One player uses dark pieces (black
          or red), and the other uses light pieces (white or yellow). The pieces
          are set up on the first three rows of each player's side, occupying
          only the dark squares.
        </p>
      </div>

      <div className="instructionsBox">
        <h2>Starting the Game:</h2>

        <p>
          Players decide who goes first (typically, the player with the dark
          pieces). Players take turns moving one piece at a time.
        </p>
      </div>

      <h2>Basic Movement:</h2>
      <div className="instructionsBox">
        <h3>Moving Pieces:</h3>
        <p>
          Each piece can move diagonally forward by one square into an
          unoccupied dark square.
        </p>
      </div>

      <div className="instructionsBox">
        <h3>Capturing Pieces:</h3>
        <p>
          If an opponent's piece is on an adjacent diagonal square and there is
          an empty square immediately beyond it, you may jump over the
          opponent's piece and land on the empty square. The opponent's piece is
          then captured and removed from the board.
        </p>
      </div>

      <div className="instructionsBox">
        <h3>Multiple Jumps:</h3>
        <p>
          If after capturing a piece, the same piece can make another capture in
          the same turn, it must continue jumping until no more captures are
          possible.
        </p>
      </div>

      <h2>King Pieces:</h2>
      <div className="instructionsBox">
        <h3>Becoming a King:</h3>
        <p>
          When a piece reaches the opponent's back row (the last row on the
          opposite side), it is promoted to a King.
        </p>
      </div>

      <div className="instructionsBox">
        <h3>King Movement:</h3>
        <p>
          Kings can move diagonally forward and backward, making them more
          versatile than regular pieces.
        </p>
      </div>

      <div className="instructionsBox">
        <h3>Crowning:</h3>
        <p>
          Place another piece of the same color on top of the piece to signify
          its King status.
        </p>
      </div>

      <h2>Winning the Game:</h2>
      <p>
        You win if your opponent has no remaining pieces or if all of their
        pieces are blocked and unable to move.
      </p>
    </div>
  );
}
export default Home;
