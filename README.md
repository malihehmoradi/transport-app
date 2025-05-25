
# TransportApp Documentation

## Overview
**TransportApp** is a Kotlin-based Android application designed to manage and display cargo information. It uses modern Android development tools and frameworks, including Jetpack Compose, Hilt for dependency injection, and Kotlin Coroutines for asynchronous programming.

---
## Architecture
The app follows a **Clean Architecture** pattern with the following layers:
1. **Presentation**: Contains UI components built with Jetpack Compose.
2. **Domain**: Includes business logic and use cases.
3. **Data**: Manages data sources and repositories.
---

## Dependencies
### Core Libraries
- **Jetpack Compose**: For building the UI.
- **Hilt**: For dependency injection.
- **Kotlin Coroutines**: For asynchronous programming.

|                 |Dependency            |Usage       |
|:----------------|:---------------------|:-----------|
|Architechture|`MVI,Clean Architecture`|For structuring the app into maintainable layers (UI, Domain, Data) and managing UI state predictably.|
|UI Toolkit|`Jetpack Compose`|For building the app’s user interface declaratively with Kotlin.|
|CI-CD|`Github Actions`|For automating the build, test, and deployment (release) pipeline of the application.|
|Dependency Injection |`Hilt`|For managing and providing dependencies throughout the application, improving modularity and testability.|
|Asynchronous |`Kotlin Coroutine, Flow`|For managing background tasks, asynchronous operations (like network calls, database access), and handling streams of data.|
|Json Parser |`Gson`|For converting JSON data (e.g., from network APIs) into Kotlin objects and vice-versa.|
|Code check or style |`Lint, Spotless, Ktlint`|For enforcing code style consistency, identifying potential bugs, and improving code quality automatically.|
|Logger |`Timber`|For enhanced and more structured logging throughout the application, especially useful for debugging|
|Testing |`JUnit`|Unit tests for the HomeViewModel. |
---

## Setup Instructions
1. Clone the repository:
   ```bash
   git clone https://github.com/malihehmoradi/transport-app.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle to download dependencies.
4. Run the app on an emulator or physical device.

---

## Testing
The app includes unit tests for the `HomeViewModel`. To run the tests:
1. Open the `HomeViewModelTest` file in `app/src/test/java`.
2. Run the tests using the Android Studio test runner.

---

## Future Enhancements
- Add network integration for real-time data fetching.
- Implement error handling for API failures.
- Add more localization support.

--- 

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.