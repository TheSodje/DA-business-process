package org.example.service;

import org.example.entity.Application;
import org.example.repositories.ApplicationRepository;
import org.example.service.impl.ApplicationServiceImpl;
import org.example.util.enums.Branch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.PriorityQueue;

class ApplicationServiceTest {

    private ApplicationService applicationService;

    public ApplicationServiceTest(){
        ApplicationRepository applicationRepository = new ApplicationRepository();
        applicationService = new ApplicationServiceImpl(applicationRepository);
    }

    @Test
    @DisplayName("Should add new Application to Queue")
    void addApplication() {
        //Given
        Application application = new Application(
                "Hairy Potter", "HBO", Branch.FINANCE);
        applicationService.addApplication(application);

        //When
        PriorityQueue<Application> applicationsQueue = applicationService.getApplications();

        //Then
        Assertions.assertTrue(
                applicationsQueue.contains(application));

    }

    @Test
    @DisplayName("Should retrieve first object in the Queue")
    void shouldRetrieveFirstObjectInQueue() {
        //Given
        Application firstPriority = applicationService.getApplications().peek();

        //When
        Application alsoFirstPriority = applicationService.next();

        //Then
        Assertions.assertEquals(firstPriority, alsoFirstPriority);
    }

    @Test
    @DisplayName("Should remove first object in de Queue")
    void shouldRemoveFirstObjectInQueue() {
        //Given
        int initialSize = applicationService.getApplications().size();

        //When
        applicationService.next();

        //Then
        int sizeAfter = applicationService.getApplications().size();
        Assertions.assertTrue((sizeAfter == initialSize - 1));
    }

    @Test
    @DisplayName("Return all applications for the given branch")
    void sortApplicationBySingleBranch() {
        //Given
        PriorityQueue<Application> applications = applicationService.getApplications();
        String branch = "IT";
        int applicantsForIT = applicationService.sortApplicationBySingleBranch(branch).size();
        int applicantsCounter = 0;

        //When
        for (Application application: applications){
            if (application.getVacature() == Branch.valueOf(branch)){
                applicantsCounter++;
            }
        }

        //Then
        Assertions.assertEquals(applicantsForIT, applicantsCounter);

    }
}