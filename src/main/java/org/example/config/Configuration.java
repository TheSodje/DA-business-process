package org.example.config;

import org.example.repositories.EmployeeRepository;
import org.example.repositories.MenuItemsRepository;
import org.example.service.EmployeeService;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.view.MainMenu;

import java.util.Scanner;

public class Configuration {

    private final Scanner scanner = new Scanner(System.in);
    private final MenuItemsRepository menuItemsRepository = new MenuItemsRepository();
    private final EmployeeRepository employeeRepository = new EmployeeRepository();

    public MainMenu menu() {
        return new MainMenu(scanner, menuItemsRepository, employeeService());
    }

    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(employeeRepository);
    }
}
