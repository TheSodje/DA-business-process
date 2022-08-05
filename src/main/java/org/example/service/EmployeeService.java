package org.example.service;

import org.example.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployeesSortByLowestScore();
}
