import { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/User";
function NavBar({ csrfToken }) {
  const [buttonPressed, setButtonPressed] = useState(true);
  const navigate = useNavigate();
  const { user, setUser } = useContext(UserContext);

  const startNewGame = () => {
    fetch(`/api/game`, {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrfToken,
      },
      body: JSON.stringify({ player1_id: 1, player2_id: 3 }),
    }).then((r) => {
      if (r.ok) {
        r.json().then((r) => {
          console.log(r);
          navigate("/game", { state: { game: r } });
        });
      }
    });
  };

  const resumeGame = () => {
    fetch(`/api/game/21`, {
      method: "get",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrfToken,
      },
    }).then((r) => {
      if (r.ok) {
        r.json().then((r) => {
          console.log(r);
          navigate(`/game`, { state: { game: r } });
        });
      }
    });
  };

  const handleLogout = () => {
    fetch(`/api/logout`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrfToken,
      },
    }).then((r) => {
      if (r.ok) {
        setUser({ username: false, authorities: false });
      } else {
        console.error("Error fetching data:", r);
      }
    });
  };

  const handleLogin = () => {};
  return (
    <div>
      <button onClick={() => handleLogin()}> Login </button>
      <button onClick={() => handleLogout()}>Logout</button>
      <button onClick={() => startNewGame()}> start game </button>
      <button onClick={() => resumeGame()}> resume game </button>
    </div>
  );
}
export default NavBar;
