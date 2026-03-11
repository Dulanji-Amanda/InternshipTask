import React from 'react';
import './Modal.css';

const Modal = ({ isOpen, country, onClose }) => {
  if (!isOpen || !country) return null;

  return (
    <div className="modal-overlay" onClick={onClose}>
      <div className="modal-content" onClick={(e) => e.stopPropagation()}>
        <button className="modal-close" onClick={onClose}>&times;</button>
        
        <div className="modal-header">
          {country.flag && (
            <img src={country.flag} alt={country.name} className="modal-flag" />
          )}
          <h2 className="modal-title">{country.name}</h2>
        </div>

        <div className="modal-body">
          <div className="detail-row">
            <span className="detail-label">Capital:</span>
            <span className="detail-value">{country.capital}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Region:</span>
            <span className="detail-value">{country.region}</span>
          </div>
          <div className="detail-row">
            <span className="detail-label">Population:</span>
            <span className="detail-value">{country.population?.toLocaleString()}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Modal;
