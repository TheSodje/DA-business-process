package org.example.repositories;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    {
        employees.add(new Employee("1", Branch.IT, (byte) 5, "John Wick"));
        employees.add(new Employee("2", Branch.FINANCE, (byte) 9, "Peter Parker"));
        employees.add(new Employee("3", Branch.SALES, (byte) 2, "Yuri Parker"));
        employees.add(new Employee("4", Branch.IT, (byte) 8, "Sergei R"));
    }
}
