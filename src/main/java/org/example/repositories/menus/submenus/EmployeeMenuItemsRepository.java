package org.example.repositories.menus.submenus;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMenuItemsRepository {

    private final List<String> employeeMenuItems = new ArrayList<>();

    public List<String> getEmployeeMenuItemsItems() {
        return employeeMenuItems;
    }

    {
        employeeMenuItems.add("0. Return to main menu");
        employeeMenuItems.add("1. View All Employees");
        employeeMenuItems.add("2. View Employees By Branch");
        employeeMenuItems.add("3. Search For Certain Employee");
        employeeMenuItems.add("4. Add Employee");
        employeeMenuItems.add("5. Search for employee with certain score)");
        employeeMenuItems.add("6. Employee(s) of the month (employee(s) with highest score)");
    }
}
