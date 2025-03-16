<<<<<<< HEAD
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/dashboard.css';

const QuickActionCard = ({ title, description, action, to, icon }) => {
  return (
    <div className="quick-action-card">
      <div className="quick-action-card-icon">{icon}</div>
      <h3 className="quick-action-card-title">{title}</h3>
      <p className="quick-action-card-description">{description}</p>
      <Link to={to} className="quick-action-card-button">
        {action} <span>&rarr;</span>
      </Link>
    </div>
  );
};

=======
import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/dashboard.css';

const QuickActionCard = ({ title, description, action, to, icon }) => {
  return (
    <div className="quick-action-card">
      <div className="quick-action-card-icon">{icon}</div>
      <h3 className="quick-action-card-title">{title}</h3>
      <p className="quick-action-card-description">{description}</p>
      <Link to={to} className="quick-action-card-button">
        {action} <span>&rarr;</span>
      </Link>
    </div>
  );
};

>>>>>>> cd202c26740cb6c39cbbe0fcebbf555b983f35a6
export default QuickActionCard;