import React, { useState, useEffect} from 'react';
import GamePiece from "./GamePiece";

function GameGrid({game, stompClient}){
    const [grid, setGrid] = useState([])
    const [selectedPiece, setSelectedPiece] = useState(null);


    const pieceToMove = (e, piece) =>{
        e.stopPropagation();
        if (selectedPiece && selectedPiece.id === piece.id){
            setSelectedPiece(null);
        }else{
            setSelectedPiece(piece)
        }
    }
    const moveTo = (moveLocation) =>{
        if(!selectedPiece){
            console.log("please select a piece to move");
            return;
        }

        if(stompClient && stompClient.connected){
        console.log(moveLocation)
            stompClient.publish({
                destination: `/app/game/${game.id}`,
                body: JSON.stringify({'sourceRow': selectedPiece.row, 'sourceColumn': selectedPiece.column ,
                'destinationRow': moveLocation.row , 'destinationColumn': moveLocation.column , 'gameId': game.id,
                'pieceId': selectedPiece.id, 'playerId': selectedPiece.player.id})
            });
        }else {
            console.error('STOMP client is not connected.')
        }
    }



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
            row.push(<div key={count} className={`${className} grid-box `} onClick ={()=> moveTo({row: i, column: j})}>
                {piece ? <GamePiece piece={piece} pieceToMove={pieceToMove} selectedPiece={selectedPiece}/> : null}
                </div>);
            count++;
        }
        initialGridSetUp.push(row);
    }
    setGrid(initialGridSetUp);
}, [selectedPiece, game]);

    return(
        <div className="wrapper">
            {grid}
        </div>
        )
    }

export default GameGrid