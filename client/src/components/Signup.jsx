import React, { useContext, useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../context/User.jsx";
import "../styles/Login.css";

function Signup({ csrfToken, setIsSignup }) {
  const { setUser } = useContext(UserContext);
  const [errors, setErrors] = useState({});
  console.log(errors);

  const [formData, setFormData] = useState({
    name: "",
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
    if (formData.password !== formData.confirmPassword)
      return setErrors({ password: "passwords do not match" });
    fetch(`/api/player/signup`, {
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
            name: "",
            username: "",
            email: "",
            password: "",
            confirmPassword: "",
          });
        });
        navigate("/");
      } else {
        res.json().then((res) => setErrors(res));
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
        <form onSubmit={handleLogin}>
          <div className="inputBox">
            <label htmlFor="firstName">first Name:</label>
            <input
              type="text"
              id="firstName"
              name="name"
              value={formData.name}
              onChange={(e) => handleChange(e)}
              required
            />
          </div>

          <div className="inputBox">
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
          {errors.username && <div className="error">{errors.username}</div>}
          <div className="inputBox">
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
          {errors.email && <div className="error">{errors.email}</div>}
          <div className="inputBox">
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
          {errors.password && <div className="error">{errors.password}</div>}
          <div className="inputBox">
            <label htmlFor="confirmPassword">confirm Password:</label>
            <input
              type="password"
              id="confirmPassword"
              name="confirmPassword"
              value={formData.confirmPassword}
              onChange={(e) => handleChange(e)}
              required
            />
          </div>
          {errors.password && <div className="error">{errors.password}</div>}

          <button type="submit">Signup</button>
        </form>
      </div>
    </div>
  );
}

export default Signup;
