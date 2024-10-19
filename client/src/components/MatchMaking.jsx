import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function MatchMaking({ stompClient }) {
  const navigate = useNavigate();
  useEffect(() => {
    if (stompClient && stompClient.connected) {
      const startGameSubscription = stompClient.subscribe(
        `/user/queue/startGame`,
        (message) => {
          const parsedData = JSON.parse(message.body);
          console.log(parsedData);
          if (parsedData !== "waiting for other players")
            navigate(`/game`, { state: { game: parsedData } });
        },
      );

      stompClient.publish({
        destination: `/app/startGame`,
      });
      return () => {
        startGameSubscription.unsubscribe();
      };
    } else {
      console.error("STOMP client is not connected.");
    }
  }, []);

  return (
    <div id="login">
      <div className="loginContainer">
        Searching for other players please wait...
      </div>
    </div>
  );
}
export default MatchMaking;
