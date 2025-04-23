import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const [role, setRole] = useState("Student");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    if (!email || !password) {
      setError("Please enter email and password.");
      setSuccess("");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/StudentLoanServices/applicant/login", {
        email,
        password,
        role,
      });

      // Save token and role to localStorage
      localStorage.setItem("token", response.data.token);
      localStorage.setItem("role", role);

      setError("");
      setSuccess("Login successful!");

      setTimeout(() => {
        if (role === "Student") {
          navigate("/student/dashboard");
        } else if (role === "BankAdmin") {
          navigate("/admin/dashboard");
        } else if (role === "BankRep") {
          navigate("/rep/dashboard");
        }
      }, 1000);

    } catch (err) {
      setSuccess("");
      setError(
        err.response?.data?.message || "Invalid credentials or server error."
      );
    }
  };

  return (
    <div className="login-page">
      <div className="login-card">
        <h2>Student Loan Portal</h2>
        <p className="login-subtitle">Login to manage your student loans</p>

        {error && <div className="error-box">{error}</div>}
        {success && <div className="success-box">{success}</div>}

        <form onSubmit={handleLogin} className="login-form">
          <label>Login As</label>
          <select
            value={role}
            onChange={(e) => setRole(e.target.value)}
            className="dropdown"
          >
            <option value="Student">Student</option>
            <option value="BankAdmin">Bank Admin</option>
            <option value="BankRep">Bank Representative</option>
          </select>

          <label>Email</label>
          <input
            type="email"
            placeholder="user@example.com"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <label>Password</label>
          <input
            type="password"
            placeholder="••••••••"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button type="submit">Login</button>
        </form>

        <div className="login-footer">
          <a href="#">Forgot Password?</a>
          {role === "Student" && (
            <>
              <span> | </span>
              <Link to="/register">Create an Account</Link>
            </>
          )}
        </div>
      </div>
    </div>
  );
}

export default Login;
