# ConsoleMenu Library

A simple, lightweight, and customizable Java console menu and input/output utility library. Perfect for interactive terminal/CLI applications.

This project was extracted from the `RentalRoomManagement` application, refactored into a reusable library, and released under the MIT License ("allow all rights/permissions").

## Features

- **Dynamic Interactive Menu**: Create nested console menus easily with customizable options and custom list/data printers.
- **Robust Console Input Reader**: Bulletproof wrapper around `Scanner` to read and validate user inputs (`String`, `int`, `double`, `Long`, `Float`) with automated error handling.
- **Colorful Terminal Output**: Painted ANSI styling helper to print text in different RGB colors and log helper methods (`success`, `warn`, `error`, `sysLog`).
- **Flexible Exiting**: Support for both standard JVM termination on exit (`System.exit(0)`) or gentle loops returning for embedded systems.

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Version 17 or higher.
- **Gradle**: Uses the Gradle Wrapper included in the repository.

### Building the Project

To build the library and generate the JAR file:

```bash
./gradlew build
```

The compiled library JAR, sources JAR, and Javadoc JAR will be generated in `build/libs/`:
- `ConsoleMenu-1.0.0.jar`
- `ConsoleMenu-1.0.0-sources.jar`
- `ConsoleMenu-1.0.0-javadoc.jar`

### Running the Demo Example

You can run the built-in demo to test the menu:

```bash
./gradlew runExample
```

---

## How to Import & Use in Other Projects

### Option 1: Pushing to Git and Using JitPack (Recommended)

Since you want to upload this project to Git to use it in other projects, the easiest way to consume it is via [JitPack](https://jitpack.io/).

1. **Push this project to your GitHub repository** (e.g., `github.com/your-username/ConsoleMenu`).
2. **Create a release** or just use the latest commit hash.
3. In your **other** project's `build.gradle.kts`, add:

```kotlin
repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    implementation("com.github.Ersikthy:ConsoleMenu:1.0.0")
}
```

*(Note: Replace `your-username` with your actual GitHub username, and `1.0.0` with your tag/commit hash).*

### Option 2: Local JAR file import

Copy the generated `ConsoleMenu-1.0.0.jar` from `build/libs/` to a `libs/` folder in your other project, then add this to its `build.gradle.kts`:

```kotlin
dependencies {
    implementation(files("libs/ConsoleMenu-1.0.0.jar"))
}
```

---

## Usage Example

Here is a quick look at how to build and display a menu:

```java
import com.ersikthy.consolemenu.Menu;
import static com.ersikthy.consolemenu.util.Output.*;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu("My App Menu");
        
        // Add menu options
        menu.addOption("Option One", () -> success("You selected Option One!"));
        menu.addOption("Submenu", () -> {
            Menu sub = new Menu("Submenu Options");
            sub.addOption("Nested action", () -> sysLog("Doing sub-work."));
            sub.displayMenu(); // 'back' option will return here
        });

        // Optional custom display header
        menu.setListPrinter(() -> println("System Active", java.awt.Color.GREEN));

        // Start display loop
        menu.displayMenu();
    }
}
```

---

## License

This library is licensed under the **MIT License**. You are free to copy, modify, distribute, and use it in any private or commercial projects without restrictions.
