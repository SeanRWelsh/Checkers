import React, { useState, useEffect, useMemo, useContext } from "react";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/User";



function Home({ csrfToken }) {
  const [buttonPressed, setButtonPressed] = useState(true);
  const navigate = useNavigate();
  const { user, setUser } = useContext(UserContext);


  const startNewGame = () =>{
      fetch(`/api/game`,{
          method: "post",
          headers: {
              "Content-Type": "application/json",
              "X-XSRF-TOKEN": csrfToken,
          },
          body: JSON.stringify({player1_id: 32, player2_id: 33})
      }).then(r =>{
          if(r.ok){
              r.json()
              .then( r =>{
                  console.log(r);
                  navigate('/game', { state: {game: r}})
              })
          }
         })
  }

  const handleClick = () => {
    fetch(`/api/logout`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-XSRF-TOKEN": csrfToken,
      },
    }).then((r) => {
            if (r.ok){
              setUser({ username: false, authorities: false })
            }else{
              console.error("Error fetching data:", r);
            }
            })
  };

  return (
    <div>
      <button onClick={() => handleClick()}>Logout</button>
      <button onClick={() => startNewGame()}> start game </button>
    </div>
  );
}
export default Home;
