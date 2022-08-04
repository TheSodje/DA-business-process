package org.example.repositories.menus;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepository {

    private final List<String> mainMenuItems = new ArrayList<>();

    public List<String> getMainMenuItems() {
        return mainMenuItems;
    }

    {
        mainMenuItems.add("0. Return to start menu");
        mainMenuItems.add("1. Employee functions  (view, edit, add employees, empl of the month)");
        mainMenuItems.add("2. Applications (get all applications, by branch, filter)");
        mainMenuItems.add("3. Workflow (view logistics)");
        mainMenuItems.add("9. Exit Program");
    }
}
