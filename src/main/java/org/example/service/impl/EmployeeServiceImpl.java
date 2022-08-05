package org.example.service.impl;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.EmployeeService;

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
    public boolean branchExist(String branch) {
        return employeeRepository.getBranch(branch);
    }

    @Override
    public List<Employee> getAllEmployeesSortByLowestScore() {
        List<Employee> sortedEmployees = divideArrayElements(0, getAllEmployees().size() - 1);
        employeeRepository.setEmployees(sortedEmployees);
        return getAllEmployees();
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
        } else return getAllEmployees();
    }

    private List<Employee> mergeArrayElements(int indexStart, int indexMiddle, int indexEnd) {
        List<Employee> tempArray = new ArrayList<>();

        int getLeftIndex = indexStart;
        int getRightIndex = indexMiddle + 1;

        while (getLeftIndex <= indexMiddle && getRightIndex <= indexEnd) {

            if (getAllEmployees().get(getLeftIndex).getEmployeeScore() <=
                    getAllEmployees().get(getRightIndex).getEmployeeScore()) {

                tempArray.add(getAllEmployees().get(getLeftIndex));
                getLeftIndex++;

            } else {

                tempArray.add(getAllEmployees().get(getRightIndex));
                getRightIndex++;

            }
        }

        while (getLeftIndex <= indexMiddle) {
            tempArray.add(getAllEmployees().get(getLeftIndex));
            getLeftIndex++;
        }

        while (getRightIndex <= indexEnd) {
            tempArray.add(getAllEmployees().get(getRightIndex));
            getRightIndex++;
        }


        for (int i = 0; i < tempArray.size(); indexStart++) {
            getAllEmployees().set(indexStart, tempArray.get(i++));
        }

        return tempArray;

    }
}
