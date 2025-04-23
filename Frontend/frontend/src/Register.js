import React, { useState } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
import "./Register.css";

function Register() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    address: "",
    educationDetails: "",
    membershipType: "Regular",
    email: "",
    phoneNumber: "",
    password: "",
    confirmPassword: "",
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleRegister = async (e) => {
    e.preventDefault();

    const {
      firstName,
      lastName,
      dateOfBirth,
      address,
      educationDetails,
      membershipType,
      email,
      phoneNumber,
      password,
      confirmPassword,
    } = formData;

    if (
      !firstName ||
      !lastName ||
      !dateOfBirth ||
      !address ||
      !educationDetails ||
      !membershipType ||
      !email ||
      !phoneNumber ||
      !password ||
      !confirmPassword
    ) {
      setError("All fields are required.");
      setSuccess("");
      return;
    }

    if (password !== confirmPassword) {
      setError("Passwords do not match.");
      setSuccess("");
      return;
    }

    try {
      // Format date as dd-MM-yyyy for backend
      const formatDate = (input) => {
      const [year, month, day] = input.split("-");
      return `${day}-${month}-${year}`;
    };
  
  const registrationData = {
    ...formData,
    dateOfBirth: formatDate(formData.dateOfBirth),
  };
  delete registrationData.confirmPassword;
  

      await axios.post("http://localhost:8080/StudentLoanServices/applicant/register", registrationData);

      setError("");
      setSuccess("Registration successful! Redirecting to login...");

      // Redirect to login after delay
      setTimeout(() => {
        navigate("/");
      }, 1500);
    } catch (err) {
      setSuccess("");
      setError(
        err.response?.data?.message || "Registration failed. Try again."
      );
    }
  };

  return (
    <div className="login-page">
      <div className="page-title">
        <h1>Student Registration</h1>
        <p>Register as a student to apply for a loan</p>
      </div>

      <div className="login-card">
        <h2>Create Student Account</h2>
        <p className="login-subtitle">Fill in your details below</p>

        {error && <div className="error-box">{error}</div>}
        {success && <div className="success-box">{success}</div>}

        <form onSubmit={handleRegister} className="login-form">
          <label>First Name</label>
          <input type="text" name="firstName" value={formData.firstName} onChange={handleChange} />

          <label>Last Name</label>
          <input type="text" name="lastName" value={formData.lastName} onChange={handleChange} />

          <label>Date of Birth</label>
          <input type="date" name="dateOfBirth" value={formData.dateOfBirth} onChange={handleChange} />

          <label>Address</label>
          <input type="text" name="address" value={formData.address} onChange={handleChange} />

          <label>Education Details</label>
          <input type="text" name="educationDetails" value={formData.educationDetails} onChange={handleChange} />

          <label>Membership Type</label>
          <select name="membershipType" value={formData.membershipType} onChange={handleChange} className="dropdown">
            <option value="Regular">Regular</option>
            <option value="Premium">Premium</option>
          </select>

          <label>Email</label>
          <input type="email" name="email" value={formData.email} onChange={handleChange} />

          <label>Phone Number</label>
          <input type="tel" name="phoneNumber" value={formData.phoneNumber} onChange={handleChange} />

          <label>Password</label>
          <input type="password" name="password" value={formData.password} onChange={handleChange} />

          <label>Confirm Password</label>
          <input type="password" name="confirmPassword" value={formData.confirmPassword} onChange={handleChange} />

          <button type="submit">Register</button>
        </form>

        <div className="login-footer">
          <p>
            Already have an account? <Link to="/">Login</Link>
          </p>
        </div>
      </div>
    </div>
  );
}

export default Register;