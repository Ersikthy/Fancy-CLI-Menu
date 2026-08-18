package com.ersikthy.consolemenu.util;

import java.awt.Color;

public class TextColor {
    private TextColor() {
    }

    static final String RESET = "\u001B[0m";

    public static Object painting(Object prompt, Color inputColor) {
        if (inputColor == null) return prompt;

        return String.format(
                "\u001B[38;2;%d;%d;%dm%s%s",
                inputColor.getRed(),
                inputColor.getGreen(),
                inputColor.getBlue(),
                prompt,
                RESET
        );
    }
}
