import React, { useState, useEffect} from 'react';

function GamePiece({piece}){


    return(
        <div >
            {piece.color}
        </div>
        )
    }

export default GamePiece