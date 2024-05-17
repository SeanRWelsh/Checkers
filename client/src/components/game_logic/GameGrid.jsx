import React, { useState, useEffect} from 'react';
import GamePiece from "./GamePiece";

function GameGrid({game}){
    const [grid, setGrid] = useState([])


useEffect(() => {
    const initialGridSetUp = [];
    let count = 0;

    for (let i = 0; i < 8; i++) {
        const row = [];
        for (let j = 0; j < 8; j++) {
            const isTan = (i % 2 === 0 && j % 2 === 0) || (i % 2 !== 0 && j % 2 !== 0);
            let className = isTan ? "tan" : "brown";
            if(j === 7) className = `${className} grid-right`;
            if(i === 7) className = `${className} grid-bottom`;
            let piece = game.pieces.find(element => element.row === i && element.column === j)
            row.push(<div key={count} className={`${className} grid-box `}>
                {piece ? <GamePiece piece={piece} /> : null}
                </div>);
            count++;
        }
        initialGridSetUp.push(row);
    }
    setGrid(initialGridSetUp);
}, []);

    return(
        <div className="wrapper">
            {grid}
        </div>
        )
    }

export default GameGrid