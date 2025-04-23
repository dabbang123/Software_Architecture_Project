import React, { useEffect, useState } from "react";
import LoanApplicationForm from "./LoanApplicationForm";
import "./StudentDashboard.css";

function StudentDashboard() {
  const [loanStatus, setLoanStatus] = useState("Pending");
  const [loanOffers, setLoanOffers] = useState([]);
  const [showLoanForm, setShowLoanForm] = useState(false);

  useEffect(() => {
    const role = localStorage.getItem("role");
    if (role !== "Student") {
      window.location.href = "/";
    }
  }, []);

  useEffect(() => {
    setLoanStatus("Pending");
    setLoanOffers([
      { id: 1, amount: 500000, interest: 8.5 },
      { id: 2, amount: 600000, interest: 9.2 },
    ]);
  }, []);

  const handleLogout = () => {
    localStorage.clear();
    window.location.href = "/";
  };

  const handleWithdraw = () => {
    setLoanStatus("Withdrawn");
    setShowLoanForm(false);
  };

  const handleAcceptOffer = (id) => {
    alert(`Loan offer ID ${id} accepted`);
  };

  const handleRejectOffer = (id) => {
    alert(`Loan offer ID ${id} rejected`);
  };

  return (
    <div className="dashboard">
      <div className="dashboard-header">
        <h2>Welcome, Student 👋</h2>
        <button className="logout-button" onClick={handleLogout}>Logout</button>
      </div>

      <section className="card">
        <h3>Loan Application</h3>
        {!showLoanForm ? (
          <button onClick={() => setShowLoanForm(true)}>Submit Loan Application</button>
        ) : (
          <LoanApplicationForm onSuccess={() => setShowLoanForm(false)} />
        )}
      </section>

      <section className="card">
        <h3>Loan Status</h3>
        <p>
          Current Status: <strong>{loanStatus}</strong>
        </p>
        {loanStatus === "Pending" && (
          <button onClick={handleWithdraw}>Withdraw Application</button>
        )}
      </section>

      <section className="card">
        <h3>Loan Offers</h3>
        {loanOffers.length === 0 ? (
          <p>No offers available yet.</p>
        ) : (
          <ul>
            {loanOffers.map((offer) => (
              <li key={offer.id}>
                ₹{offer.amount.toLocaleString()} @ {offer.interest}% interest
                <div className="offer-actions">
                  <button onClick={() => handleAcceptOffer(offer.id)}>Accept</button>
                  <button onClick={() => handleRejectOffer(offer.id)}>Reject</button>
                </div>
              </li>
            ))}
          </ul>
        )}
      </section>
    </div>
  );
}

export default StudentDashboard;
