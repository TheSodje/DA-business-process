package org.example.service;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    boolean addEmployee(Employee employee);

    List<Employee> getAllEmployeesSortByLowestScore();

    Employee findEmployeeByName(String name);

    boolean branchExist(String branch);

}
