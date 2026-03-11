# Quick Start Guide

## One-Command Startup (Windows)

```bash
.\start.bat
```

## One-Command Startup (Mac/Linux)

```bash
chmod +x start.sh
./start.sh
```

## Manual Startup

### Terminal 1 - Backend (Java/Spring Boot)

```bash
cd backend
mvn spring-boot:run
```

Expected output: Application started on port 8080

### Terminal 2 - Frontend (React)

```bash
cd frontend
npm install
npm start
```

Expected output: Application opens on http://localhost:3000

## Access the Application

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080/api/countries
- **API Health Check**: http://localhost:8080/api/countries/health

## Features to Try

1. **View All Countries**: Page loads with all countries in a table
2. **Search**: Type "france" or "paris" in the search box to filter
3. **View Details**: Click any row to see the country details modal
4. **Clear Search**: Click the ✕ icon to clear the search

## Backend API Endpoints

### Get All Countries

```bash
curl http://localhost:8080/api/countries
```

### Search Countries

```bash
curl "http://localhost:8080/api/countries/search?query=france"
```

### Health Check

```bash
curl http://localhost:8080/api/countries/health
```

### Clear Cache

```bash
curl -X POST http://localhost:8080/api/countries/cache/clear
```

## Troubleshooting

### Port Already in Use

- **Port 8080**: Kill process or change `server.port` in `backend/src/main/resources/application.properties`
- **Port 3000**: Kill process or run `npm start -- --port 3001`

### Can't Connect to Backend

- Ensure backend is running: `http://localhost:8080/api/countries/health`
- Check CORS configuration in `application.properties`
- Open browser DevTools to see network errors

### Maven Build Fails

```bash
# Clear Maven cache
mvn clean
# Try again
mvn spring-boot:run
```

### npm Install Fails

```bash
# Clear npm cache
npm cache clean --force
# Delete node_modules and lock file
rm -rf node_modules package-lock.json
# Reinstall
npm install
```

## What's Happening Under the Hood

1. **Backend** calls REST Countries API every 10 minutes (or on first request)
2. **Caching** stores results in memory to reduce API calls
3. **Frontend** calls backend `/api/countries` endpoint
4. **Search** is handled server-side for efficiency
5. **Modal** displays full country details on row click

## Performance

- API calls are cached for 10 minutes
- Cache refreshes automatically every 10 minutes
- Over 250 countries available
- Search is fuzzy and matches: name, capital, region

## Next Steps

1. Explore the UI and search for different countries
2. Check browser DevTools Network tab to see API calls
3. Look at the source code to understand the architecture
4. Try modifying the UI or adding new features

Enjoy exploring countries! 🌍
