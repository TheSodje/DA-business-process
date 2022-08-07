package org.example.service.impl;

import org.example.entity.Application;
import org.example.repositories.ApplicationRepository;
import org.example.service.ApplicationService;
import org.example.util.enums.Branch;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepo;

    public ApplicationServiceImpl(ApplicationRepository applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    @Override
    public PriorityQueue<Application> getApplications() {
        return applicationRepo.getApplications();
    }

    @Override
    public boolean addApplication(Application application) {
        return applicationRepo.addApplication(application);
    }

    @Override
    public Application next() {
        return applicationRepo.next();
    }

    @Override
    public ArrayList<Application> sortApplicationBySingleBranch(String branch){
        ArrayList<Application> applicationsList = new ArrayList<>();
        for (Application application: applicationRepo.getApplications()){
            if (application.getVacature() == Branch.valueOf(branch)){
                applicationsList.add(application);
            }
        }
        return applicationsList;
    }
}
