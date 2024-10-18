import React, { useEffect, useState } from "react";
import GameGrid from "./GameGrid";
import { useLocation } from "react-router-dom";
import PlayerUI from "./PlayerUI";
function Game({ stompClient }) {
  const location = useLocation();
  const { game } = location.state;
  const [gameState, setGame] = useState(game);

  useEffect(() => {
    const subscription = stompClient.subscribe(
      `/topic/${game.id}`,
      (message) => {
        const parsedData = JSON.parse(message.body);
        setGame(parsedData);
        console.log("parsedData");
        console.log(parsedData);
      },
    );

    return () => {
      subscription.unsubscribe(`/topic/${game.id}`);
    };
  }, []);

  return (
    <div id="gamePage">
      <PlayerUI
        gamePlayers={gameState.players}
        playerTurn={gameState.player_turn}
      />
      <div className="boardContainer">
        <GameGrid game={gameState} stompClient={stompClient} />
      </div>
    </div>
  );
}
export default Game;
