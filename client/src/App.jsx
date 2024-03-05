import { Routes, Route } from "react-router-dom";
import "./App.css";
import Game from "./components/Game";
import Home from "./components/Home";

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/game" element={<Game />} />
        <Route path="/" element={<Home />} />
      </Routes>
    </div>
  );
}

export default App;
