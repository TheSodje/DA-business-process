package org.example.service.impl;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.EmployeeService;
import org.example.util.enums.Branch;

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

    @Override
    public Employee findEmployeeByName(String name) {
        return employeeRepository.findEmployeeByName(name);
    }

    @Override
    public List<Employee> getEmployeesWithHighestScore() {
        return employeeRepository.getEmployeesWithHighestScore();
    }

    @Override
    public List<Employee> getAllEmployeesSortByLowestScore() {
        return employeeRepository.employeesortByLowestScore();
    }

    @Override
    public List<Employee> getAllEmployeesSortByHighestScore() {
        return employeeRepository.employeesrtByHighestScore();
    }

    @Override
    public void sortEmployeesBySingleBranch(String branch) {
        employeeRepository.sortEmployeesBySingleBranch(branch);
    }


}
