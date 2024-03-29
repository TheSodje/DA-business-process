package org.example.repositories;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.*;

public class EmployeeRepository {

    /*
     * Datastructure: ArrayList, for easy index based acces (O)1 and adding. Most important feature is that it can grow itself when needed;
     * In this case the Arraylist wil be used to constantly acces data, delete date, search for data en sort data.
     * */
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

    public Employee findEmployeeByName(String name) {
        Employee employee = null;
        for (Employee empl : employees) {
            if (empl.getFullName().equalsIgnoreCase(name)) {
                employee = empl;
                break;
            }
        }
        return employee;
    }

    {
        employees.add(new Employee("1", Branch.IT, (byte) 5, "John Wick"));
        employees.add(new Employee("2", Branch.FINANCE, (byte) 9, "Peter Doe"));
        employees.add(new Employee("3", Branch.SALES, (byte) 2, "Yuri Parker"));
        employees.add(new Employee("4", Branch.IT, (byte) 8, "Sergei R"));
        employees.add(new Employee("5", Branch.FINANCE, (byte) 6, "Tony Parker"));
        employees.add(new Employee("6", Branch.SALES, (byte) 7, "Yuri Rostov"));
        employees.add(new Employee("7", Branch.HR, (byte) 4, "Herb Dean"));
        employees.add(new Employee("8", Branch.IT, (byte) 3, "Gab Marcotti"));
        employees.add(new Employee("9", Branch.PR, (byte) 9, "Frank Lebouf"));
    }
}
