import React, { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import GameGrid from './GameGrid';
import {useLocation} from "react-router-dom";
function Game({csrfToken}) {
    const [connected, setConnected] = useState(false);
    const [stompClient, setStompClient] = useState(null);
    const location = useLocation();
    const { game } = location.state;
    const [gameState, setGame] = useState(game)
    const gameId = game.id;

        useEffect(() => {
            const client = new Client({
                brokerURL: 'ws://192.168.0.107:8080/ws',
//                 connectHeaders:{
//                     'X-CSRF-TOKEN': csrfToken},
                onConnect: (frame) => {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    client.subscribe(`/topic/${gameId}`, (message) => {
                        const parsedData = JSON.parse(message.body);
                        setGame(parsedData)
                        console.log(parsedData);
                        // Handle the incoming message
                    });
                },
                onWebSocketError: (error) => {
                    console.error('Error with websocket', error);
                },
                onStompError: (frame) => {
                    console.error('Broker reported error: ' + frame.headers['message']);
                    console.error('Additional details: ' + frame.body);
                },
            });

            client.activate(); // Activate the client
            setStompClient(client);

            // Cleanup function to deactivate the client when the component unmounts
            return () => {
                client.deactivate();
                setConnected(false);
                console.log("Disconnected");
            };
        }, [gameId]);

    return (
        <div  className="container">
            <GameGrid game={gameState} stompClient={stompClient}/>
        </div>
    );
}

export default Game;