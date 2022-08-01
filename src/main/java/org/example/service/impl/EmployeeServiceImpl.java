package org.example.service.impl;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getEmployees();
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }
}
