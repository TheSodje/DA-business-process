package org.example.service;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployeesSortByLowestScore(List<Employee> employees);

    List<Employee> getAllEmployeesSortByHighestScore(List<Employee> employees);

    List<Employee> getEmployeesWithHighestScore(List<Employee> employees);

    Employee findEmployeeByName(String name);

    void sortEmployeesBySingleBranch(String branch);
}
