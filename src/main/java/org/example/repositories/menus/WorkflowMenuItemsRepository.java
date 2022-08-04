package org.example.repositories.menus;

import java.util.ArrayList;
import java.util.List;

public class WorkflowMenuItemsRepository {

    private final List<String> workflowMenuItems = new ArrayList<>();

    public List<String> getWorkflowMenuItems() {
        return workflowMenuItems;
    }

    {
        workflowMenuItems.add("0. Return to main menu");
        workflowMenuItems.add("1. View Workflow");
    }
}
