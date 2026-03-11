#!/bin/bash

echo ""
echo "Starting Countries Explorer Application..."
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 17 or higher"
    exit 1
fi

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "ERROR: Node.js is not installed or not in PATH"
    echo "Please install Node.js 16 or higher"
    exit 1
fi

echo "Java and Node.js are installed. Starting services..."
echo ""

# Start backend in background
echo "Starting Backend (Spring Boot on port 8080)..."
cd backend
mvn spring-boot:run &
BACKEND_PID=$!
sleep 5

# Start frontend in background
echo "Starting Frontend (React on port 3000)..."
cd ../frontend
npm install
npm start &
FRONTEND_PID=$!

echo ""
echo "Both services are starting..."
echo "Backend: http://localhost:8080"
echo "Frontend: http://localhost:3000"
echo ""
echo "Press Ctrl+C to stop all services"
echo ""

# Wait for both processes
wait $BACKEND_PID $FRONTEND_PID
