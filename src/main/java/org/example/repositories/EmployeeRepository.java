package org.example.repositories;

import org.example.entity.Employee;
import org.example.util.enums.Branch;

import java.util.*;

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

    public Employee findEmployeeByName(String name) {
        Employee employee= null;
        for (Employee empl: employees){
            if (empl.getFullName().equalsIgnoreCase(name)){
                employee = empl;
                break;
            }
        }
        return employee;
    }

        public List<Employee> getEmployeesWithHighestScore() {
        List<Employee> sortedByHighestScore = employeesrtByHighestScore();
        List<Employee> employeesWithHighestScore = new ArrayList<>();

        for (int i = 0; i <= sortedByHighestScore.size() - 1; i++) {
            if (sortedByHighestScore.get(i).getEmployeeScore() >=
                    sortedByHighestScore.get(0).getEmployeeScore()) {
                employeesWithHighestScore.add(sortedByHighestScore.get(i));
            }
        }
        return employeesWithHighestScore;
    }


    public List<Employee> employeesortByLowestScore() {
        employees = divideArrayElements(0, employees.size() - 1);
        return employees;
    }


    public List<Employee> employeesrtByHighestScore() {
        List<Employee> sortedByLowestScoreEmployees = employeesortByLowestScore();
        List<Employee> sortedByHighestScoreEmployees = new ArrayList<>();
        for (int i = sortedByLowestScoreEmployees.size() - 1; i >= 0; i--) {
            sortedByHighestScoreEmployees.add(sortedByLowestScoreEmployees.get(i));
        }
        return sortedByHighestScoreEmployees;
    }

    /*
     * Sorting algorithm used is merged sort
     * why? merge sort is viable for all sizes of datasets compared to other basic sorting algorithms
     * how? merge sort uses divide and conquer principle by breaking up the data, sorting and merging
     *      together
     * */

    private List<Employee> divideArrayElements(int startIndex, int endIndex) {
        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int middleElement = (endIndex + startIndex) / 2;

            divideArrayElements(startIndex, middleElement);
            divideArrayElements(middleElement + 1, endIndex);

            return mergeArrayElements(startIndex, middleElement, endIndex);
        } else return employees;
    }

    private List<Employee> mergeArrayElements(int indexStart, int indexMiddle, int indexEnd) {
        List<Employee> tempArray = new ArrayList<>();

        int getLeftIndex = indexStart;
        int getRightIndex = indexMiddle + 1;

        while (getLeftIndex <= indexMiddle && getRightIndex <= indexEnd) {

            if (employees.get(getLeftIndex).getEmployeeScore() <=
                    employees.get(getRightIndex).getEmployeeScore()) {

                tempArray.add(employees.get(getLeftIndex));
                getLeftIndex++;
            } else {
                tempArray.add(employees.get(getRightIndex));
                getRightIndex++;
            }
        }

        while (getLeftIndex <= indexMiddle) {
            tempArray.add(employees.get(getLeftIndex));
            getLeftIndex++;
        }

        while (getRightIndex <= indexEnd) {
            tempArray.add(employees.get(getRightIndex));
            getRightIndex++;
        }


        for (int i = 0; i < tempArray.size(); indexStart++) {
            employees.set(indexStart, tempArray.get(i++));
        }

        return tempArray;

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
