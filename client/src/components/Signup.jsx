import React, { useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/User.jsx";
import "../styles/Login.css";

function Signup({ csrfToken, setIsSignup }) {
  const { setUser } = useContext(UserContext);
  const [errors, setErrors] = useState({});
  const [formData, setFormData] = useState({
    firstName: "",
    username: "",
    email: "",
    password: "",
    confirmPassword: "",
  });
  const navigate = useNavigate();

  const toggleLogin = ({ target }) => {
    if (target.id === "login") setIsSignup(false);
  };

  const handleLogin = (e) => {
    e.preventDefault();
    fetch(`/api/login`, {
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
          setIsSignup(false);
          setFormData({
            firstName: "",
            username: "",
            email: "",
            password: "",
            confirmPassword: "",
          });
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
        <h1>Create an account!</h1>
        <h3>Signup</h3>
        {errors && <h3>{errors.error}</h3>}
        <form onSubmit={handleLogin}>
          <div>
            <label htmlFor="firstName">firstName:</label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              value={formData.firstName}
              onChange={(e) => handleChange(e)}
              required
            />
          </div>
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
            <label htmlFor="email">email:</label>
            <input
              type="text"
              id="email"
              name="email"
              value={formData.email}
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
          <div>
            <label htmlFor="confirmPassword">confirmPassword:</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={(e) => handleChange(e)}
              required
            />
          </div>
          <button type="submit">Signup</button>
        </form>
      </div>
    </div>
  );
}

export default Signup;
