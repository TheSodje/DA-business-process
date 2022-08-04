package org.example.repositories.menus;

import java.util.ArrayList;
import java.util.List;

public class ApplicationsMenuItemsRepository {

    private final List<String> applicationsMenuItems = new ArrayList<>();

    public List<String> getApplicationsMenuItems() {
        return applicationsMenuItems;
    }

    {
        applicationsMenuItems.add("0. Return to main menu");
        applicationsMenuItems.add("1. Get All Applications");
        applicationsMenuItems.add("2. View Applications By Branch");
        applicationsMenuItems.add("3. Filter Through Applicants");
    }
}
