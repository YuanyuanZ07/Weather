# 🌤️ Weather App

An Android weather application built with **Kotlin** that displays real-time weather and a multi-day forecast for Halifax.  
The app retrieves live weather data from an external weather API and presents it in a clean mobile interface.

This project demonstrates **API integration, asynchronous data handling, and UI state management** using Android architecture components.

---

## ✨ Project Overview

The application simulates a lightweight mobile weather companion.

Users can:

- View current weather conditions
- Check a 3-day forecast
- Switch between **Now** and **Daily** views

The focus of this project is handling real data from a remote service and updating the UI dynamically.

---

## 📱 App Screens

### 🔹 Current Weather (Now Screen)
<img width="1080" height="2400" alt="Screenshot_20260215_095039" src="https://github.com/user-attachments/assets/5406fcc3-7014-4f3d-ab2b-386d6075697a" />

The main screen displays the current weather in Halifax.

**Displayed information:**

- Current temperature
- Weather condition (e.g., Partly Cloudy)
- "Feels like" temperature
- Wind speed
- Wind direction
- Weather icon

When the app launches, it sends a network request to the weather API and updates the UI automatically.

This demonstrates:

- Network calls
- JSON parsing
- Live UI updates

---

### 🔹 3-Day Forecast (Daily Screen)
<img width="1080" height="2400" alt="Screenshot_20260215_095113" src="https://github.com/user-attachments/assets/fb7ba4ae-5e96-4fe7-bdb4-ae8dc04094f1" />

The Daily screen shows a short-term weather forecast.

**Features:**

- 3-day forecast list
- High and low temperatures
- Weather conditions
- Weather icons

Each day is rendered using a reusable card-style UI component.

---

## 🔄 Navigation

The app uses a **bottom navigation bar**:

- **Now** → Current conditions
- **Daily** → Forecast view

Navigation preserves UI state and avoids unnecessary reloads.

---

## 🧱 Architecture

The project follows a simplified **MVVM (Model–View–ViewModel)** architecture.

### UI Layer
Responsible for:
- Rendering UI components
- Handling user interaction
- Displaying weather data

### ViewModel Layer
Responsible for:
- Fetching weather data
- Managing UI state
- Providing lifecycle-safe data to UI

### Data Layer
Responsible for:
- Performing API requests
- Parsing JSON responses
- Mapping data to Kotlin models

---

## 🌐 API & Data Handling

The app retrieves live weather information from a REST API.

Concepts demonstrated:

- HTTP requests
- Asynchronous operations
- JSON → Kotlin model mapping
- Error handling

---

## 🛠 Tech Stack

- **Language:** Kotlin
- **IDE:** Android Studio
- **Architecture:** MVVM
- **Networking:** REST API
- **UI:** Material Design Components

**Android Jetpack Components**
- ViewModel
- LiveData
- Navigation

---

## 🧠 Key Skills Demonstrated

- Consuming REST APIs
- Asynchronous programming
- Updating UI from network data
- Multi-screen navigation
- Clean separation of concerns
- Mobile UI design

---

## ▶️ How to Run

1. Clone the repository
git clone https://github.com/YuanyuanZ07/Weather.git


2. Open in **Android Studio**

3. Sync Gradle

4. Run on emulator or Android device

---

## 👩‍💻 Author

**Yuanyuan Zhou**  
NSCC IT Programming Student  
Halifax, Nova Scotia

