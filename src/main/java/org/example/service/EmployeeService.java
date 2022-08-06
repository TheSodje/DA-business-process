package org.example.service;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    List<Employee> getEmployeesWithHighestScore();

    List<Employee> getAllEmployeesSortByLowestScore();

    Employee findEmployeeByName(String name);

    List<Employee> getAllEmployeesSortByHighestScore();

    void sortEmployeesBySingleBranch(String branch);
}
