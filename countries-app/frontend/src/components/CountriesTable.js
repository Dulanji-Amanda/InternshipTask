import React from 'react';
import './CountriesTable.css';

const CountriesTable = ({ countries, onRowClick, loading }) => {
  if (loading) {
    return <div className="loading">Loading countries...</div>;
  }

  if (countries.length === 0) {
    return <div className="no-results">No countries found. Try a different search.</div>;
  }

  return (
    <div className="table-container">
      <table className="countries-table">
        <thead>
          <tr>
            <th className="flag-col">Flag</th>
            <th className="name-col">Name</th>
            <th className="capital-col">Capital</th>
            <th className="region-col">Region</th>
            <th className="population-col">Population</th>
          </tr>
        </thead>
        <tbody>
          {countries.map((country, index) => (
            <tr 
              key={index} 
              onClick={() => onRowClick(country)}
              className="table-row"
            >
              <td className="flag-col">
                {country.flag && (
                  <img 
                    src={country.flag} 
                    alt={country.name} 
                    className="flag-image"
                  />
                )}
              </td>
              <td className="name-col">{country.name}</td>
              <td className="capital-col">{country.capital}</td>
              <td className="region-col">{country.region}</td>
              <td className="population-col">{country.population?.toLocaleString()}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default CountriesTable;
