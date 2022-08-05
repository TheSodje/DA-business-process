package org.example.view;

import org.example.entity.Application;
import org.example.entity.Employee;
import org.example.repositories.menus.*;
import org.example.repositories.menus.submenus.ApplicationsMenuItemsRepository;
import org.example.repositories.menus.submenus.EmployeeMenuItemsRepository;
import org.example.repositories.menus.submenus.Submenus;
import org.example.repositories.menus.submenus.WorkflowMenuItemsRepository;
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
                System.out.println("Which branch do you want to see");
                String branch = scanner.nextLine();
//                method to order by branch in params
                employeeMenu();
                break;
            case "3":
//                Search for employee
                System.out.println("Name of employee: ");
                String employeeName = scanner.nextLine();
                Employee employee = employeeService.findEmployeeByName(employeeName);
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

                //Add branch
                newEmployee.setBranch(branchVerification());
                System.out.println("Score:");
                byte score = scanner.nextByte();

                newEmployee.setEmployeeScore(score);

                //Add Employee to arrayList
                employeeService.addEmployee(newEmployee);

                System.out.println("New Employee added: "+ newEmployee);
                System.out.println();
                viewEmployeesMenu();
                break;
            case "5":
//                Employee(s) of the month
//                Method to return array with employees with highest score.
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
                employeeService.getAllEmployeesSortByLowestScore().forEach(System.out::println);
                employeeMenu();
                break;
            case "2":
//                    order empl by score (desc)
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
                String branch = scanner.nextLine();
//                method to order application by branch in params
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
    public void addApplicationMenu(){
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

        //Add application to Qeue
        applicationService.addApplication(application);

        System.out.println("New Application inserted: "+ application);
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
                Employee employeeFirst = employeeService.findEmployeeByName(employeeName);
                workflowService.insertFirst(employeeFirst);
                workflowMenu();
                break;
            case "2":
//                 addLast Method
                System.out.println("Name of employee: ");
                String employeeName1 = scanner.nextLine();
                Employee employeeLast = employeeService.findEmployeeByName(employeeName1);
                workflowService.insertLast(employeeLast);
                workflowMenu();
                break;
            case "3":
//                 add Method
                System.out.println("Name of employee: ");
                String employeeNameToAdd = scanner.nextLine();
                Employee newEmployeeNode = employeeService.findEmployeeByName(employeeNameToAdd);
                System.out.println(newEmployeeNode);

                System.out.println("Place before employee: ");
                String employeeNameAfterNode = scanner.nextLine();
                Employee nodeAfter = workflowService.findNodeByName(employeeNameAfterNode);

                int position = workflowService.getWorkflow().indexOf(nodeAfter) ;
                workflowService.getWorkflow().add(position, newEmployeeNode);

                workflowMenu();
                break;
            case "4":
//                 replace empl method
                System.out.println("Name of employee to add: ");
                String newNodeName = scanner.nextLine();
                Employee newEmplNode = employeeService.findEmployeeByName(newNodeName);

                System.out.println("Name of employee to be replaced: ");
                String replaceNodeName = scanner.nextLine();
                Employee replaceEmplNode = workflowService.findNodeByName(replaceNodeName);

                workflowService.replace(replaceEmplNode, newEmplNode);

                workflowMenu();
                break;
            case "5":
//                 remove method
                System.out.println("Name of employee to remove: ");
                String nameToRemove = scanner.nextLine();
                workflowService.remove(nameToRemove);
                workflowMenu();
            default:
                System.err.println("Invalid item number try again\n");
                editWorkflow();
                break;
        }
    }

//    -----------------------------------Helper Methods ---------------------------------------------//

    public Branch branchVerification(){
        System.out.println(Arrays.toString(Branch.values()));
        System.out.println("Type in the branch");
        String vacatureBranch = scanner.nextLine().toUpperCase().strip();
        Branch openBranch = findBranch(vacatureBranch);
        if (openBranch == null){
            System.out.println("We do not have branch: " +vacatureBranch+ " in our system");
            System.out.println("Please try again");
            branchVerification();
        }

        return openBranch;
    }

    public Branch findBranch(String branchNewEmpl){
        Branch branchNew = null;
        if (employeeService.branchExist(branchNewEmpl)) {
            branchNew = Branch.valueOf(branchNewEmpl);
            return branchNew;
        }

        return branchNew;
    }

}
