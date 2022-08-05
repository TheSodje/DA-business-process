package org.example.entity;

import org.example.util.enums.Branch;

public class Employee {

    private String employeeId;
    private Branch branch;
    // Score 0-10
    private Byte employeeScore;
    private String fullName;

    public Employee() {
    }

    public Employee(String employeeId,
                    Branch branch,
                    Byte employeeScore,
                    String fullName) {
        this.employeeId = employeeId;
        this.branch = branch;
        this.employeeScore = employeeScore;
        this.fullName = fullName;
    }

    public Employee(String fullName) {
        this.fullName = fullName;
    }

    public Employee(String employeeId, Branch branch, String fullName) {
        this.employeeId = employeeId;
        this.branch = branch;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Byte getEmployeeScore(byte b) {
        return employeeScore;
    }

    public void setEmployeeScore(Byte employeeScore) {
        this.employeeScore = employeeScore;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", branch=" + branch +
                ", employeeScore=" + employeeScore +
                ", firstname='" + fullName + '\'' +
                '}';
    }
}
