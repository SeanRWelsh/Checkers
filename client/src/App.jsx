import { Routes, Route } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import "./styles/App.css";
import Game from "./components/game_logic/Game";
import Home from "./components/Home";

import NavBar from "./components/NavBar";
import { UserContext } from "./context/User";

function App() {
  const [csrfToken, setCsrfToken] = useState("");
  useEffect(() => {
    fetch("/api/csrf")
      .then((r) => r.json())
      .then((r) => {
        setCsrfToken(r.token);
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);

  const { setUser } = useContext(UserContext);
  useEffect(() => {
    // Fetch user information from Spring Security's endpoint
    fetch("/api/player")
      .then((r) => r.json())
      .then((r) => setUser({ username: r.username, authorities: false }))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="App">
      <NavBar csrfToken={csrfToken} />
      <Routes>
        <Route path="/game" element={<Game csrfToken={csrfToken} />} />
        <Route path="/" element={<Home csrfToken={csrfToken} />} />
      </Routes>
    </div>
  );
}

export default App;
