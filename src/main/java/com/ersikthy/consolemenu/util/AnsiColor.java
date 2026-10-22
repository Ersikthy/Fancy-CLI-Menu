package com.ersikthy.consolemenu.util;

import java.awt.*;

public class AnsiColor {
     private AnsiColor() {
     }

     public static final String DEFAULT = "\u001B[0m";
     public static final String RESET = "\u001B[0m";
     public static final String ITALIC = "\u001B[3m";
     public static final String BOLD = "\u001B[1m";
     public static final String UNDERLINE = "\u001B[4m";
     public static final String STRIKETHROUGH = "\u001B[9m";
     public static final String HIDDEN = "\u001B[8m";

     public static String textFormat(Object prompt, Color color, Color bg, String... styles) {
          if (prompt == null) return "";

          StringBuilder stringBuilder = new StringBuilder();

          if (styles != null) {
               for (String style : styles) {
                    if (style != null) stringBuilder.append(style);
               }
          }

          if (color != null) {
               stringBuilder.append(String.format(
                       "\u001B[38;2;%d;%d;%dm",
                       color.getRed(),
                       color.getGreen(),
                       color.getBlue()
               ));
          }

          if (bg != null) {
               stringBuilder.append(String.format(
                       "\u001B[48;2;%d;%d;%dm",
                       bg.getRed(),
                       bg.getGreen(),
                       bg.getBlue()
               ));
          }

          stringBuilder.append(prompt).append(DEFAULT);

          return stringBuilder.toString();
     }
}

