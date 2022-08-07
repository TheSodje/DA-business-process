package org.example.service;

import org.example.entity.Application;
import org.example.util.enums.Branch;

import java.util.ArrayList;
import java.util.Queue;

public interface ApplicationService {

    Queue<Application> getApplications();

    boolean addApplication(Application application);

    Application next();

    ArrayList<Application> sortApplicationBySingleBranch(String branch);
}
