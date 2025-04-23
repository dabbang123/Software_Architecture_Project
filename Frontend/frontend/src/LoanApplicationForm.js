import React, { useState } from "react";
import axios from "axios";
import "./LoanApplicationForm.css";

function LoanApplicationForm({ onSuccess }) {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    address: "",
    educationDetails: "",
    membershipType: "Regular",
    email: "",
    phoneNumber: "",
    loanAmount: "",
    purpose: "",
    guarantor: ""
  });

  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const formattedData = {
        ...formData,
        dateOfBirth: formatDate(formData.dateOfBirth)
      };

      await axios.post("http://localhost:8080/api/loans/submit", formattedData);
      setSuccess("Loan application submitted successfully!");
      setError("");

      // Reset form
      setFormData({
        firstName: "", lastName: "", dateOfBirth: "", address: "", educationDetails: "",
        membershipType: "Regular", email: "", phoneNumber: "", loanAmount: "",
        purpose: "", guarantor: ""
      });

      if (onSuccess) {
        setTimeout(onSuccess, 1000);
      }

    } catch (err) {
      setSuccess("");
      setError("Failed to submit loan. Please try again.");
    }
  };

  const formatDate = (input) => {
    const [year, month, day] = input.split("-");
    return `${day}-${month}-${year}`; // convert yyyy-MM-dd to dd-MM-yyyy
  };

  return (
    <div className="loan-form-container">
      <h3>Loan Application Form</h3>
      {success && <div className="success-box">{success}</div>}
      {error && <div className="error-box">{error}</div>}

      <form onSubmit={handleSubmit} className="loan-form">
        {[
          ["First Name", "firstName"],
          ["Last Name", "lastName"],
          ["Date of Birth", "dateOfBirth", "date"],
          ["Address", "address"],
          ["Education Details", "educationDetails"],
          ["Email", "email", "email"],
          ["Phone Number", "phoneNumber", "tel"],
          ["Loan Amount", "loanAmount", "number"],
          ["Purpose", "purpose"],
          ["Guarantor", "guarantor"]
        ].map(([label, name, type = "text"]) => (
          <div key={name}>
            <label>{label}</label>
            <input type={type} name={name} value={formData[name]} onChange={handleChange} />
          </div>
        ))}

        <label>Membership Type</label>
        <select name="membershipType" value={formData.membershipType} onChange={handleChange}>
          <option value="Regular">Regular</option>
          <option value="Premium">Premium</option>
        </select>

        <button type="submit">Submit Loan</button>
      </form>
    </div>
  );
}

export default LoanApplicationForm;
