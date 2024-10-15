import "./CheckerPiece.css";
function PlayerUI({ gamePlayers, playerTurn }) {
  console.log(playerTurn);
  return (
    <div className="playerDataContainer">
      <div className="playerContainer">
        {playerTurn === gamePlayers[0].username ? (
          <div id="arrow">⮞</div>
        ) : (
          <div></div>
        )}

        <div className="checker-piece red">
          <div className="inner-circle"></div>
          <div className="small-circle"></div>
        </div>
        <h1>{gamePlayers[0].username}</h1>
      </div>
      <div className="playerContainer">
        {playerTurn === gamePlayers[1].username ? (
          <div id="arrow">⮞</div>
        ) : (
          <div></div>
        )}
        <div className="checker-piece black">
          <div className="inner-circle"></div>
          <div className="small-circle"></div>
        </div>
        <h1>{gamePlayers[1].username}</h1>
      </div>
    </div>
  );
}
export default PlayerUI;
