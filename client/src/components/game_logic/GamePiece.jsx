import React, { useState, useEffect } from "react";
import "./CheckerPiece.css";

function GamePiece({ piece, pieceToMove, selectedPiece }) {
  const { color, king } = piece;
  let className = `checker-piece ${color.toLowerCase()}`;
  if (selectedPiece && selectedPiece.id === piece.id)
    className = className + " selected";

  return (
    <div className={className} onClick={(e) => pieceToMove(e, piece)}>
      <div className="inner-circle"></div>
      <div className="small-circle"></div>
      {king && <div className="king-indicator">K</div>}
    </div>
  );
}

export default GamePiece;
