package com.ersikthy.consolemenu.style;

import java.awt.*;

public record Theme(BorderStyle borderStyle, Color borderColor, Color titleColor, Color textColor) {

     public static final Theme DEFAULT = new Theme(
             BorderStyle.SINGLE,
             Color.GREEN,
             Color.CYAN,
             Color.WHITE
     );

     public static final Theme CYBERPUNK = new Theme(
             BorderStyle.DOUBLE,
             Color.MAGENTA,
             Color.CYAN,
             Color.MAGENTA
     );

     public static final Theme COZY = new Theme(
             BorderStyle.SQUIRCLE,
             Color.ORANGE,
             Color.YELLOW,
             Color.ORANGE
     );

}
