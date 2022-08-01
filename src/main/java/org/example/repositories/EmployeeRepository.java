package org.example.repositories;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    {
        employees.add(new Employee("1", Branch.IT, (byte) 5, "john Wick"));
        employees.add(new Employee("2", Branch.FINANCE, (byte) 6, "peter parker"));
    }
}