import React from 'react';
import './SearchInput.css';

const SearchInput = ({ value, onChange, placeholder = "Search by country name, capital, or region..." }) => {
  return (
    <div className="search-container">
      <input
        type="text"
        className="search-input"
        placeholder={placeholder}
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
      {value && (
        <button 
          className="clear-button" 
          onClick={() => onChange('')}
          title="Clear search"
        >
          ✕
        </button>
      )}
    </div>
  );
};

export default SearchInput;
