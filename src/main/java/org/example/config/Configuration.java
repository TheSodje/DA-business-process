package org.example.config;

import org.example.repositories.ApplicationRepository;
import org.example.repositories.EmployeeRepository;
import org.example.repositories.menus.MenuItemsRepository;
import org.example.repositories.WorkFlowRepository;
import org.example.service.ApplicationService;
import org.example.service.EmployeeService;
import org.example.service.WorkflowService;
import org.example.service.impl.ApplicationServiceImpl;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.service.impl.WorkFlowServiceImpl;
import org.example.view.Menus;

import java.util.Scanner;

public class Configuration {

    private final Scanner scanner = new Scanner(System.in);
    private final MenuItemsRepository menuItemsRepository = new MenuItemsRepository();
    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final ApplicationRepository applicationRepository = new ApplicationRepository();
    private final WorkFlowRepository workFlowRepository = new WorkFlowRepository();

    public Menus menu() {
        return new Menus(scanner, menuItemsRepository, employeeService(), applicationService(), workflowService());
    }

    public EmployeeService employeeService() {
        return new EmployeeServiceImpl(employeeRepository);
    }
    public ApplicationService applicationService() {
        return new ApplicationServiceImpl(applicationRepository);
    }
    public WorkflowService workflowService() { return new WorkFlowServiceImpl(workFlowRepository);
    }
}
