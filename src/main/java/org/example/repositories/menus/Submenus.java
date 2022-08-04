package org.example.repositories.menus;

import java.util.ArrayList;
import java.util.List;

public class Submenus {

    private final List<String> viewEmployeesMenu = new ArrayList<>();

    public List<String> getViewEmployeesMenu() {
        return viewEmployeesMenu;
    }

    {
        viewEmployeesMenu.add("0. Return To Employee Menu");
        viewEmployeesMenu.add("1. Order Employees By score (asc)");
        viewEmployeesMenu.add("2. Order Employees By score (desc)");
    }

    private final List<String> filterApplicationsMenuItems = new ArrayList<>();

    public List<String> getFilterApplicationsMenuItems() {
        return filterApplicationsMenuItems;
    }

    {
        filterApplicationsMenuItems.add("1. For Next Applicant");
        filterApplicationsMenuItems.add("0. Return To Applications Menu");
    }

    private final List<String> editWorkflowMenuItems = new ArrayList<>();

    public List<String> getEditWorkflowMenuItems() {
        return editWorkflowMenuItems;
    }

    {
        editWorkflowMenuItems.add("1. To add a node at the beginning");
        editWorkflowMenuItems.add("2. To add a node at the end");
        editWorkflowMenuItems.add("3. To add a node at a certain point");
        editWorkflowMenuItems.add("4. To replace an employee");
        editWorkflowMenuItems.add("0. Return Workflow");
    }


}
