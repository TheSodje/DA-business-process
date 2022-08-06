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
    public List<Employee> getEmployeesWithHighestScore(List<Employee> employees) {
        List<Employee> sortedByHighestScore = getAllEmployeesSortByHighestScore(employees);
        List<Employee> employeesWithHighestScore = new ArrayList<>();

        for (int i = 0; i <= sortedByHighestScore.size() - 1; i++) {
            if (sortedByHighestScore.get(i).getEmployeeScore() >=
                    sortedByHighestScore.get(0).getEmployeeScore()) {
                employeesWithHighestScore.add(sortedByHighestScore.get(i));
            }
        }
        return employeesWithHighestScore;
    }

    @Override
    public List<Employee> getAllEmployeesSortByLowestScore(List<Employee> employees) {
        List<Employee> sortedEmployees = divideArrayElements(0, employees.size() - 1, employees);
        employeeRepository.setEmployees(sortedEmployees);
        return employees;
    }

    @Override
    public List<Employee> getAllEmployeesSortByHighestScore(List<Employee> employees) {
        List<Employee> sortedByLowestScoreEmployees = getAllEmployeesSortByLowestScore(employees);
        List<Employee> sortedByHighestScoreEmployees = new ArrayList<>();
        for (int i = sortedByLowestScoreEmployees.size() - 1; i >= 0; i--) {
            sortedByHighestScoreEmployees.add(sortedByLowestScoreEmployees.get(i));
        }
        return sortedByHighestScoreEmployees;
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

    /*
     * Sorting algorithm used is merged sort
     * why? merge sort is viable for all sizes of datasets compared to other basic sorting algorithms
     * how? merge sort uses divide and conquer principle by breaking up the data, sorting and merging
     *      together
     * */

    private List<Employee> divideArrayElements(int startIndex, int endIndex, List<Employee> employees) {
        if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int middleElement = (endIndex + startIndex) / 2;

            divideArrayElements(startIndex, middleElement, employees);
            divideArrayElements(middleElement + 1, endIndex, employees);

            return mergeArrayElements(startIndex, middleElement, endIndex, employees);
        } else return employees;
    }

    private List<Employee> mergeArrayElements(int indexStart, int indexMiddle, int indexEnd, List<Employee> employees) {
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
}
