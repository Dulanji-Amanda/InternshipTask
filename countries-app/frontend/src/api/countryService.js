import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/countries';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const countryService = {
  getAllCountries: () => api.get('/'),
  
  searchCountries: (query) => api.get('/search', {
    params: { query }
  }),
  
  clearCache: () => api.post('/cache/clear')
};

export default api;
