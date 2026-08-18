package com.ersikthy.consolemenu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ersikthy.consolemenu.util.Output.*;
import static com.ersikthy.consolemenu.util.Input.*;

public class Menu {
    @FunctionalInterface
    public interface MenuAction {
        void execute();
    }

    public static class MenuItem {
        private final String label;
        private final MenuAction action;

        public MenuItem(String label, MenuAction action) {
            this.label = label;
            this.action = action;
        }

        public String getLabel() {
            return label;
        }

        public void run() {
            action.execute();
        }
    }

    private final String title;
    private final List<MenuItem> items = new ArrayList<>();
    private final Scanner scanner;
    private Runnable listPrinter;
    private boolean exitOnQuit = true;

    public void setListPrinter(Runnable listPrinter) {
        this.listPrinter = listPrinter;
    }

    public void setExitOnQuit(boolean exitOnQuit) {
        this.exitOnQuit = exitOnQuit;
    }

    public boolean isExitOnQuit() {
        return exitOnQuit;
    }

    public Menu(String title) {
        this(title, new Scanner(System.in));
    }

    public Menu(String title, Scanner scanner) {
        this.title = title;
        this.scanner = scanner;
    }

    public void addOption(String label, MenuAction action) {
        items.add(new MenuItem(label, action));
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public String getTitle() {
        return title;
    }

    public void displayMenu() {
        while (true) {
            println("=========" + title + "=========", null);

            if (listPrinter != null) {
                listPrinter.run();
                println("------------------", null);
            }

            for (int i = 0; i < items.size(); i++) {
                printf("[%d].%s", (i + 1), items.get(i).getLabel() + "\n");
            }

            println("Type: exit to end this program or back to return", Color.GRAY);

            String rawInput = readStr(scanner, "Enter here: ");

            if (rawInput.equalsIgnoreCase("exit")) {
                print("Exiting...", null);
                if (exitOnQuit) {
                    System.exit(0);
                } else {
                    break;
                }
            }

            if (rawInput.equalsIgnoreCase("back")) {
                println("Returning...", null);
                break;
            }

            try {
                int input = Integer.parseInt(rawInput);

                if (input > 0 && input <= items.size()) {
                    items.get(input - 1).run();
                } else {
                    sysLog("Unknown option");
                }
            } catch (NumberFormatException e) {
                sysLog("Please enter a valid number! \nOr type: \n- Back to return. \n- Exit to end program");
            }
        }
    }
}
