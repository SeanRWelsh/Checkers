import React, { useContext, useState, useEffect } from "react";



function Login() {
const [formData, setFormData] = useState({username:"", password:"", _csrf:""})
const [csrfToken, setCsrfToken] = useState('')

useEffect(()=>{
  fetch('/api/csrf')
  .then(r => r.json())
  .then( r => {
  console.log(r)
    setCsrfToken(r.token)
//        setFormData({_csrf:r.token})

  })
  .catch((error) => {
//   console.error("Error fetching data:", error);
  });
  },[]);
  console.log(csrfToken)




  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(`/api/login`, {
      method: "POST",
      headers: {
      "Content-Type": "application/json",
      "X-XSRF-TOKEN": csrfToken
      },
      body: JSON.stringify(formData),
    }).then((res) => {
      if (res.ok) {
    console.log(res)
//         res.json().then((user) => setUser(user));
//         setErrors([]);
//         handleClose();
      } else {
      console.log("WHAT THE FUCK")
//         res.json().then((err) => setErrors(err.errors));
      }
    });

    // Here you can perform any action you want with the username and password, such as sending them to a server for authentication


  };
    const handleChange = (e) => {
    console.log(e.target.name)
      let key = e.target.name;
      let value = e.target.value;
      setFormData({ ...formData, [key]: value });
    };

  return (
    <div>
      <h2>Login Form</h2>
      <form onSubmit={handleSubmit}>
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
  );
}

export default Login;
