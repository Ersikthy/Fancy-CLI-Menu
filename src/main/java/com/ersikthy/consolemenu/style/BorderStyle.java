package com.ersikthy.consolemenu.style;

public enum BorderStyle {
     NONE(" ", " ", " ", " ", " ", " "),

     SINGLE("┌", "┐", "└", "┘", "─", "│"),

     CLASSIC("+", "+", "+", "+", "─", "│"),

     SQUIRCLE("╭", "╮", "╰", "╯", "─", "│"),

     DOUBLE("╔", "╗", "╚", "╝", "═", "║");

     private final String topLeft;
     private final String topRight;
     private final String bottomLeft;
     private final String bottomRight;
     private final String horizons;
     private final String vertical;

     BorderStyle(
             String topLeft,
             String topRight,
             String bottomLeft,
             String bottomRight,
             String horizons,
             String vertical) {
          this.topLeft = topLeft;
          this.topRight = topRight;
          this.bottomLeft = bottomLeft;
          this.bottomRight = bottomRight;
          this.horizons = horizons;
          this.vertical = vertical;
     }

     public String getTopLeft() {
          return topLeft;
     }

     public String getTopRight() {
          return topRight;
     }

     public String getBottomLeft() {
          return bottomLeft;
     }

     public String getBottomRight() {
          return bottomRight;
     }

     public String getHorizons() {
          return horizons;
     }

     public String getVertical() {
          return vertical;
     }
}
