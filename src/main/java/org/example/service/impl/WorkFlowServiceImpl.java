package org.example.service.impl;

import org.example.entity.Employee;
import org.example.repositories.WorkFlowRepository;
import org.example.service.WorkflowService;

import java.util.LinkedList;

public class WorkFlowServiceImpl implements WorkflowService {

    private final WorkFlowRepository workFlowRepo;

    public WorkFlowServiceImpl(WorkFlowRepository workFlowRepository) {
        this.workFlowRepo = workFlowRepository;
    }

    @Override
    public LinkedList<Employee> getWorkflow() {
        return workFlowRepo.getWorkflow();
    }

    @Override
    public void insertLast(Employee employee) {
        workFlowRepo.insertLast(employee);
    }

    @Override
    public void insertFirst(Employee employee) {
        workFlowRepo.insertFirst(employee);
    }

    @Override
    public void replace(Employee employee1, Employee employee2) {
        workFlowRepo.replace(employee1, employee2);
    }

    @Override
    public void viewWorkFlow() {
        workFlowRepo.viewWorkflow();
    }
}
