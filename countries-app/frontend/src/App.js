import React, { useState, useEffect } from 'react';
import './App.css';
import SearchInput from './components/SearchInput';
import CountriesTable from './components/CountriesTable';
import Modal from './components/Modal';
import { countryService } from './api/countryService';

function App() {
  const [countries, setCountries] = useState([]);
  const [filteredCountries, setFilteredCountries] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedCountry, setSelectedCountry] = useState(null);
  const [modalOpen, setModalOpen] = useState(false);

  // Fetch countries on component mount
  useEffect(() => {
    fetchCountries();
  }, []);

  // Filter countries when search query changes
  useEffect(() => {
    if (searchQuery.trim()) {
      handleSearch(searchQuery);
    } else {
      setFilteredCountries(countries);
    }
  }, [searchQuery, countries]);

  const fetchCountries = async () => {
    try {
      setLoading(true);
      setError(null);
      const response = await countryService.getAllCountries();
      setCountries(response.data);
      setFilteredCountries(response.data);
    } catch (err) {
      console.error('Error fetching countries:', err);
      setError('Failed to fetch countries. Please try again later.');
      setCountries([]);
      setFilteredCountries([]);
    } finally {
      setLoading(false);
    }
  };

  const handleSearch = async (query) => {
    try {
      setLoading(true);
      setError(null);
      const response = await countryService.searchCountries(query);
      setFilteredCountries(response.data);
    } catch (err) {
      console.error('Error searching countries:', err);
      setError('Failed to search countries. Please try again.');
      setFilteredCountries([]);
    } finally {
      setLoading(false);
    }
  };

  const handleRowClick = (country) => {
    setSelectedCountry(country);
    setModalOpen(true);
  };

  const handleCloseModal = () => {
    setModalOpen(false);
    setSelectedCountry(null);
  };

  const handleSearchChange = (query) => {
    setSearchQuery(query);
  };

  return (
    <div className="App">
      <header className="app-header">
        <h1>🌍 Countries Explorer</h1>
        <p className="subtitle">Explore information about countries around the world</p>
      </header>

      <main className="app-main">
        <div className="container">
          <SearchInput 
            value={searchQuery} 
            onChange={handleSearchChange}
          />
          
          {error && <div className="error-message">{error}</div>}
          
          <CountriesTable 
            countries={filteredCountries}
            onRowClick={handleRowClick}
            loading={loading}
          />

          <Modal 
            isOpen={modalOpen}
            country={selectedCountry}
            onClose={handleCloseModal}
          />

          {!loading && countries.length > 0 && (
            <div className="info-footer">
              Showing {filteredCountries.length} of {countries.length} countries
            </div>
          )}
        </div>
      </main>

      <footer className="app-footer">
        <p>Data powered by <a href="https://restcountries.com/" target="_blank" rel="noopener noreferrer">REST Countries API</a></p>
      </footer>
    </div>
  );
}

export default App;
