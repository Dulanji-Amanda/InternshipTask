@echo off
set "ROOT_DIR=%~dp0"
cd /d "%ROOT_DIR%"

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

REM Resolve Maven command (global or local)
set "MAVEN_CMD=mvn"
where mvn >nul 2>&1
if "%ERRORLEVEL%" NEQ "0" (
    if exist "%ROOT_DIR%tools\apache-maven-3.9.9\bin\mvn.cmd" (
        set "MAVEN_CMD=%ROOT_DIR%tools\apache-maven-3.9.9\bin\mvn.cmd"
        echo Maven not found in PATH. Using local Maven: %MAVEN_CMD%
    ) else (
        echo ERROR: Maven is not installed and local Maven was not found.
        echo Please install Maven or keep local Maven at tools\apache-maven-3.9.9\bin\mvn.cmd
        pause
        exit /b 1
    )
)

echo Java and Node.js are installed. Starting services...
echo.

REM Start backend in a new window
echo Starting Backend (Spring Boot on port 8080)...
cd /d "%ROOT_DIR%backend"
start cmd /k "\"%MAVEN_CMD%\" spring-boot:run"
timeout /t 5 /nobreak

REM Start frontend in a new window
echo Starting Frontend (React on port 3000)...
cd /d "%ROOT_DIR%frontend"
call npm install
start cmd /k "npm start"

echo.
echo Both services are starting...
echo Backend: http://localhost:8080
echo Frontend: http://localhost:3000
echo.
pause
