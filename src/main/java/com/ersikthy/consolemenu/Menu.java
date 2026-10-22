package com.ersikthy.consolemenu;

import com.ersikthy.consolemenu.core.Draw;
import com.ersikthy.consolemenu.style.Theme;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
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
    private final List<MenuItem> itemsSys = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private Runnable listPrinter;
    private boolean isClose = false;
    private Theme theme;
    private static final List<Menu> subMenus = new ArrayList<>();
    private Menu parent;
    public void setListPrinter(Runnable listPrinter) {
        this.listPrinter = listPrinter;
    }

    public Menu getParent() {
        return parent;
    }

    public List<Menu> getSubMenus() {
        return Collections.unmodifiableList(subMenus);
    }

    public String getBreadCrumb() {
        if (parent == null) {
            return title;
        }
        return parent.getBreadCrumb() + " › " + title;
    }

    public String getTitle() {
        return title;
    }

    public void addSubMenu(Menu subMenu) {
        addSubMenu(subMenu.getTitle() , subMenu);
    }

    public void addSubMenu(String label, Menu subMenu) {
        subMenu.parent = this;
        subMenus.add(subMenu);

        boolean hasBack = subMenu.itemsSys.stream().anyMatch(item -> item.getLabel().equalsIgnoreCase("Back"));
//        boolean hasSetting = subMenu.itemsSys.stream().anyMatch(item -> item.getLabel().equalsIgnoreCase("Setting"));
        if (!hasBack) {
            subMenu.addSysOpt("Back", subMenu::back);
        }

        this.addOption(label, subMenu::displayMenu);
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void back() {
        println("Returning...");
        this.isClose = false;
    }

    public void exit() {
        println("Exiting...");
        System.exit(0);
    }

    public Menu(String title, Theme theme) {
        this.title = title;
        this.theme = theme;
    }

    public void addOption(String label, MenuAction action) {
        items.add(new MenuItem(label, action));
    }

    public void addSysOpt(String label, MenuAction action) {
        itemsSys.add(new MenuItem(label, action));
    }

    public void addSysSubMenu(Menu subMenu) {
        addSysSubMenu(subMenu.getTitle(), subMenu);
    }

    public void addSysSubMenu(String label, Menu subMenu) {
        subMenu.parent = this;
        subMenus.add(subMenu);

        boolean hasBack = subMenu.itemsSys.stream()
                .anyMatch(item -> item.getLabel().equalsIgnoreCase("Back"));
        if (!hasBack) {
            subMenu.addSysOpt("Back", subMenu::back);
        }

        this.addSysOpt(label, subMenu::displayMenu);
    }

    public void displayMenu() {
        this.isClose = true;
        while (this.isClose) {
            Draw.clearScreen();

            if (listPrinter != null) {
                listPrinter.run();
                println("------------------", Color.GRAY);
            }


            Draw.render(title, getBreadCrumb(), items, itemsSys, theme);

            String rawInput = readStr(scanner, "> Enter here: ");

            boolean handled = false;
            for (MenuItem item : itemsSys) {
                if (rawInput.equalsIgnoreCase(String.valueOf(item.getLabel().charAt(0)))) {
                    item.run();
                    handled = true;
                    break;
                }
            }
            if (handled) {
                continue;
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
