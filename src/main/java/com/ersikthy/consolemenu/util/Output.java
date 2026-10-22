package com.ersikthy.consolemenu.util;

import static com.ersikthy.consolemenu.util.AnsiColor.textFormat;
import static com.ersikthy.consolemenu.util.AnsiColor.*;

import java.awt.*;

public class Output {
    private Output() {}

    public static void print(Object prompt) {
        print(prompt, null, null);
    }

    public static void print(Object prompt, Color color) {
        print(prompt, color, null);
    }

    public static void print(Object prompt, Color color, Color bg, String... styles) {
        System.out.print(textFormat(prompt, color, bg, styles));
    }

    public static void println(Object prompt) {
        println(prompt, null, null);
    }

    public static void println(Object prompt, Color color) {
        println(prompt, color, null);
    }

    public static void println(Object prompt, Color color, Color bg, String... styles) {
        System.out.println(textFormat(prompt, color, bg, styles));
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void success(Object prompt) {
        System.out.println(textFormat("[SUCCESS]: " + prompt, Color.black, Color.green, BOLD));
    }

    public static void warn(Object prompt) {
        System.out.println(textFormat("[WARN]: " + prompt, Color.black, Color.orange, BOLD));
    }

    public static void error(Object prompt) {
        System.out.println(textFormat("[ERROR]: " + prompt, Color.black, Color.red, BOLD));
    }

    public static void sysLog(Object prompt) {
        System.out.println(textFormat("[SYSTEM]: " + prompt, Color.black, Color.gray, BOLD));
    }
}
