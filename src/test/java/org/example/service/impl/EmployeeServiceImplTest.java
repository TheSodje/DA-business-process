package org.example.service.impl;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.EmployeeService;
import org.example.util.enums.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EmployeeServiceImplTest {
    List<Employee> testEmployeeList = new ArrayList<>();

    private EmployeeService employeeService;

    public void EmployeeServiceTest() {
        testEmployeeList.add(new Employee("1", Branch.IT, (byte) 1, "John Wick"));
        testEmployeeList.add(new Employee("2", Branch.FINANCE, (byte) 3, "Peter Parker"));
        testEmployeeList.add(new Employee("3", Branch.SALES, (byte) 10, "Yuri Parker"));
        testEmployeeList.add(new Employee("4", Branch.IT, (byte) 7, "Sergei R"));

        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeService = new EmployeeServiceImpl(employeeRepository);
    }
    
}