package org.example.service;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.repositories.WorkFlowRepository;
import org.example.service.impl.WorkFlowServiceImpl;
import org.example.util.enums.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class WorkflowServiceTest {

    private WorkflowService workflowService;
    WorkFlowRepository workFlowRepository;
    Employee newEmployee;
    String nameNewEmployee;

    public WorkflowServiceTest(){
        workFlowRepository = new WorkFlowRepository();
        workflowService = new WorkFlowServiceImpl(workFlowRepository);

        EmployeeRepository employeeRepository = new EmployeeRepository();
        nameNewEmployee = "Deebo Samuel";
        newEmployee = new Employee("14", Branch.FINANCE, (byte)7, nameNewEmployee);
        employeeRepository.addEmployee(newEmployee);

    }

    @Test
    @DisplayName("Insert new Node at the end")
    void shouldInsertAtEndOfList() {
        //Given
        workflowService.insertLast(newEmployee);
        //When
        Employee lastEmployee = workflowService.getWorkflow().getLast();
        //Then
        Assertions.assertEquals(newEmployee, lastEmployee);

    }

    @Test
    @DisplayName("Insert new Node at the beginning")
    void shouldInsertAtBeginningOfList() {
        //Given
        workflowService.insertFirst(newEmployee);
        //When
        Employee firstEmployee = workflowService.getWorkflow().getFirst();
        //Then
        Assertions.assertEquals(newEmployee, firstEmployee);
    }

    @Test
    @DisplayName("Replace Node with new Employee")
    void shouldReplaceNodeAtGivenIndex() {
        //Given
        int index = 4;
        Employee employeeToReplace = workflowService.getWorkflow().get(index);
        int workflowSize = workflowService.getWorkflow().size();

        //When
        workflowService.replace(employeeToReplace, newEmployee);
        int workflowSizeAfter = workflowService.getWorkflow().size();

        //Then
        Assertions.assertTrue(
                (workflowService.getWorkflow().get(index).equals(newEmployee))
                &&
                (workflowSize == workflowSizeAfter));
    }

    @Test
    @DisplayName("Find Node by given Employee name")
    void shouldFindNodeByName() {
        //Given
        workflowService.getWorkflow().add(newEmployee);

        //When
        Employee nodeFound = workflowService.findNodeByName(nameNewEmployee);

        //Then
        Assertions.assertEquals(newEmployee, nodeFound);

    }

    @Test
    @DisplayName("Should remove newly Added node")
    void shouldNotContainNewlyAddedNodeAfterRemove() {
        //Given
        workflowService.getWorkflow().add(newEmployee);

        //When
        workflowService.remove(newEmployee);

        //Then
        Assertions.assertFalse(workflowService.getWorkflow().contains(newEmployee));
    }
}