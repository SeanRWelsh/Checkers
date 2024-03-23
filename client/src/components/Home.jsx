import React, { useState, useEffect, useMemo, useContext } from "react";
import { NavLink } from "react-router-dom";
function Home() {
const [buttonPressed, setButtonPressed] = useState(true);

useEffect(() =>{
   fetch(`/api/player/32`)
      .then((r) => r.json())
      .then((trails) => {
        console.log(trails)
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);


  return <div>
  Home

  </div>;
}
export default Home;
