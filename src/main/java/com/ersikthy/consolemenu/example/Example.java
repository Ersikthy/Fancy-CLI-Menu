package com.ersikthy.consolemenu.example;

import com.ersikthy.consolemenu.Menu;
import static com.ersikthy.consolemenu.util.Output.*;

public class Example {
    public static void main(String[] args) {
        // Create the main menu
        Menu mainMenu = new Menu("Main Menu Demo");
        
        mainMenu.addOption("Say Hello", () -> success("Hello, welcome to ConsoleMenu library!"));
        
        mainMenu.addOption("System Status", () -> {
            sysLog("All systems are operational.");
            warn("Note: High CPU load simulated.");
        });

        // Add a nested submenu
        mainMenu.addOption("Settings Submenu", () -> {
            Menu settingsMenu = new Menu("Settings");
            settingsMenu.addOption("Toggle Debug Mode", () -> success("Debug mode enabled."));
            settingsMenu.addOption("Clear Cache", () -> sysLog("Cache cleared successfully."));
            
            // Nested menu will display, and typing 'back' returns to Main Menu
            settingsMenu.displayMenu();
        });

        // Optional list printer to show custom information before menu choices
        mainMenu.setListPrinter(() -> {
            println("Active User: Developer", java.awt.Color.CYAN);
            println("App Version: 1.0.0", java.awt.Color.CYAN);
        });

        // Show the menu
        mainMenu.displayMenu();
    }
}
