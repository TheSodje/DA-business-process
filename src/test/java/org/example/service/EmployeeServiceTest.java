package org.example.service;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.util.enums.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest {

    List<Employee> testEmployeeList = new ArrayList<>();

    private final EmployeeService employeeService;

    public EmployeeServiceTest() {
        testEmployeeList.add(new Employee("1", Branch.IT, (byte) 1, "John Wick"));
        testEmployeeList.add(new Employee("2", Branch.FINANCE, (byte) 3, "Peter Parker"));
        testEmployeeList.add(new Employee("3", Branch.SALES, (byte) 10, "Yuri Parker"));
        testEmployeeList.add(new Employee("4", Branch.IT, (byte) 7, "Sergei R"));

        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeService = new EmployeeServiceImpl(employeeRepository);

    }


    @Test
    @DisplayName("Top in list has higher or equal score than the next entree")
    void employeeListHighestScoreAtTop() {
        List<Employee> sortedEmployeesByHighest = employeeService.getAllEmployeesSortByHighestScore(testEmployeeList);
        boolean result = sortedEmployeesByHighest.get(0).getEmployeeScore() >= sortedEmployeesByHighest.get(1).getEmployeeScore();

        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Sort By Branch")
    void sortEmployeeBySingleBranch() {
        List<Employee> employeesList = employeeService.getAllEmployees();
        String branch = "IT";
        int employeeForIT = employeeService.sortEmployeesBySingleBranch(branch).size();
        int employeeCounter = 0;
        for (Employee employee : employeeService.getAllEmployees()) {
            if (employee.getBranch() == Branch.valueOf(branch)) {
                employeeCounter++;
            }
        }
        Assertions.assertEquals(employeeForIT, employeeCounter);
    }

    @Test
    void getAllEmployeesSortByLowestScore() {
        List<Employee> sortedEmployeesByLowest = employeeService.getAllEmployeesSortByLowestScore(testEmployeeList);
        boolean result = sortedEmployeesByLowest.get(0).getEmployeeScore() <= sortedEmployeesByLowest.get(1).getEmployeeScore();

        Assertions.assertTrue(result);


    }

    @Test
    @DisplayName("Find Employee By name")
    void shouldFindEmployeeByName() {

        //Given
        int index = 4;
        String employeeName = employeeService.getAllEmployees().get(index).getFullName();

        //When
        Employee employeefound = employeeService.findEmployeeByName(employeeName);
        int indexFound = employeeService.getAllEmployees().indexOf(employeefound);

        //Then
        Assertions.assertEquals(index, indexFound);
    }
}