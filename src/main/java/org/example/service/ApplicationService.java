package org.example.service;

import org.example.entity.Application;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface ApplicationService {

    PriorityQueue<Application> getApplications();

    boolean addApplication(Application application);

    Application next();

    ArrayList<Application> sortApplicationBySingleBranch(String branch);
}
