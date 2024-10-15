import { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/User";
import Login from "./Login";
import Signup from "./Signup";
function NavBar({ csrfToken }) {
  const [isSignup, setIsSignup] = useState(false);
  const [isLogin, setIsLogin] = useState(false);
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
          navigate("/game", { state: { game: r } });
        });
      }
    });
  };

  const resumeGame = () => {
    fetch(`/api/game/resume`, {
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
        navigate("/");
        window.location.reload();
      } else {
        console.error("Error fetching data:", r);
      }
    });
  };

  return (
    <header>
      <h1> Checkers </h1>
      {isLogin && <Login csrfToken={csrfToken} setIsLogin={setIsLogin} />}
      {isSignup && <Signup csrfToken={csrfToken} setIsSignup={setIsSignup} />}
      {!user.username && (
        <div className="navButtons">
          <button value="Login" onClick={() => setIsLogin(true)}>
            Login
          </button>
          <button value="Signup" onClick={() => setIsSignup(true)}>
            Signup
          </button>
        </div>
      )}
      {user.username && (
        <div className="navButtons">
          <button onClick={() => startNewGame()}> start game </button>
          <button onClick={() => resumeGame()}> resume game </button>
          <button onClick={() => navigate(`/`)}> home </button>
          <button onClick={() => handleLogout()}>Logout</button>
        </div>
      )}
    </header>
  );
}
export default NavBar;
