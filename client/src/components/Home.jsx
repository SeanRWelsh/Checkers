import React, { useState, useEffect, useMemo, useContext } from "react";
function Home() {

useEffect(() =>{
   fetch(`http://localhost:8080/player/32`)
      .then((r) => r.json())
      .then((trails) => {
        console.log(trails)
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);
  return <div>

  </div>;
}
export default Home;
