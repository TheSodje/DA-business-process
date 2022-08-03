package org.example.repositories;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepository {

    private final List<String> mainMenuItems = new ArrayList<>();

    public List<String> getMainMenuItems() {
        return mainMenuItems;
    }

    {
        mainMenuItems.add("0. Return to start menu");
        mainMenuItems.add("1. View all employees");
        mainMenuItems.add("2. Add employee");
        mainMenuItems.add("3. View Applications");
        mainMenuItems.add("4. WorkFlows");
        mainMenuItems.add("9. Exit Program");
    }
}
