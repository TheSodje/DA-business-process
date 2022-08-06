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

    private EmployeeService employeeService;

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
    public void employeeListHighestScoreAtTop() {
        List<Employee> sortedEmployeesByHighest = employeeService.getAllEmployeesSortByHighestScore(testEmployeeList);
        boolean result = sortedEmployeesByHighest.get(0).getEmployeeScore() >= sortedEmployeesByHighest.get(1).getEmployeeScore();

        Assertions.assertTrue(result);
    }
}
