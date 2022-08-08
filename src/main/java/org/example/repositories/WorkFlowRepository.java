package org.example.repositories;

import org.example.entity.Employee;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class WorkFlowRepository {

    private final EmployeeRepository employeeRepo = new EmployeeRepository();

    /*
     * Datastructure: LinkedList, for easy adding Nodes(employees) in workflow, without having to delete the node first.
     * It is very dynamic and utilizes memory efficiently.
     * */
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

    public void replace(Employee emplToBeReplaced, Employee insertedEmployee){
        Employee employeeToBeReplaced = new Employee();
        for (Employee node: workflow){
            if (node.getFullName().equalsIgnoreCase(emplToBeReplaced.getFullName())){
                employeeToBeReplaced = node;
                break;
            }
        }
        int index = workflow.indexOf(employeeToBeReplaced);
        workflow.remove(employeeToBeReplaced);
        workflow.add(index, insertedEmployee);
    }

    public Employee findNode(String employee) {
        Employee employeeNode = new Employee();
        for (Employee empl: workflow){
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
    public void remove(Employee employee) {
        workflow.remove(employee);
        System.out.println(employee);

    }

    {
        workflow.add(employees.get(2));
        workflow.add(employees.get(4));
        workflow.add(employees.get(0));
        workflow.add(employees.get(8));
        workflow.add(employees.get(6));
    }

}
