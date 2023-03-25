# Modern Android Development

## Language
* [Kotlin](https://kotlinlang.org/docs/home.html)
* [Android Basics in Kotlin](https://developer.android.com/courses/android-basics-kotlin/course)


## Android Jetpack
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  * Codelab
* [Room](https://developer.android.com/training/data-storage/room)
  * Codelab
* [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
  * Codelab
* [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
  * Codelab
* [Navigation](https://developer.android.com/guide/navigation)
  * Codelab
* [CameraX](https://developer.android.com/training/camerax)
  * Codelab
* [Compose](https://medium.com/@devjorgecastro/modern-android-app-development-in-2023-ff445d3652b4#af4e)
  * [Course](https://developer.android.com/courses/jetpack-compose/course)
  * Codelab


## Material Design
* [Material Design 3](https://m3.material.io/)

## Clean Architecture in Android
* Presentation: Activities, Fragments, View Models, others view components.
* Domain: Use Cases, Entities, Repositories, others domain components.
* Data: Repository implementations, Mappers, DTO’s, etc.

### Architecture Patterns for Presentation Layer
* MVVM
* MVI

### [Guide to app architecture](https://developer.android.com/topic/architecture)
* Common architectural principles
  * __Separation of concerns__: These UI-based classes(Activity, Fragment) should only contain logic that handles UI and operating system interactions. By keeping these classes as lean as possible, you can avoid many problems related to the component lifecycle, and improve the testability of these classes.
  * __Drive UI from data models(persistent models)__: Data models are independent from the UI elements and other components in your app. This means that they are not tied to the UI and app component lifecycle, but will still be destroyed when the OS decides to remove the app's process from memory.
  * __Single source of truth(SSOT)__: The SSOT is the owner of that data, and only the SSOT can modify or mutate it. To achieve this, the SSOT exposes the data using an immutable type, and to modify the data, the SSOT exposes functions or receive events that other types can call.
  * __Unidirectional Data Flow(UDF)__: The single source of truth principle is often used in our guides with the Unidirectional Data Flow (UDF) pattern. In UDF, state flows in only one direction. The events that modify the data flow in the opposite direction.

## Recommended app architecture
  * The UI layer that displays application data on the screen.
  * The data layer that contains the business logic of your app and exposes application data.

![Alt text](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview.png)
(The arrows mean their dependency)

### Modern App Architecture
* A reactive and layered architecture.
* Unidirectional Data Flow (UDF) in all layers of the app.
* A UI layer with state holders to manage the complexity of the UI.
* Coroutines and flows.
* Dependency injection best practices.

#### UI layer
* The role of the UI layer (or presentation layer) is to display the application data on the screen.
* The UI layer is made up of two things:
  * UI elements that render the data on the screen. You build these elements using __Views__ or __Jetpack Compose__ functions.
  * State holders (such as __ViewModel__ classes) that hold data, expose it to the UI, and handle logic.

![Alt text](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-ui.png)

#### Data layer
* The data layer of an app contains the business logic. The business logic is what gives value to your app—it's made of rules that determine how your app creates, stores, and changes data.
* The data layer is made of __repositories__ that each can contain zero to many data sources.

![Alt text](https://developer.android.com/static/topic/libraries/architecture/images/mad-arch-overview-data.png)

* Repository classes are responsible for the following tasks:
  * Exposing data to the rest of the app.
  * Centralizing changes to the data.
  * Resolving conflicts between multiple data sources.
  * Abstracting sources of data from the rest of the app.
  * Containing business logic.

#### Domain layer
* The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels.

### [General best practices](https://developer.android.com/topic/architecture#best-practices)
* Don't store data in app components(e.g. activities, fragment, services).
* Reduce dependencies on Android classes.
* Create well-defined boundaries of responsibility(single responsibility) between various modules in your app.
* Expose as little as possible from each module.
* Focus on the unique core of your app so it stands out from other apps.
* Consider how to make each part of your app testable in isolation.
* Types are responsible for their concurrency policy.
* Persist as much relevant and fresh data as possible.


## References
* [Modern Android Development in 2023](https://devjorgecastro.medium.com/modern-android-app-development-in-2023-ff445d3652b4)
* [Guide to app architecture](https://developer.android.com/topic/architecture)
