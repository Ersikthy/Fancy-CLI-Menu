package com.ersikthy.consolemenu.core;

import com.ersikthy.consolemenu.Menu.MenuItem;
import com.ersikthy.consolemenu.style.BorderStyle;
import com.ersikthy.consolemenu.style.Theme;
import com.ersikthy.consolemenu.util.AnsiColor;

import java.awt.Color;
import java.util.List;

import static com.ersikthy.consolemenu.util.AnsiColor.BOLD;
import static com.ersikthy.consolemenu.util.Output.println;

public class Draw {
     private Draw() {
     }

     public static void clearScreen() {
          System.out.print("\u001B[H\u001B[2J");
          System.out.flush();
     }

     public static int calcWidth(String breadCrumb, List<MenuItem> items, List<MenuItem> itemSys) {
          int max = ("  Navigation: " + (breadCrumb != null ? breadCrumb : "")).length();

          if (items != null) {
               for (int i = 0; i < items.size(); i++) {
                    MenuItem item = items.get(i);
                    if (item != null && item.getLabel() != null) {
                         int len = String.format("   [%d]  %s", i + 1, item.getLabel()).length();
                         if (max < len) max = len;
                    }
               }
          }

          if (itemSys != null && !itemSys.isEmpty()) {
               int navLen = 2;
               for (MenuItem item : itemSys) {
                    if (item != null && item.getLabel() != null) {
                         String key = String.valueOf(item.getLabel().charAt(0));
                         navLen += String.format("[%s] %-8s ", key, item.getLabel()).length();
                    }
               }
               if (max < navLen) max = navLen;
          }

          return Math.max(0, max + 6);
     }

     public static void drawTopBorder(BorderStyle border, int width, Color borderColor) {
          String line = border.getTopLeft() + border.getHorizons().repeat(width) + border.getTopRight();
          println(line, borderColor, null, BOLD);
     }

     public static void drawNavigation(String breadCrumb, BorderStyle border, int width, Theme theme) {
          String content = "  Nav: " + (breadCrumb != null ? breadCrumb : "");
          int remaining = width - content.length();
          if (remaining > 0) {
               content += " ".repeat(remaining);
          }
          String borders = AnsiColor.textFormat(border.getVertical(), theme.borderColor(), null);
          String text = AnsiColor.textFormat(content, Color.GRAY, null, AnsiColor.ITALIC);
          System.out.println(borders + text + borders);
     }

     public static void drawDivider(BorderStyle border, int width, Color borderColor) {
          String line = border.getVertical() + border.getHorizons().repeat(width) + border.getVertical();
          println(line, borderColor, null, BOLD);
     }


     public static void drawItem(MenuItem item, int index, BorderStyle border, int width, Theme theme) {
          String content = String.format("   [%d]  %s", index, item.getLabel());
          int remaining = width - content.length();
          if (remaining > 0) {
               content += " ".repeat(remaining);
          }

          String borders = AnsiColor.textFormat(border.getVertical(), theme.borderColor(), null);
          String text = AnsiColor.textFormat(content, theme.textColor(), null);
          System.out.println(borders + text + borders);
     }

     public static void drawNavigationRow(List<MenuItem> itemSys, BorderStyle border, int width, Theme theme) {
          StringBuilder row = new StringBuilder("  ");
          for (MenuItem item : itemSys) {
               String key = String.valueOf(item.getLabel().charAt(0));
               row.append(String.format("[%s] %-8s ", key, item.getLabel()));
          }

          int remaining = width - row.length();
          if (remaining > 0) {
               row.repeat(" ", remaining);
          }

          String borders = AnsiColor.textFormat(border.getVertical(), theme.borderColor(), null);
          String text = AnsiColor.textFormat(row.toString(), theme.textColor(), null);
          System.out.println(borders + text + borders);
     }

     public static void drawBottomBorder(BorderStyle border, int width, Color borderColor) {
          String line = border.getBottomLeft() + border.getHorizons().repeat(width) + border.getBottomRight();
          println(line, borderColor, null, BOLD);
     }

     public static void drawTitle(String title, BorderStyle border, int width, Color titleColor, Color borderColor) {
          int titleLen = title != null ? title.length() : 0;
          int padding = Math.max(0, (width - titleLen) / 2);
          int rightPadding = Math.max(0, width - titleLen - padding);
          String centered = " ".repeat(padding) + (title != null ? title : "") + " ".repeat(rightPadding);
          String borders = AnsiColor.textFormat(border.getVertical(), borderColor, null, BOLD);
          String text = AnsiColor.textFormat(centered, titleColor, null, BOLD);
          System.out.println(borders + text + borders);
     }

     public static void render(String title, String breadCrumb, List<MenuItem> items, List<MenuItem> itemSys, Theme theme) {
          if (theme == null) {
               theme = Theme.COZY;
          }
          BorderStyle border = theme.borderStyle();
          Color borderColor = theme.borderColor();
          int width = calcWidth(breadCrumb, items, itemSys);
          drawTopBorder(border, width, borderColor);

          drawTitle(title, border, width, theme.titleColor(), borderColor);

          drawNavigation(breadCrumb != null ? breadCrumb : title, border, width, theme);
          drawDivider(border, width, borderColor);
          if (items != null) {
               for (int i = 0; i < items.size(); i++) {
                    drawItem(items.get(i), i + 1, border, width, theme);
               }
          }

          if (itemSys != null && !itemSys.isEmpty()) {
               drawDivider(border, width, borderColor);
               drawNavigationRow(itemSys, border, width, theme);
          }
          drawBottomBorder(border, width, borderColor);
     }
}