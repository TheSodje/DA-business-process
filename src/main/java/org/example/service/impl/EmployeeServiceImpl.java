package org.example.service.impl;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.EmployeeService;
import org.example.util.enums.Branch;

import java.util.ArrayList;
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
    public ArrayList<Employee> sortEmployeesBySingleBranch(String branch) {
        ArrayList<Employee> employeesList = new ArrayList<>();
        for (Employee employee : employeeRepository.getEmployees()) {
            if (employee.getBranch() == Branch.valueOf(branch)) {
                employeesList.add(employee);
            }
        }
        return employeesList;
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

    //---------------------- Sorting Algorithm --------------------------//
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
        employeeRepository.setEmployees(sortedByHighestScoreEmployees);
        return sortedByHighestScoreEmployees;
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

    //---------------------- Search Algorithm --------------------------//
    @Override
    public ArrayList<Employee> searchEmployeesByScore(byte key) {
        List<Employee> employees = employeeRepository.getEmployees();
        List<Employee> sortedByLowestScoreEmployees = getAllEmployeesSortByLowestScore(employees);
        return findByKey(0, sortedByLowestScoreEmployees.size() - 1, sortedByLowestScoreEmployees, key);
    }

    /*
     * Searching algorithm used is Binary search
     * why? Binary search runs in O(log n), so it's more efficient than linear search as the input grows.
     * how? The input (data) has to be sorted first, which is done with the sorting algorithm above (merge sort (O(log n))),
     * then it takes the middle point of the sorted input, where if the search value is either greater or less than the middle point,
     * it cuts the input size in half by discarding everything to the left(if greater) or right(if less) of it.
     * */

    public ArrayList<Employee> findByKey (int startIndex, int endIndex, List<Employee> employees, byte key) {
        ArrayList<Employee> employeesFound = new ArrayList<>();

        while (startIndex <= endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            byte middleEmployeeScore = employees.get(middleIndex).getEmployeeScore();
            if (middleEmployeeScore == key) {
                employeesFound.add(employees.get(middleIndex));
                while(middleIndex < endIndex && employees.get(middleIndex+1).getEmployeeScore() == key){
                        employeesFound.add(employees.get(middleIndex+1));
                        middleIndex++;
                }
                break;
            } else if (startIndex == endIndex){
                System.out.println("No Employee Found with score: "+ key);
                break;
            }
            else if (middleEmployeeScore < key) {
                startIndex = middleIndex + 1;

            } else {
                endIndex = middleIndex - 1;
            }
        }

        return employeesFound;
    }
}
