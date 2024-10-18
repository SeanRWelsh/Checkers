import { Routes, Route } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import "./styles/App.css";
import Game from "./components/game_logic/Game";
import Home from "./components/Home";
import { Client } from "@stomp/stompjs";

import NavBar from "./components/NavBar";
import { UserContext } from "./context/User";

function App() {
  const { user, setUser } = useContext(UserContext);

  useEffect(() => {
    // Fetch user information from Spring Security's endpoint
    fetch("/api/player")
      .then((r) => r.json())
      .then((r) => setUser({ username: r.username, authorities: false }))
      .catch((err) => console.log(err));
  }, []);

  const [connected, setConnected] = useState(false);
  const [stompClient, setStompClient] = useState(null);
  useEffect(() => {
    if (user.username) {
      const client = new Client({
        brokerURL: "/ws/ws", //url is this way to utilize vite proxy in order to send the web socket from the same origin
        // as the rest of the site. This will allow for the JSESSIONID to be attached with
        //websocket requests so that websocket requests can utilize it for authorization
        debug: function (str) {
          //remove for prod
          console.log(str);
        },
        reconnectDelay: 5000,
        heartbeatIncoming: 4000,
        heartbeatOutgoing: 4000,
        onConnect: (frame) => {
          setConnected(true);
          console.log("Connected: " + frame);
          client.subscribe("/user/queue/errors", (message) => {
            alert(message.body);
          });
        },
        onWebSocketError: (error) => {
          console.error("Error with websocket", error);
        },
        onStompError: (frame) => {
          console.error("Broker reported error: " + frame.headers["message"]);
          console.error("Additional details: " + frame.body);
        },
      });

      client.activate(); // Activate the client
      setStompClient(client);

      // Cleanup function to deactivate the client when the component unmounts
      return () => {
        client.deactivate();
        setConnected(false);
        setStompClient(null);
        console.log("Disconnected");
      };
    }
  }, [user.username]);

  return (
    <div className="App">
      <NavBar stompClient={stompClient} />
      <Routes>
        <Route path="/game" element={<Game stompClient={stompClient} />} />
        <Route path="/" element={<Home />} />
      </Routes>
    </div>
  );
}

export default App;
