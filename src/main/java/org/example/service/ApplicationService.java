package org.example.service;

import org.example.entity.Application;
import org.example.util.enums.Branch;

import java.util.Queue;

public interface ApplicationService {

    Queue<Application> getApplications();

    boolean addApplication(Application application);

    Application next();

    void sortApplicationBySingleBranch(String branch);
}
