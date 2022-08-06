package org.example.repositories;

import org.example.entity.Application;
import org.example.util.enums.Branch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.PriorityQueue;
import java.util.Queue;

class ApplicationRepositoryTest {

    private ApplicationRepository applicationRepository;
    @BeforeEach
    void setup(){
        applicationRepository = new ApplicationRepository();
    }


    @Test
    @DisplayName("Should add new Application to Queue")
    void addApplication() {
        Application application = new Application(
                "Hairy Potter", "HBO", Branch.FINANCE);
        applicationRepository.addApplication(application);
        PriorityQueue<Application> applicationsQueue = applicationRepository.getApplications();
        Assertions.assertTrue(
                applicationsQueue.contains(application));

    }

    @Test
    @DisplayName("Should retrieve first object in the Queue")
    void shouldRetrieveFirstObjectInQueue() {
        Application firstPriority = applicationRepository.getApplications().peek();
        Application alsoFirstPriority = applicationRepository.next();
        Assertions.assertEquals(firstPriority, alsoFirstPriority);
    }

    @Test
    @DisplayName("Should remove first object in de Queue")
    void shouldRemoveFirstObjectInQueue() {
        int initialSize = applicationRepository.getApplications().size();
        applicationRepository.next();
        int sizeAfter = applicationRepository.getApplications().size();
        Assertions.assertTrue((sizeAfter == initialSize - 1));
    }

    @Test
    @DisplayName("Return all applications for the given branch")
    void sortApplicationBySingleBranch() {
        PriorityQueue<Application> applications = applicationRepository.getApplications();
        String branch = "IT";
        int applicantsForIT = applicationRepository.sortApplicationBySingleBranch(branch).size();
        int applicantsCounter = 0;
        for (Application application: applications){
            if (application.getVacature() == Branch.valueOf(branch)){
                applicantsCounter++;
            }
        }
        Assertions.assertEquals(applicantsForIT, applicantsCounter);

    }
}