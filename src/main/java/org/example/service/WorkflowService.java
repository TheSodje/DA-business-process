package org.example.service;

import org.example.entity.Employee;

import java.util.LinkedList;

public interface WorkflowService {
    LinkedList<Employee> getWorkflow();

    void insertLast(Employee employee);
    void insertFirst(Employee employee);
    void replace(Employee employee1, Employee employee2);
    void viewWorkFlow();

}
