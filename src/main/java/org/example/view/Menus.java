package org.example.view;

import org.example.entity.Employee;
import org.example.repositories.menus.*;
import org.example.service.ApplicationService;
import org.example.service.EmployeeService;
import org.example.service.WorkflowService;
import org.example.util.enums.Branch;

import java.util.Arrays;
import java.util.Scanner;

public class Menus {

    private final Scanner scanner;
    private final MenuItemsRepository menuItemsRepository;
    private final EmployeeService employeeService;
    private final ApplicationService applicationService;
    private final WorkflowService workflowService;
    public Menus(Scanner scanner, MenuItemsRepository menuItemsRepository,
                 EmployeeService employeeService, ApplicationService applicationService, WorkflowService workflowService) {
        this.scanner = scanner;
        this.menuItemsRepository = menuItemsRepository;
        this.employeeService = employeeService;
        this.applicationService = applicationService;
        this.workflowService = workflowService;
    }

    private final EmployeeMenuItemsRepository employeeMenuItemsRepository = new EmployeeMenuItemsRepository();
    private final ApplicationsMenuItemsRepository applicationsMenuItemsRepository = new ApplicationsMenuItemsRepository();
    private final WorkflowMenuItemsRepository workflowMenuItemsRepository = new WorkflowMenuItemsRepository();
    private final Submenus submenus = new Submenus();


    public void startMenu() {
//        System.out.println("\n" +
//                "          _____                    _____                    _____          \n" +
//                "         /\\    \\                  /\\    \\                  /\\    \\         \n" +
//                "        /::\\____\\                /::\\    \\                /::\\____\\        \n" +
//                "       /:::/    /               /::::\\    \\              /::::|   |        \n" +
//                "      /:::/    /               /::::::\\    \\            /:::::|   |        \n" +
//                "     /:::/    /               /:::/\\:::\\    \\          /::::::|   |        \n" +
//                "    /:::/____/               /:::/__\\:::\\    \\        /:::/|::|   |        \n" +
//                "   /::::\\    \\              /::::\\   \\:::\\    \\      /:::/ |::|   |        \n" +
//                "  /::::::\\    \\   _____    /::::::\\   \\:::\\    \\    /:::/  |::|___|______  \n" +
//                " /:::/\\:::\\    \\ /\\    \\  /:::/\\:::\\   \\:::\\____\\  /:::/   |::::::::\\    \\ \n" +
//                "/:::/  \\:::\\    /::\\____\\/:::/  \\:::\\   \\:::|    |/:::/    |:::::::::\\____\\\n" +
//                "\\::/    \\:::\\  /:::/    /\\::/   |::::\\  /:::|____|\\::/    / ~~~~~/:::/    /\n" +
//                " \\/____/ \\:::\\/:::/    /  \\/____|:::::\\/:::/    /  \\/____/      /:::/    / \n" +
//                "          \\::::::/    /         |:::::::::/    /               /:::/    /  \n" +
//                "           \\::::/    /          |::|\\::::/    /               /:::/    /   \n" +
//                "           /:::/    /           |::| \\::/____/               /:::/    /    \n" +
//                "          /:::/    /            |::|  ~|                    /:::/    /     \n" +
//                "         /:::/    /             |::|   |                   /:::/    /      \n" +
//                "        /:::/    /              \\::|   |                  /:::/    /       \n" +
//                "        \\::/    /                \\:|   |                  \\::/    /        \n" +
//                "         \\/____/                  \\|___|                   \\/____/         \n" +
//                "                                                                           \n");
//
//        System.out.println("Input your firstname and press enter to continue\n");
//        String firstname = scanner.nextLine();
//        System.out.println("Welcome " + firstname + "\n");
        mainMenu();
    }

    public void mainMenu() {
        System.out.println();
        System.out.println("What would you like to do?\n");

        menuItemsRepository.getMainMenuItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                startMenu();
                break;
            case "1":

                submenus.getViewEmployeesMenu().forEach(System.out::println);
//                employeeService.getAllEmployees().forEach(System.out::println);
//                mainMenu();
                break;
            case "2":
                Employee employee = new Employee();

                System.out.println("id:");
                String employeeId = scanner.nextLine();
                employee.setEmployeeId(employeeId);

                System.out.println("full name:");
                String fullName = scanner.nextLine();
                employee.setFullName(fullName);

                System.out.println("Score:");
                byte score = scanner.nextByte();

                employee.getEmployeeScore(score);

                System.out.println(Arrays.toString(Branch.values()));
                System.out.println("Type in the branch");

                scanner.nextLine();
                String branch = scanner.nextLine();
                switch (branch.toUpperCase()) {
                    case "IT":
                        employee.setBranch(Branch.IT);
                        break;
                    case "SALES":
                        employee.setBranch(Branch.SALES);
                        break;
                    case "FINANCE":
                        employee.setBranch(Branch.FINANCE);
                        break;
                }

                employeeService.addEmployee(employee);
                mainMenu();
                break;
            case "3":
                applicationService.getApplications().forEach(System.out::println);
                mainMenu();
            case "4":
                workflowService.viewWorkFlow();
                mainMenu();
            case "9":
                System.exit(1);
            default:
                System.err.println("Invalid item number try again\n");
                mainMenu();
                break;
        }
    }

    public void viewAllEmployeesMenu() {
        System.out.println();

        menuItemsRepository.getMainMenuItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                startMenu();
                break;
            case "1":
                employeeService.getAllEmployees().forEach(System.out::println);
                mainMenu();
                break;
            case "9":
                System.exit(1);
            default:
                System.err.println("Invalid item number try again\n");
                mainMenu();
                break;
        }
    }
    public void employeeMenu() {
        System.out.println();

        employeeMenuItemsRepository.getEmployeeMenuItemsItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                mainMenu();
                break;
            case "1":
                submenus.getViewEmployeesMenu().forEach(System.out::println);
                String response = scanner.nextLine();
                if (response.equals("1")){
//                  order empl by score (ascending)
                } else if(response.equals("2")) {
//                    order empl by score (desc)
                } else if(response.equals("0")) {
                    employeeMenu();
                } else {
                    System.out.println("Invalid number chosen");
                }
                break;
            case "2":
                System.out.println("Which branch do you want to see");
                String branch = scanner.nextLine();
//                method to order by branch in params
                employeeMenu();
                break;
            case "3":
//                Search for employee
                String employee = scanner.nextLine();
//                method to find employee (in params)
                employeeMenu();
            case "4":

                workflowService.viewWorkFlow();
                mainMenu();
            case "9":
                System.exit(1);
            default:
                System.err.println("Invalid item number try again\n");
                mainMenu();
                break;
        }
    }

}
