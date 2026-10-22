package com.ersikthy.consolemenu.example;

import com.ersikthy.consolemenu.Menu;
import com.ersikthy.consolemenu.style.Theme;

import static com.ersikthy.consolemenu.util.Output.*;

public class Example {
    public static void main(String[] args) {
        Menu mainMenu = new Menu("Main Menu Demo", Theme.DEFAULT);

        mainMenu.addOption("Say Hello", () -> success("Hello, welcome to ConsoleMenu library!"));
        mainMenu.addOption("System Status", () -> {
            sysLog("All systems are operational.");
            warn("Note: High CPU load simulated.");
        });

        Menu demoSubmenu = new Menu("Demo Submenu", Theme.DEFAULT);
        demoSubmenu.addOption("Toggle Debug Mode", () -> success("Debug mode enabled."));
        demoSubmenu.addOption("Clear Cache", () -> sysLog("Cache cleared successfully."));
        demoSubmenu.addSysOpt("Exit", demoSubmenu::exit);
        mainMenu.addSubMenu(demoSubmenu);

        Menu settingMenu = new Menu("Setting Menu", Theme.DEFAULT);
        settingMenu.addOption("Change Theme to Cyberpunk", () -> {
            settingMenu.setTheme(Theme.CYBERPUNK);
            success("Theme updated to CYBERPUNK!");
        });
        settingMenu.addSysOpt("Exit", settingMenu::exit);

        mainMenu.addSysSubMenu("Setting", settingMenu);
        mainMenu.addSysOpt("Exit", mainMenu::exit);
        mainMenu.addSysOpt("Help", () -> sysLog("Help: Type number or [S], [E] to navigate."));

        mainMenu.displayMenu();
    }
}