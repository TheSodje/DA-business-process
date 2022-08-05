package org.example.service;

import org.example.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployeesSortByLowestScore();

    Employee findEmployeeByName(String name);

    boolean branchExist(String branch);

}
