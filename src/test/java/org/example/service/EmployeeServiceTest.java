package org.example.service;

import org.example.entity.Employee;
import org.example.repositories.EmployeeRepository;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.util.enums.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EmployeeServiceTest {

    List<Employee> testEmployeeList = new ArrayList<>();

    private final EmployeeService employeeService;

    public EmployeeServiceTest() {
        testEmployeeList.add(new Employee("1", Branch.IT, (byte) 1, "John Wick"));
        testEmployeeList.add(new Employee("2", Branch.FINANCE, (byte) 3, "Peter Parker"));
        testEmployeeList.add(new Employee("3", Branch.SALES, (byte) 10, "Yuri Parker"));
        testEmployeeList.add(new Employee("4", Branch.IT, (byte) 7, "Sergei R"));

        EmployeeRepository employeeRepository = new EmployeeRepository();
        employeeService = new EmployeeServiceImpl(employeeRepository);

    }

    @Test
    @DisplayName("Sort By Branch")
    void sortEmployeeBySingleBranch() {
        //Given
        String branch = "IT";
        int employeesForIT = employeeService.sortEmployeesBySingleBranch(branch).size();
        int employeeCounter = 0;

        //When
        for (Employee employee : employeeService.getAllEmployees()) {
            if (employee.getBranch() == Branch.valueOf(branch)) {
                employeeCounter++;
            }
        }

        //Then
        Assertions.assertEquals(employeesForIT, employeeCounter);
    }

    @Test
    @DisplayName("Sort Employees By Score (asc)")
    void getAllEmployeesSortByLowestScore() {
        //Given
        List<Employee> sortedEmployeesByLowest = employeeService.getAllEmployeesSortByLowestScore(testEmployeeList);
        //When
        boolean result = sortedEmployeesByLowest.get(0).getEmployeeScore() <= sortedEmployeesByLowest.get(1).getEmployeeScore();
        //Then
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Sort Employees By Score (asc)")
    void getAllEmployeesSortByLowestScores() {
        //Given
        List<Employee> sortedEmployeesByLowest = employeeService.getAllEmployeesSortByLowestScore(testEmployeeList);
        boolean asc = true;
        int index = 0;

        //When
        while(index < (sortedEmployeesByLowest.size() -1)){
            asc = sortedEmployeesByLowest.get(index).getEmployeeScore() <= sortedEmployeesByLowest.get(index + 1).getEmployeeScore();
            if (!asc){
                break;
            }
            index++;
        }

        //Then
        Assertions.assertTrue(asc);
    }


    @Test
    @DisplayName("Sort Employees By Score (desc)")
    void employeeListHighestScoreAtTop() {
        //Given
        List<Employee> sortedEmployeesByHighest = employeeService.getAllEmployeesSortByHighestScore(testEmployeeList);
        boolean desc = true;
        int index = 0;

        //When
        while(index < (sortedEmployeesByHighest.size() -1)){
            desc = sortedEmployeesByHighest.get(index).getEmployeeScore() >= sortedEmployeesByHighest.get(index + 1).getEmployeeScore();
            if (!desc){
                break;
            }
            index++;
        }

        //Then
        Assertions.assertTrue(desc);
    }

    @Test
    @DisplayName("Find Employee By name")
    void shouldFindEmployeeByName() {

        //Given
        int index = 4;
        String employeeName = employeeService.getAllEmployees().get(index).getFullName();

        //When
        Employee employeeFound = employeeService.findEmployeeByName(employeeName);
        int indexFound = employeeService.getAllEmployees().indexOf(employeeFound);

        //Then
        Assertions.assertEquals(index, indexFound);
    }

    @Test
    @DisplayName("Return first employee with given score")
    void searchForEmployeeWithCertainScore(){
        //Given
        byte score = (byte)5;

        //When
        Employee employeeWithScore = employeeService.searchEmployeeByScore(score);
        byte scoreFound = employeeWithScore.getEmployeeScore();

        //Then
        Assertions.assertEquals(score, scoreFound);

    }
}