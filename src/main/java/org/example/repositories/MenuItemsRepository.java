package org.example.repositories;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsRepository {

    List<String> mainMenuItems = new ArrayList<>();

    {
        mainMenuItems.add("0. Return to start menu");
    }

    public List<String> getMainMenuItems() {
        return mainMenuItems;
    }
}
