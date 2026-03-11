# Countries Explorer Application

A full-stack application to explore and search information about countries using the REST Countries API.

## Features

### Backend (Java Spring Boot)

- Fetches country data from [REST Countries API](https://restcountries.com/)
- Returns filtered data: Name, Capital, Region, Population, and Flag
- Global search functionality (search by name, capital, or region)
- Simple in-memory caching with automatic refresh every 10 minutes
- RESTful API with CORS enabled for frontend communication

### Frontend (React)

- Interactive countries table with all key information
- Real-time search functionality
- Clickable rows to view detailed country information in a modal
- Responsive design for all device sizes
- Modern UI with gradient styling

## Project Structure

```
countries-app/
├── backend/                          # Spring Boot Application
│   ├── src/main/java/com/countries/
│   │   ├── api/
│   │   │   └── CountriesApplication.java
│   │   ├── controller/
│   │   │   └── CountryController.java
│   │   ├── service/
│   │   │   └── CountryService.java
│   │   ├── model/
│   │   │   └── CountryDTO.java
│   │   └── config/
│   │       └── RestTemplateConfig.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
│
└── frontend/                         # React Application
    ├── src/
    │   ├── components/
    │   │   ├── CountriesTable.js
    │   │   ├── SearchInput.js
    │   │   ├── Modal.js
    │   │   └── *.css
    │   ├── api/
    │   │   └── countryService.js
    │   ├── App.js
    │   ├── index.js
    │   └── App.css
    ├── public/
    │   └── index.html
    └── package.json
```

## Prerequisites

- **Backend**: Java 17+, Maven 3.6+
- **Frontend**: Node.js 16+, npm 8+

## Installation & Setup

### Backend Setup

1. Navigate to the backend directory:

   ```bash
   cd backend
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

   The backend will start on `http://localhost:8080`

### Frontend Setup

1. Navigate to the frontend directory:

   ```bash
   cd frontend
   ```

2. Install dependencies:

   ```bash
   npm install
   ```

3. Start the development server:

   ```bash
   npm start
   ```

   The frontend will automatically open on `http://localhost:3000`

## API Endpoints

### Get All Countries

```
GET /api/countries
```

Returns a list of all countries with their information.

### Search Countries

```
GET /api/countries/search?query={query}
```

Searches countries by name, capital, or region. Returns matching results.

### Clear Cache

```
POST /api/countries/cache/clear
```

Manually clears the backend cache (useful for testing).

### Health Check

```
GET /api/countries/health
```

Returns the health status of the API.

## Usage

1. **View All Countries**: On page load, all countries are displayed in a table
2. **Search**: Type in the search box to filter countries by name, capital, or region
3. **View Details**: Click on any row to open a modal with detailed information
4. **Clear Search**: Click the ✕ button in the search box to clear the search

## Caching Strategy

The backend implements a simple caching mechanism:

- Countries data is fetched from the REST Countries API and cached in memory
- Cache validity: 10 minutes
- When cache expires, fresh data is automatically fetched on the next request
- Scheduled refresh task runs every 10 minutes to keep data fresh
- Manual cache clearing is available via the `/cache/clear` endpoint

## Response Format

### Country Object

```json
{
  "name": "United States",
  "capital": "Washington, D.C.",
  "region": "Americas",
  "population": 331002651,
  "flag": "https://flagcdn.com/us.png"
}
```

## Technologies Used

### Backend

- Spring Boot 3.1.5
- Java 17
- Maven
- REST Template for API calls
- Lombok for code generation

### Frontend

- React 18
- Axios for HTTP requests
- CSS3 with modern features (Flexbox, Grid, Animations)
- HTML5

## Error Handling

- **Backend**: Returns cached data if API fails, includes comprehensive logging
- **Frontend**: Displays user-friendly error messages with retry capability

## Performance Considerations

1. **Caching**: Reduces API calls by 10x (every 10 minutes)
2. **Lazy Loading**: Frontend loads only visible data
3. **Efficient Filtering**: Server-side search reduces data transfer
4. **CORS Enabled**: Allows frontend to communicate with backend

## Troubleshooting

### Backend won't start

- Ensure Java 17+ is installed: `java -version`
- Check if port 8080 is available
- Clear Maven cache: `mvn clean`

### Frontend won't start

- Ensure Node.js 16+ is installed: `node --version`
- Clear npm cache: `npm cache clean --force`
- Delete node_modules and reinstall: `rm -rf node_modules && npm install`

### Can't find countries

- Ensure backend is running on `http://localhost:8080`
- Check browser console for API errors
- Try clearing the backend cache: `curl -X POST http://localhost:8080/api/countries/cache/clear`

## Future Enhancements

- Add filtering by continent
- Implement pagination for large datasets
- Add export functionality (CSV, PDF)
- Include additional country data (languages, currencies, etc.)
- Add favorites/bookmarks feature
- Implement data persistence with database
- Add unit and integration tests

## License

This project is open source and available for educational purposes.

## Author

Created for the Internship Project

## Support

For issues or questions, please refer to the [REST Countries API Documentation](https://restcountries.com/)
