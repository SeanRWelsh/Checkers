import React, { useState, useEffect, useMemo, useContext } from "react";
import { NavLink } from "react-router-dom";
import { useNavigate } from "react-router-dom";

function Home({ csrfToken }) {
  const [buttonPressed, setButtonPressed] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    fetch(`/api/player/32`)
      .then((r) => {
      if (r.ok){
        r.json()
      .then((r) => {
        console.log(r);
      })

      }else{
        console.error("Error fetching data:", r);
      }
      })

  }, []);

  const handleClick = () => {
    fetch(`/api/logout`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-XSRF-TOKEN": csrfToken,
      },
    }).then((r) => {
            if (r.ok){
              console.log(r);
            }else{
              console.error("Error fetching data:", r);
            }
            })
  };

  return (
    <div>
      <button onClick={() => handleClick()}>Logout</button>
    </div>
  );
}
export default Home;
