# CleanArchitectureDemoKotlinWithJetpack

A reference Android app demonstrating **Clean Architecture** in pure Kotlin, using **Jetpack Compose** for UI, **DataStore** for simple caching, and real network data from a public API.

---

## ✨ Features

- **Splash Screen:** Loads user data from the internet or cache, with a friendly minimum splash and graceful error handling.
- **User List Screen:** Displays all users, supports pull-to-refresh, fast search with a clear icon, and elegant navigation.
- **User Detail Screen:** Tap any user to see full details with a clean back navigation.
- **Offline Support:** Uses DataStore to cache API results for offline use.
- **Clean Architecture:** All core layers (Presentation, Application, Domain, Data) are strictly separated for maximum testability and maintainability.
- **Minimal Dependencies:** Only modern Jetpack and Kotlin libraries (OkHttp, Gson, DataStore).

---

## 🏛️ Clean Architecture Overview

> **"Inner layers know nothing about outer layers."**

This app enforces strong separation between:

- **Presentation Layer:** Jetpack Compose screens, state, and navigation (no business logic or data code)
- **Application Layer:** Use cases, orchestrating business rules (no data/UI dependencies)
- **Domain Layer:** Pure business models & repository interfaces (no frameworks or Android code)
- **Data Layer:** API, cache, repository implementations (only this knows about OkHttp, Gson, DataStore, etc.)

**Why?**  
- Modular, maintainable, and easy-to-test code.
- You can swap network/cache logic or refactor UI without breaking core features.

---

## 🗂️ Project Structure

```plaintext
com.biplob.cleanarchitecturecompose
│
├── application/
│   └── usecase/                # Use cases (Application Layer)
│
├── data/
│   ├── model/                  # API DTOs (UserDto)
│   ├── repository/             # Repository implementations
│   └── source/                 # Data sources (Remote/Local)
│
├── domain/
│   ├── model/                  # Pure business models (User)
│   └── repository/             # Repository interfaces
│
└── presentation/
    ├── common/                 # Adapter, Singleton holder, etc.
    ├── splash/                 # SplashScreen
    ├── userdetail/             # UserDetailScreen
    └── userlist/               # UserListScreen + ViewModel
MainActivity.kt


🔗 Public API Used
JSONPlaceholder /users

🛠️ Tech Stack
Kotlin 2.x (or latest stable)

Jetpack Compose (for UI)

DataStore (for simple persistent caching)

OkHttp (for network requests)

Gson (for JSON parsing)

ViewModel + StateFlow (for state management)

No DI frameworks, no legacy Android XML layouts, no Room/SQLite

🚀 How To Run
Clone this repo.

Open in Android Studio (latest stable recommended).

Run Sync Project with Gradle Files.

Run on any emulator or real device (API 24+).

The app will fetch users on first run.
If offline, it shows last cached users (if available).

🧑‍💻 Code Flow Example
SplashScreen triggers GetUsersUseCase

GetUsersUseCase (Application Layer) calls the UserRepository interface

UserRepositoryImpl (Data Layer) loads from API or cache, converts DTOs to domain models

UserListScreen displays the list, handles search, filter, refresh

UserDetailScreen shows the selected user’s full info

🧪 Unit Testing
Designed for robust unit testing at every layer.

You can easily mock repositories, use cases, or test UI with Compose testing tools.


📐 Clean Architecture Diagram
plaintext
Copy
Edit
Presentation (Compose UI)
         ↓
Application (UseCase)
         ↓
Domain (Repository Interface, Entity)
         ↓
Data (Repository Impl, Data Sources, DTOs)

👨‍💻 Author
Shafiul Alam Biplob

Inspired by Uncle Bob’s Clean Architecture and the Android/Jetpack Compose community

💡 Want More?
This project is a reference for porting to Flutter, modern MVVM, or even multiplatform (KMP) stacks.

See also:

Java Clean Architecture Demo

Flutter Clean Architecture Demo
