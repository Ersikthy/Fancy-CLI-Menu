package com.ersikthy.consolemenu.util;

import static com.ersikthy.consolemenu.util.TextColor.*;
import java.awt.Color;

public class Output {
    private Output() {
    }

    public static void print(Object prompt, Color color) {
        System.out.print(painting(prompt, color));
    }

    public static void println(Object prompt, Color color) {
        System.out.println(painting(prompt, color));
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void success(Object prompt) {
        System.out.println(painting("[SUCCESS]: " + prompt, Color.BLUE));
    }

    public static void warn(Object prompt) {
        System.out.println(painting("[WARN]: " + prompt, Color.ORANGE));
    }

    public static void error(Object prompt) {
        System.out.println(painting("[ERROR]: " + prompt, Color.RED));
    }

    public static void sysLog(Object prompt) {
        System.out.println(painting("[SYSTEM]: " + prompt, Color.GRAY));
    }
}
