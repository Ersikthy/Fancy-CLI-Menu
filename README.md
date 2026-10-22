Language: [Vietmaese](README_VN.md) | [English]

# Fancy-CLI-Menu

A lightweight Java library for building interactive terminal/CLI menus with themes, breadcrumb navigation, and nested submenus.

## Features

- **Themed Box-Drawing UI** — Styled menus with `SINGLE`, `DOUBLE`, `SQUIRCLE`, and `CLASSIC` border styles
- **Breadcrumb Navigation** — Automatically shows path like `Home › Rooms › Settings`
- **Nested Submenus** — Link menus in a parent-child hierarchy; submenus auto-get a `[B] Back` shortcut
- **Bottom Navigation Bar** — System shortcuts (`[B] Back`, `[E] Exit`, `[S] Setting`) rendered in a clean footer row
- **Colored Output** — RGB ANSI helper with `success`, `warn`, `error`, `sysLog` badges
- **Input Validation** — Bulletproof `Scanner` wrappers for `String`, `int`, `double`, `Long`, `Float`

---

## Quick Start

**Requirements:** Java 17+, Gradle (wrapper included)

```bash
# Build
./gradlew build

# Run the built-in demo
./gradlew runExample
```

---

## Usage

```java
import com.ersikthy.consolemenu.Menu;
import com.ersikthy.consolemenu.style.Theme;
import static com.ersikthy.consolemenu.util.Output.*;

public class Main {
    public static void main(String[] args) {

        Menu mainMenu = new Menu("Main Menu", Theme.COZY);
        mainMenu.addOption("Say Hello", () -> success("Hello!"));
        mainMenu.addOption("System Status", () -> sysLog("All systems operational."));

        // Submenu as numbered option
        Menu demoMenu = new Menu("Demo", Theme.COZY);
        demoMenu.addOption("Toggle Debug", () -> success("Debug on."));
        demoMenu.addSysOpt("Exit", demoMenu::exit);
        mainMenu.addSubMenu(demoMenu);

        // Submenu in the footer bar (press [S] to open)
        Menu settingMenu = new Menu("Settings", Theme.COZY);
        settingMenu.addOption("Switch to Cyberpunk", () -> {
            settingMenu.setTheme(Theme.CYBERPUNK);
            success("Theme updated!");
        });
        mainMenu.addSysSubMenu("Setting", settingMenu);

        // Footer shortcuts
        mainMenu.addSysOpt("Exit", mainMenu::exit);
        mainMenu.addSysOpt("Help", () -> sysLog("Type a number or shortcut key."));

        mainMenu.displayMenu();
    }
}
```

**Output:**
```
╭──────────────────────────────────────────╮
│                Main Menu                 │
│  Nav: Main Menu                          │
├──────────────────────────────────────────┤
│   [1]  Say Hello                         │
│   [2]  System Status                     │
│   [3]  Demo                              │
├──────────────────────────────────────────┤
│  [S] Setting  [E] Exit    [H] Help       │
╰──────────────────────────────────────────╯
> Enter here:
```

---

## API Reference

### `Menu`

| Method | Description |
|---|---|
| `new Menu(title, theme)` | Create a menu with title and theme |
| `addOption(label, action)` | Add a numbered menu item |
| `addSysOpt(label, action)` | Add a shortcut to the footer bar |
| `addSubMenu(menu)` | Add a submenu as a numbered option (auto-injects `[B] Back`) |
| `addSysSubMenu(label, menu)` | Add a submenu to the footer bar (auto-injects `[B] Back`) |
| `setTheme(theme)` | Change the theme at runtime |
| `setListPrinter(runnable)` | Print custom content above the menu box |
| `back()` | Close this menu and return to parent |
| `exit()` | Terminate the application |
| `displayMenu()` | Start the interactive loop |
| `getBreadCrumb()` | Get full path string e.g. `Home › Settings` |
| `getParent()` | Get the parent menu |
| `getSubMenus()` | Get list of registered submenus |

### Themes

| Theme | Border | Colors |
|---|---|---|
| `Theme.DEFAULT` | `SINGLE` `┌ ┐ └ ┘` | Green / Cyan / White |
| `Theme.COZY` | `SQUIRCLE` `╭ ╮ ╰ ╯` | Orange / Yellow / Orange |
| `Theme.CYBERPUNK` | `DOUBLE` `╔ ╗ ╚ ╝` | Magenta / Cyan / Magenta |

### Output Utilities

```java
import static com.ersikthy.consolemenu.util.Output.*;

success("Operation completed.");    // green badge
warn("Disk space low.");            // orange badge
error("Connection failed.");        // red badge
sysLog("Cache cleared.");           // gray badge
println("Custom text", Color.CYAN);
```

### Input Utilities

```java
import static com.ersikthy.consolemenu.util.Input.*;

String name  = readStr(scanner, "Name: ");
int    age   = readInt(scanner, "Age: ");
double price = readDouble(scanner, "Price: ");
```

---

## Displaying Special Characters in IntelliJ IDEA

By default, IntelliJ's Run console may show `?` or broken boxes instead of border characters like `╭ ╮ ╰ ╯ ├ ┤ │ ─`.

### Fix 1: Enable UTF-8 in the Run Console (Recommended)

Add the JVM option `-Dfile.encoding=UTF-8` to your Run Configuration:

1. Open **Run > Edit Configurations...**
2. Select your run config (e.g. `runExample`)
3. Click **Modify options > Add VM options**
4. Add: `-Dfile.encoding=UTF-8`
5. Click **Apply** → **Run**

Or add it directly in [`build.gradle.kts`](build.gradle.kts) so it applies to all runs:

```kotlin
tasks.register<JavaExec>("runExample") {
    group = "application"
    mainClass.set("com.ersikthy.consolemenu.example.Example")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    jvmArgs("-Dfile.encoding=UTF-8")   // <-- add this line
}
```

### Fix 2: Use IntelliJ's Built-in Terminal Instead

The built-in terminal renders Unicode correctly out of the box:

1. Open **View > Tool Windows > Terminal** (or press `` Alt+F12 ``)
2. Run the program directly:
```bash
./gradlew runExample
```

### Fix 3: Set UTF-8 as Default for All Projects

Go to **Help > Edit Custom VM Options...** and add:

```
-Dfile.encoding=UTF-8
-Dconsole.encoding=UTF-8
```

Restart IntelliJ after saving.

---

## Import in Other Projects

You can import `Fancy-CLI-Menu` into your projects using any of the following 3 ways:

### 1. IntelliJ IDEA (Plain Java Project)

If your project does not use Gradle or Maven:

1. Copy `Fancy-CLI-Menu-v2.0.0.jar` (from `build/libs/`) into your project (e.g., inside a `libs/` folder).
2. In IntelliJ IDEA:
   - **Quick method:** Right-click `Fancy-CLI-Menu-v2.0.0.jar` in the Project view → Select **Add as Library...** → Click **OK**.
   - **Manual method:** Go to **File** → **Project Structure...** (`Ctrl+Alt+Shift+S`) → **Libraries** → Click **`+`** → **Java** → Select the JAR file → **Apply**.

---

### 2. Maven (`pom.xml`)

**Via JitPack:**

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Ersikthy</groupId>
        <artifactId>Fancy-CLI-Menu</artifactId>
        <version>v2.0.0</version>
    </dependency>
</dependencies>
```

**Via Local JAR:**

```xml
<dependency>
    <groupId>com.github.Ersikthy</groupId>
    <artifactId>Fancy-CLI-Menu</artifactId>
    <version>v2.0.0</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/Fancy-CLI-Menu-v2.0.0.jar</systemPath>
</dependency>
```

---

### 3. Gradle

**Via JitPack:**

- **Kotlin DSL (`build.gradle.kts`):**
  ```kotlin
  repositories {
      mavenCentral()
      maven { url = uri("https://jitpack.io") }
  }

  dependencies {
      implementation("com.github.Ersikthy:Fancy-CLI-Menu:v2.0.0")
  }
  ```

- **Groovy DSL (`build.gradle`):**
  ```groovy
  repositories {
      mavenCentral()
      maven { url 'https://jitpack.io' }
  }

  dependencies {
      implementation 'com.github.Ersikthy:Fancy-CLI-Menu:v2.0.0'
  }
  ```

**Via Local JAR:**

Put `Fancy-CLI-Menu-v2.0.0.jar` into your project's `libs/` folder:

```kotlin
dependencies {
    implementation(files("libs/Fancy-CLI-Menu-v2.0.0.jar"))
}
```

---

## [License](./LICENSE)


MIT — free to use, modify, and distribute.
