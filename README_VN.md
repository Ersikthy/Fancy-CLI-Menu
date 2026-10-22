Ngôn ngữ: [Vietmanese] | [English](README.md)

# Fancy-CLI-Menu

Một thư viện Java nhẹ, giúp xây dựng menu tương tác trên Terminal/CLI với giao diện theo chủ đề, điều hướng breadcrumb và menu con lồng nhau.

## Tính năng

- **Giao diện khung viền theo chủ đề** — Menu được định dạng với các kiểu viền `SINGLE`, `DOUBLE`, `SQUIRCLE` và `CLASSIC`.
- **Điều hướng Breadcrumb** — Tự động hiển thị đường dẫn như `Home › Rooms › Settings`.
- **Menu con lồng nhau** — Liên kết các menu theo cấu trúc cha-con; menu con tự động có phím tắt `[B] Back`.
- **Thanh điều hướng phía dưới** — Hiển thị các phím tắt hệ thống (`[B] Back`, `[E] Exit`, `[S] Setting`) trong một hàng chân menu gọn gàng.
- **Đầu ra có màu sắc** — Hỗ trợ màu RGB ANSI cùng các nhãn `success`, `warn`, `error` và `sysLog`.
- **Kiểm tra dữ liệu đầu vào** — Các hàm hỗ trợ `Scanner` an toàn cho kiểu `String`, `int`, `double`, `Long` và `Float`.

---

## Bắt đầu nhanh

**Yêu cầu:** Java 17 trở lên, Gradle (đã bao gồm Gradle Wrapper).

```bash
# Build dự án
./gradlew build

# Chạy chương trình demo có sẵn
./gradlew runExample
```

---

## Hướng dẫn sử dụng

```java
import com.ersikthy.consolemenu.Menu;
import com.ersikthy.consolemenu.style.Theme;
import static com.ersikthy.consolemenu.util.Output.*;

public class Main {
    public static void main(String[] args) {

        Menu mainMenu = new Menu("Main Menu", Theme.COZY);
        mainMenu.addOption("Say Hello", () -> success("Hello!"));
        mainMenu.addOption("System Status",
                () -> sysLog("All systems operational."));

        // Thêm menu con dưới dạng một tùy chọn được đánh số
        Menu demoMenu = new Menu("Demo", Theme.COZY);
        demoMenu.addOption("Toggle Debug",
                () -> success("Debug on."));
        demoMenu.addSysOpt("Exit", demoMenu::exit);
        mainMenu.addSubMenu(demoMenu);

        // Thêm menu con vào thanh điều hướng phía dưới
        // Nhấn [S] để mở menu
        Menu settingMenu = new Menu("Settings", Theme.COZY);
        settingMenu.addOption("Switch to Cyberpunk", () -> {
            settingMenu.setTheme(Theme.CYBERPUNK);
            success("Theme updated!");
        });
        mainMenu.addSysSubMenu("Setting", settingMenu);

        // Thêm các phím tắt ở chân menu
        mainMenu.addSysOpt("Exit", mainMenu::exit);
        mainMenu.addSysOpt("Help",
                () -> sysLog("Type a number or shortcut key."));

        mainMenu.displayMenu();
    }
}
```

**Kết quả hiển thị:**

```text
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

## Tài liệu API

### `Menu`

| Phương thức | Mô tả |
|---|---|
| `new Menu(title, theme)` | Tạo menu với tiêu đề và chủ đề giao diện |
| `addOption(label, action)` | Thêm một mục menu được đánh số |
| `addSysOpt(label, action)` | Thêm phím tắt vào thanh điều hướng phía dưới |
| `addSubMenu(menu)` | Thêm menu con dưới dạng một mục được đánh số; tự động thêm `[B] Back` |
| `addSysSubMenu(label, menu)` | Thêm menu con vào thanh điều hướng phía dưới; tự động thêm `[B] Back` |
| `setTheme(theme)` | Thay đổi chủ đề giao diện khi chương trình đang chạy |
| `setListPrinter(runnable)` | In nội dung tùy chỉnh phía trên khung menu |
| `back()` | Đóng menu hiện tại và quay về menu cha |
| `exit()` | Kết thúc ứng dụng |
| `displayMenu()` | Bắt đầu vòng lặp tương tác của menu |
| `getBreadCrumb()` | Lấy chuỗi đường dẫn đầy đủ, ví dụ `Home › Settings` |
| `getParent()` | Lấy menu cha |
| `getSubMenus()` | Lấy danh sách các menu con đã đăng ký |

### Chủ đề giao diện (Themes)

| Chủ đề | Kiểu viền | Màu sắc |
|---|---|---|
| `Theme.DEFAULT` | `SINGLE` — `┌ ┐ └ ┘` | Xanh lá / Xanh cyan / Trắng |
| `Theme.COZY` | `SQUIRCLE` — `╭ ╮ ╰ ╯` | Cam / Vàng / Cam |
| `Theme.CYBERPUNK` | `DOUBLE` — `╔ ╗ ╚ ╝` | Hồng tím / Xanh cyan / Hồng tím |

### Tiện ích xuất dữ liệu (Output Utilities)

```java
import static com.ersikthy.consolemenu.util.Output.*;

success("Operation completed.");  // Nhãn màu xanh lá
warn("Disk space low.");          // Nhãn màu cam
error("Connection failed.");      // Nhãn màu đỏ
sysLog("Cache cleared.");         // Nhãn màu xám
println("Custom text", Color.CYAN);
```

### Tiện ích nhập dữ liệu (Input Utilities)

```java
import static com.ersikthy.consolemenu.util.Input.*;

String name  = readStr(scanner, "Name: ");
int age      = readInt(scanner, "Age: ");
double price = readDouble(scanner, "Price: ");
```

---

## Hiển thị ký tự đặc biệt trong IntelliJ IDEA

Theo mặc định, cửa sổ Run của IntelliJ IDEA có thể hiển thị ký tự `?` hoặc các ô vuông bị lỗi thay cho những ký tự viền như `╭ ╮ ╰ ╯ ├ ┤ │ ─`.

### Cách 1: Bật UTF-8 cho Run Console (Khuyến nghị)

Thêm tùy chọn JVM `-Dfile.encoding=UTF-8` vào cấu hình chạy chương trình:

1. Mở **Run > Edit Configurations...**
2. Chọn cấu hình chạy, ví dụ `runExample`.
3. Nhấn **Modify options > Add VM options**.
4. Thêm `-Dfile.encoding=UTF-8`.
5. Nhấn **Apply** rồi chạy lại chương trình.

Hoặc thêm trực tiếp vào file [`build.gradle.kts`](build.gradle.kts) để áp dụng cho tác vụ chạy demo:

```kotlin
tasks.register<JavaExec>("runExample") {
    group = "application"
    mainClass.set("com.ersikthy.consolemenu.example.Example")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
    jvmArgs("-Dfile.encoding=UTF-8") // Thêm dòng này
}
```

### Cách 2: Sử dụng Terminal tích hợp của IntelliJ

Terminal tích hợp thường hiển thị Unicode tốt hơn trong các môi trường đã được cấu hình UTF-8.

1. Mở **View > Tool Windows > Terminal** hoặc nhấn `Alt+F12`.
2. Chạy chương trình:

```bash
./gradlew runExample
```

### Cách 3: Đặt UTF-8 làm mặc định cho tất cả dự án

1. Mở **Help > Edit Custom VM Options...**.
2. Thêm các dòng sau:

```text
-Dfile.encoding=UTF-8
-Dconsole.encoding=UTF-8
```

3. Lưu file và khởi động lại IntelliJ IDEA.

---

## Tích hợp vào các dự án khác

Bạn có thể tích hợp `Fancy-CLI-Menu` vào dự án bằng một trong ba cách sau.

### 1. IntelliJ IDEA (dự án Java thông thường)

Áp dụng nếu dự án của bạn không sử dụng Gradle hoặc Maven.

1. Sao chép file `Fancy-CLI-Menu-v2.0.0.jar` từ thư mục `build/libs/` vào dự án, ví dụ thư mục `libs/`.
2. Trong IntelliJ IDEA, chọn một trong hai cách:

   - **Cách nhanh:** Nhấp chuột phải vào `Fancy-CLI-Menu-v2.0.0.jar` trong cửa sổ Project, chọn **Add as Library...** rồi nhấn **OK**.
   - **Cách thủ công:** Mở **File > Project Structure...** (`Ctrl+Alt+Shift+S`) → **Libraries** → nhấn **+** → chọn **Java** → chọn file JAR → **Apply**.

---

### 2. Maven (`pom.xml`)

**Thông qua JitPack:**

Thêm repository và dependency sau vào file `pom.xml`:

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

**Thông qua file JAR cục bộ:**

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

**Thông qua JitPack:**

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

**Thông qua file JAR cục bộ:**

Đặt file `Fancy-CLI-Menu-v2.0.0.jar` trong thư mục `libs/` của dự án, sau đó thêm:

```kotlin
dependencies {
    implementation(files("libs/Fancy-CLI-Menu-v2.0.0.jar"))
}
```

---

## Giấy phép

[MIT License](./LICENSE)

Miễn phí sử dụng, chỉnh sửa và phân phối theo các điều khoản của giấy phép MIT.
