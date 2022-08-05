package org.example.repositories;

import org.example.entity.Employee;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class WorkFlowRepository {

    private final EmployeeRepository employeeRepo = new EmployeeRepository();
    private final List<Employee> employees = employeeRepo.getEmployees();

    private final LinkedList<Employee> workflow = new LinkedList<>();

    public LinkedList<Employee> getWorkflow(){
        return workflow;
    }

    public void insertLast(Employee employee){
        workflow.addLast(employee);
    }
    public void insertFirst(Employee employee){
        workflow.addFirst(employee);
    }

    public void replace(Employee employee1, Employee employee2){
        Employee employeeToBeReplaced = new Employee();
        for (Employee node: workflow){
            if (node.getFullName().equalsIgnoreCase(employee1.getFullName())){
                employeeToBeReplaced = node;
                break;
            }
        }
        int index = workflow.indexOf(employeeToBeReplaced);
        workflow.remove(employeeToBeReplaced);
        workflow.add(index, employee2);
    }

    public Employee findNode(String employee) {
        Employee employeeNode = new Employee();
        for (Employee empl: employees){
            if (empl.getFullName().equalsIgnoreCase(employee)){
                employeeNode = empl;
                break;
            }
        }
        return employeeNode;
    }

    public void viewWorkflow() {
        ListIterator<Employee> employeeListIterator = workflow.listIterator();
        int step = 1;
        while(employeeListIterator.hasNext()){
            System.out.println(step +". "+ employeeListIterator.next());
            step++;
        }
    }
    public void remove(String name) {
        Employee nodeToRemove = employeeRepo.findEmployeeByName(name);
        workflow.remove(nodeToRemove);
        System.out.println(nodeToRemove);

    }

    {
        workflow.add(employees.get(3));
        workflow.add(employees.get(1));
        workflow.add(employees.get(4));
        workflow.add(employees.get(2));
    }

}
