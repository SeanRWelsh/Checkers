import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/User.jsx";
import "../styles/Login.css";

function Login({ csrfToken, setIsLogin }) {
  const { setUser } = useContext(UserContext);
  const [errors, setErrors] = useState({});
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });
  const navigate = useNavigate();

  const toggleLogin = ({ target }) => {
    if (target.id === "login") setIsLogin(false);
  };

  const handleLogin = (e) => {
    e.preventDefault();
    fetch(`/api/player/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "X-CSRF-TOKEN": csrfToken,
      },
      body: JSON.stringify(formData),
    }).then((res) => {
      if (res.ok) {
        res.json().then((res) => {
          setUser({ username: res.user, authorities: false });
          setIsLogin(false);
          setFormData({ username: "", password: "" });
        });
        navigate("/");
      } else {
        res.json().then((res) => setErrors({ error: res.error }));
      }
    });
  };
  const handleChange = (e) => {
    let key = e.target.name;
    let value = e.target.value;
    setFormData({ ...formData, [key]: value });
  };

  return (
    <div id="login" onClick={toggleLogin}>
      <div className="loginContainer">
        <h1>Welcome Back!</h1>
        <h3>Login</h3>
        {errors && <h3>{errors.error}</h3>}
        <form onSubmit={handleLogin}>
          <div>
            <label htmlFor="username">Username:</label>
            <input
              type="text"
              id="username"
              name="username"
              value={formData.username}
              onChange={(e) => handleChange(e)}
              required
            />
          </div>
          <div>
            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              name="password"
              value={formData.password}
              onChange={(e) => handleChange(e)}
              required
            />
          </div>
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
}

export default Login;
