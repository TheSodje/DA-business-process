package org.example.repositories;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeRepository {

    private final List<Employee> employees = new ArrayList<>();

    public List<Employee> getEmployees() {
        return employees;
    }

    public boolean addEmployee(Employee employee) {
        return employees.add(employee);
    }

    public Employee findEmployeeByName(String name) {
        Employee employee= new Employee();
        for (Employee empl: employees){
            if (empl.getFullName().equalsIgnoreCase(name)){
                employee = empl;
                break;
            }
        }
        return employee;
    }

    public boolean getBranch(String branchString){
        boolean exist = false;
        ArrayList<String> branchList = new ArrayList<>();
        Arrays.stream(Branch.values()).forEach(brnch -> branchList.add(brnch.toString()));

        for (String branch: branchList) {
            if(branch.equalsIgnoreCase(branchString)){
                exist = true;
                break;
            }
        }

        return exist;

    }


    {
        employees.add(new Employee("1", Branch.IT, (byte) 5, "John Wick"));
        employees.add(new Employee("2", Branch.FINANCE, (byte) 6, "Peter Parker"));
        employees.add(new Employee("3", Branch.SALES, (byte) 7, "Yuri Rostov"));
        employees.add(new Employee("4", Branch.HR, (byte) 8, "Herb Dean"));
        employees.add(new Employee("5", Branch.IT, (byte) 8, "Gab Marcotti"));
        employees.add(new Employee("6", Branch.PR, (byte) 8, "Stephen A Smith"));
    }
}
