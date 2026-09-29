# Android Application Development - Practical 7
**Enrollment / Student ID:** 24012011221  
**Project Name:** 24012011221_Raju_prac7_mad  

---

## 📱 Overview
This Android application is developed as part of the Mobile Application Development (MAD) coursework (Practical 7). It demonstrates advanced Android development concepts including **SQLite Database integration**, **Asynchronous Network Operations (HTTP API calls using Coroutines)**, **RecyclerView with custom adapters**, and **Activity navigation** (Registration, Detail Views, and Map integration).

---

## ✨ Features
- **Local Data Persistence (SQLite):** Uses `DatabaseHelper` to store, retrieve, insert, and delete person records locally. Automatically populates default data on first launch if empty.
- **Remote API Data Fetching:** Integrates with an external REST API endpoint (`json-generator.com`) using Kotlin Coroutines (`Dispatchers.IO`) to fetch and sync person details.
- **Dynamic RecyclerView:** Displays person records efficiently using `RecyclerView` and `PersonAdapter` with interactive item deletion and UI updates.
- **Multi-Activity Workflow:**
  - `MainActivity`: Main dashboard with list view and API refresh action.
  - `RegisterActivity`: Form for registering/adding new person entries.
  - `AllDataView`: Comprehensive view for record management.
  - `MapActivity`: Location / map-based view component.
- **Modern Material Design:** Styled with Material Design components and Jetpack Compose theme support (`ui/theme`).

---

## 📂 Project Structure

```text
app/src/main/java/com/example/a24012011221_raju_prac7_mad/
│
├── MainActivity.kt          # Main entry point displaying RecyclerView and handling API sync
├── DatabaseHelper.kt        # SQLite database handler for CRUD operations
├── HttpRequest.kt           # Network utility for making HTTP service calls
├── Person.kt                # Data model representing person details
├── PersonAdapter.kt         # RecyclerView Adapter for binding person items
├── PersonDbTableData.kt     # Database table schema definition
├── RegisterActivity.kt      # Activity for user registration / entry creation
├── AllDataView.kt           # Detailed data viewing activity
└── MapActivity.kt           # Map integration activity
```

---

## 🛠️ Tech Stack & Libraries
- **Language:** Kotlin
- **Concurrency:** Kotlin Coroutines (`Dispatchers.IO`, `Dispatchers.Main`)
- **Database:** Android SQLite Database
- **UI Components:** RecyclerView, FloatingActionButton, Material Components, XML Layouts & Jetpack Compose Theme
- **Networking:** `HttpURLConnection` / Custom HTTP Request handler

---

## 🚀 Getting Started

1. **Clone or Open** the project in Android Studio (Arctic Fox or newer recommended).
2. **Sync Gradle:** Ensure all Gradle dependencies are downloaded and synced (`build.gradle.kts`).
3. **Run the App:** Connect an Android device or start an Emulator (API 24+) and click **Run** (`Shift + F10`).


   ## screenshot
   <img width="377" height="783" alt="image" src="https://github.com/user-attachments/assets/a3c88903-13df-40b6-856b-9ba0b840056d" />
   
