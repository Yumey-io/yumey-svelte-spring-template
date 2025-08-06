# Yumey Svelte + Spring Template

A full-stack web application template using Svelte for the frontend and Spring Boot for the backend.

## 🚀 Technology Stack

### Frontend
- **Svelte** - A reactive JavaScript framework
- **SvelteKit** - The official Svelte application framework
- **TypeScript** - For type safety
- **Vite** - Next generation frontend tooling

### Backend
- **Spring Boot** - Java-based framework for building web applications
- **Kotlin** - Modern JVM language with enhanced syntax and features
- **Spring Security** - Authentication and authorization
- **JUnit 5** - Testing framework

## 📂 Project Structure

```
yumey-svelte-spring-template/
├── App/                    # Frontend Svelte application
│   ├── src/                # Source files
│   ├── static/             # Static assets
│   ├── tests/              # Frontend tests
│   └── package.json        # NPM dependencies
│
├── Server/                 # Backend Spring Boot application
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/     # Kotlin source files
│   │   │   └── resources/  # Application resources
│   │   └── test/           # Backend tests
│   ├── build.gradle.kts    # Gradle build configuration
│   └── gradlew             # Gradle wrapper script
│
└── README.md               # Project documentation
```

## 🔧 Setup

### Prerequisites
- JDK 17 or later
- Node.js 16 or later
- Gradle 7.x or later (or use the included Gradle wrapper)

### Backend Setup
1. Navigate to the Server directory:
   ```bash
   cd Server
   ```

2. Build the application:
   ```bash
   ./gradlew build
   ```

### Frontend Setup
1. Navigate to the App directory:
   ```bash
   cd App
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

## 🏃‍♂️ Running the Application

### Running the Backend
```bash
cd Server
./gradlew bootRun
```
The Spring Boot server will start on `http://localhost:8080`.

### Running the Frontend
```bash
cd App
npm run dev
```
The Svelte application will be available at `http://localhost:5173`.

## 🧪 Testing

### Backend Tests
```bash
cd Server
./gradlew test
```

### Frontend Tests
```bash
cd App
npm run test
```

## 🔄 Development Workflow

1. Run both the frontend and backend servers
2. Make changes to either part of the application
3. The frontend has hot module replacement for instant updates
4. The backend may need to be restarted for certain changes

## 📦 Building for Production

### Backend
```bash
cd Server
./gradlew build
```
This will create a runnable JAR in the `build/libs` directory.

### Frontend
```bash
cd App
npm run build
```
This creates optimized production assets in the `build` directory.

## 🔐 Security

The application uses Spring Security for authentication and authorization. The security configuration can be found in the `SecurityConfig.kt` file.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## 📄 License

[MIT License](LICENSE)
