@echo off
echo.
echo Starting Countries Explorer Application...
echo.

REM Check if Java is installed
java -version >nul 2>&1
if "%ERRORLEVEL%" NEQ "0" (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher
    pause
    exit /b 1
)

REM Check if Node.js is installed
node --version >nul 2>&1
if "%ERRORLEVEL%" NEQ "0" (
    echo ERROR: Node.js is not installed or not in PATH
    echo Please install Node.js 16 or higher
    pause
    exit /b 1
)

echo Java and Node.js are installed. Starting services...
echo.

REM Start backend in a new window
echo Starting Backend (Spring Boot on port 8080)...
cd backend
start cmd /k "mvn spring-boot:run"
timeout /t 5 /nobreak

REM Start frontend in a new window
echo Starting Frontend (React on port 3000)...
cd ..\frontend
call npm install
start cmd /k "npm start"

echo.
echo Both services are starting...
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo.
pause
