package org.example.view;

import org.example.entity.Application;
import org.example.entity.Employee;
import org.example.repositories.menus.MenuItemsRepository;
import org.example.repositories.menus.submenus.ApplicationsMenuItemsRepository;
import org.example.repositories.menus.submenus.EmployeeMenuItemsRepository;
import org.example.repositories.menus.submenus.Submenus;
import org.example.repositories.menus.submenus.WorkflowMenuItemsRepository;
import org.example.service.ApplicationService;
import org.example.service.EmployeeService;
import org.example.service.WorkflowService;
import org.example.util.enums.Branch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menus {

    private final Scanner scanner;
    private final EmployeeService employeeService;
    private final ApplicationService applicationService;
    private final WorkflowService workflowService;


    private final MenuItemsRepository menuItemsRepository;
    private final EmployeeMenuItemsRepository employeeMenuItemsRepository;
    private final ApplicationsMenuItemsRepository applicationsMenuItemsRepository;
    private final WorkflowMenuItemsRepository workflowMenuItemsRepository;
    private final Submenus submenus;

    public Menus(Scanner scanner, EmployeeService employeeService, ApplicationService applicationService, WorkflowService workflowService,
                 MenuItemsRepository menuItemsRepository,
                 EmployeeMenuItemsRepository employeeMenuItemsRepository, ApplicationsMenuItemsRepository applicationsMenuItemsRepository,
                 WorkflowMenuItemsRepository workflowMenuItemsRepository, Submenus submenus) {
        this.scanner = scanner;
        this.menuItemsRepository = menuItemsRepository;
        this.employeeService = employeeService;
        this.applicationService = applicationService;
        this.workflowService = workflowService;
        this.employeeMenuItemsRepository = employeeMenuItemsRepository;
        this.applicationsMenuItemsRepository = applicationsMenuItemsRepository;
        this.workflowMenuItemsRepository = workflowMenuItemsRepository;
        this.submenus = submenus;
    }



    public void startMenu() {
        System.out.println("\n" +
                "          _____                    _____                    _____          \n" +
                "         /\\    \\                  /\\    \\                  /\\    \\         \n" +
                "        /::\\____\\                /::\\    \\                /::\\____\\        \n" +
                "       /:::/    /               /::::\\    \\              /::::|   |        \n" +
                "      /:::/    /               /::::::\\    \\            /:::::|   |        \n" +
                "     /:::/    /               /:::/\\:::\\    \\          /::::::|   |        \n" +
                "    /:::/____/               /:::/__\\:::\\    \\        /:::/|::|   |        \n" +
                "   /::::\\    \\              /::::\\   \\:::\\    \\      /:::/ |::|   |        \n" +
                "  /::::::\\    \\   _____    /::::::\\   \\:::\\    \\    /:::/  |::|___|______  \n" +
                " /:::/\\:::\\    \\ /\\    \\  /:::/\\:::\\   \\:::\\____\\  /:::/   |::::::::\\    \\ \n" +
                "/:::/  \\:::\\    /::\\____\\/:::/  \\:::\\   \\:::|    |/:::/    |:::::::::\\____\\\n" +
                "\\::/    \\:::\\  /:::/    /\\::/   |::::\\  /:::|____|\\::/    / ~~~~~/:::/    /\n" +
                " \\/____/ \\:::\\/:::/    /  \\/____|:::::\\/:::/    /  \\/____/      /:::/    / \n" +
                "          \\::::::/    /         |:::::::::/    /               /:::/    /  \n" +
                "           \\::::/    /          |::|\\::::/    /               /:::/    /   \n" +
                "           /:::/    /           |::| \\::/____/               /:::/    /    \n" +
                "          /:::/    /            |::|  ~|                    /:::/    /     \n" +
                "         /:::/    /             |::|   |                   /:::/    /      \n" +
                "        /:::/    /              \\::|   |                  /:::/    /       \n" +
                "        \\::/    /                \\:|   |                  \\::/    /        \n" +
                "         \\/____/                  \\|___|                   \\/____/         \n" +
                "                                                                           \n");

        System.out.println("Input your firstname and press enter to continue\n");
        String firstname = scanner.nextLine();
        System.out.println("Welcome " + firstname + "\n");
        mainMenu();
    }

    // --------------------------Main Menu ---------------------------------//

    public void mainMenu() {
        System.out.println("What would you like to do?");

        menuItemsRepository.getMainMenuItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                startMenu();
                break;
            case "1":
                employeeMenu();
                break;
            case "2":
                applicationsMenu();
            case "3":
                workflowMenu();
            case "9":
                System.out.println("See you later?");
                System.exit(0);
            default:
                System.err.println("Invalid item number try again\n");
                mainMenu();
                break;
        }
    }

    // --------------------------Employee Menu ---------------------------------//

    public void employeeMenu() {
        System.out.println();
        employeeMenuItemsRepository.getEmployeeMenuItemsItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                mainMenu();
                break;
            case "1":
                viewEmployeesMenu();
                break;
            case "2":
//              Order employees by branch
                System.out.println("Which branch do you want to see");
                String branch = scanner.nextLine().toUpperCase().strip();
                if (branchExist(branch)){
                    employeeService.sortEmployeesBySingleBranch(branch)
                            .forEach(System.out::println);
                } else{
                    System.out.println("We do not have branch: " + branch + " in our system");
                }
                employeeMenu();
                break;
            case "3":
//                Search for employee
                System.out.println("Name of employee: ");
                String employeeName = scanner.nextLine();
                Employee employee = retryIfEmployeeIsNull(employeeService.findEmployeeByName(employeeName));

                System.out.println(employee);

                employeeMenu();
            case "4":
//                Add employee
                Employee newEmployee = new Employee();

                //Insert Id
                System.out.println("id:");
                String employeeId = scanner.nextLine();
                newEmployee.setEmployeeId(employeeId);

                // Insert Name
                System.out.println("full name:");
                String fullName = scanner.nextLine();
                newEmployee.setFullName(fullName);

                //Add score
                System.out.println("Employee Score:");
                byte score = Byte.parseByte(scanner.nextLine());
                newEmployee.setEmployeeScore(score);

                //Add branch
                newEmployee.setBranch(branchVerification());

                //Add Employee to arrayList
                employeeService.addEmployee(newEmployee);

                System.out.println("New Employee added: " + newEmployee);
                System.out.println();
                viewEmployeesMenu();
                break;
            case "5":
//                Employee(s) of the month
                System.out.println("The employee(s) of the month are: \n");
                employeeService.getEmployeesWithHighestScore(employeeService.getAllEmployees()).forEach(System.out::println);
                employeeMenu();
                break;
            default:
                System.err.println("Invalid item number try again\n");
                employeeMenu();
                break;
        }
    }

    public void viewEmployeesMenu() {
        employeeService.getAllEmployees().forEach(System.out::println);
        System.out.println();
        submenus.getViewEmployeesMenu().forEach(System.out::println);
        String response = scanner.nextLine();
        switch (response) {
            case "0":
                employeeMenu();
                break;
            case "1":
//                  order empl by score (ascending)
                employeeService.getAllEmployeesSortByLowestScore(employeeService.getAllEmployees());
                viewEmployeesMenu();
                break;
            case "2":
//                    order empl by score (desc)
                employeeService.getAllEmployeesSortByHighestScore(employeeService.getAllEmployees());
                viewEmployeesMenu();
                break;
            default:
                System.out.println("Invalid number chosen");
                break;
        }
    }

    // --------------------------Applications Menu ---------------------------------//

    public void applicationsMenu() {
        System.out.println();
        applicationsMenuItemsRepository.getApplicationsMenuItems().forEach(System.out::println);

        String item = scanner.nextLine();
        switch (item) {
            case "0":
                mainMenu();
                break;
            case "1":
                applicationService.getApplications().forEach(System.out::println);
                applicationsMenu();
                break;
            case "2":
//                method to order application by branch in params
                System.out.println("Which branch do you want to see");
                String branch = scanner.nextLine().toUpperCase().strip();
                if (branchExist(branch)){
                    System.out.println(branch+" branch: ");
                    applicationService.sortApplicationBySingleBranch(branch)
                            .forEach(System.out::println);
                } else{
                    System.out.println("We do not have branch: " + branch + " in our system");
                }
                applicationsMenu();
                break;
            case "3":
                filterApplicationsMenu();
                break;
            case "4":
                addApplicationMenu();
                applicationsMenu();
                break;
            default:
                System.err.println("Invalid item number try again\n");
                applicationsMenu();
                break;
        }
    }

    public void filterApplicationsMenu() {
        submenus.getFilterApplicationsMenuItems().forEach(System.out::println);
        String choice = scanner.nextLine();
        switch (choice) {
            case "0":
                applicationsMenu();
            case "1":
                if (applicationService.getApplications().isEmpty()) {
                    System.out.println("No Applications Left");
                    applicationsMenu();
                }
                System.out.println(applicationService.next());
                filterApplicationsMenu();
            default:
                System.err.println("Invalid item number try again\n");
                filterApplicationsMenu();
                break;
        }
    }

    public void addApplicationMenu() {
        Application application = new Application();

        //Insert Name
        System.out.println("full name:");
        String name = scanner.nextLine();
        application.setName(name);

        //Insert Education
        System.out.println("education: " + "(MBO[middelbaar] of HBO)");
        String education = scanner.nextLine().toUpperCase();
        application.setEducation(education);

        //Set Branch
        application.setVacature(branchVerification());

        //Add application to Queue
        applicationService.addApplication(application);

        System.out.println("New Application inserted: " + application);
        System.out.println();
    }

    // --------------------------Workflow Menu ---------------------------------//

    public void workflowMenu() {
        System.out.println("Workflow");
        workflowService.viewWorkFlow();
        System.out.println();
        workflowMenuItemsRepository.getWorkflowMenuItems().forEach(System.out::println);

        String choice = scanner.nextLine();
        switch (choice) {
            case "0":
                mainMenu();
                break;
            case "1":
                editWorkflow();
            default:
                System.err.println("Invalid item number try again\n");
                workflowMenu();
                break;
        }
    }

    public void editWorkflow() {
        System.out.println();
        submenus.getEditWorkflowMenuItems().forEach(System.out::println);
        String item = scanner.nextLine();
        switch (item) {
            case "0":
                workflowMenu();
                break;
            case "1":
//                addFirst method
                System.out.println("Name of employee: ");
                String employeeName = scanner.nextLine();
                Employee employeeFirst = retryIfEmployeeIsNull(employeeService.findEmployeeByName(employeeName));
                workflowService.insertFirst(employeeFirst);
                workflowMenu();
                break;
            case "2":
//                 addLast Method
                System.out.println("Name of employee: ");
                String employeeName1 = scanner.nextLine();
                Employee employeeLast = retryIfEmployeeIsNull(employeeService.findEmployeeByName(employeeName1));
                workflowService.insertLast(employeeLast);
                workflowMenu();
                break;
            case "3":
//                 add Method
                System.out.println("Name of employee: ");
                String employeeNameToAdd = scanner.nextLine();
                Employee newEmployeeNode = retryIfEmployeeIsNull(employeeService.findEmployeeByName(employeeNameToAdd));
                System.out.println(newEmployeeNode);

                System.out.println("Place before employee: ");
                String employeeNameAfterNode = scanner.nextLine();
                Employee nodeAfter = retryIfNodeIsNull(workflowService.findNodeByName(employeeNameAfterNode));

                int position = workflowService.getWorkflow().indexOf(nodeAfter);
                workflowService.getWorkflow().add(position, newEmployeeNode);

                workflowMenu();
                break;
            case "4":
//                 replace Node method
                System.out.println("Name of employee to add: ");
                String newNodeName = scanner.nextLine();
                Employee newEmplNode = retryIfEmployeeIsNull(employeeService.findEmployeeByName(newNodeName));

                System.out.println("Name of employee to be replaced: ");
                String replaceNodeName = scanner.nextLine();
                Employee replaceEmplNode = retryIfNodeIsNull(workflowService.findNodeByName(replaceNodeName));

                workflowService.replace(replaceEmplNode, newEmplNode);

                workflowMenu();
                break;
            case "5":
//                 remove Node method
                System.out.println("Name of employee to remove: ");
                String nameToRemove = scanner.nextLine();
                Employee employeeToRemove = retryIfNodeIsNull(workflowService.findNodeByName(nameToRemove));
                workflowService.remove(employeeToRemove);
                workflowMenu();
            default:
                System.err.println("Invalid item number try again\n");
                editWorkflow();
                break;
        }
    }

//    -----------------------------------Helper Methods ---------------------------------------------//

    public Branch branchVerification() {
        System.out.println(Arrays.toString(Branch.values()));
        System.out.println("Type in the branch");
        String vacatureBranch = scanner.nextLine().toUpperCase().strip();
        Branch branch;
        if (branchExist(vacatureBranch)) {
            branch = Branch.valueOf(vacatureBranch);
        } else {
            System.out.println("We do not have branch: " + vacatureBranch + " in our system");
            System.out.println("Please try again");
            branch = branchVerification();
        }

        return branch;
    }

    public boolean branchExist(String branchString) {
        boolean exist = false;
        ArrayList<String> branchList = new ArrayList<>();
        Arrays.stream(Branch.values()).forEach(brnch -> branchList.add(brnch.toString()));

        for (String branch : branchList) {
            if (branch.equalsIgnoreCase(branchString)) {
                exist = true;
                break;
            }
        }
        return exist;

    }
    public Employee retryIfEmployeeIsNull(Employee employee) {
        while (employee == null) {
            System.out.println("Employee  not found, Try Again?");
            System.out.println("Employee name: ");
            String employeeName = scanner.nextLine();
            employee = employeeService.findEmployeeByName(employeeName);
        }
        return employee;
    }

    public Employee retryIfNodeIsNull(Employee employee) {
        while (workflowService.getWorkflow().contains(employee)) {
            System.out.println("Node not found, Try Again?");
            System.out.println("Employee name: ");
            String employeeName = scanner.nextLine();
            employee = workflowService.findNodeByName(employeeName);
        }
        return employee;
    }

}
