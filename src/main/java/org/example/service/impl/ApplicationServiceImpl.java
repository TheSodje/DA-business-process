package org.example.service.impl;

import org.example.entity.Application;
import org.example.repositories.ApplicationRepository;
import org.example.service.ApplicationService;
import org.example.util.enums.Branch;

import java.util.Queue;

public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepo;

    public ApplicationServiceImpl(ApplicationRepository applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    @Override
    public Queue<Application> getApplications() {
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
    public void sortApplicationBySingleBranch(String branch) {
        applicationRepo.sortApplicationBySingleBranch(branch);
    }
}
