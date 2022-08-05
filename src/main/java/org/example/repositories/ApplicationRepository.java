package org.example.repositories;

import org.example.entity.Application;
import org.example.util.enums.Branch;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ApplicationRepository {

    static class Compare implements Comparator<Application> {

        @Override
        public int compare(Application o1, Application o2) {
            String education1 = o1.getEducation();
            String education2 = o2.getEducation();

            String first_prio = "Master";
            String second_prio = "HBO";
            String third_prio = "MBO";

            if (education1.equalsIgnoreCase(second_prio) && education2.equalsIgnoreCase(third_prio)) {
                return -1;
            }
            if (education1.equalsIgnoreCase(third_prio) && education2.equalsIgnoreCase(second_prio)) {
                return 1;
            }
            if (education1.equalsIgnoreCase(first_prio) && education2.equalsIgnoreCase(second_prio)) {
                return -1;
            }
            if (education1.equalsIgnoreCase(first_prio) && education2.equalsIgnoreCase(third_prio)) {
                return -1;
            }
            if (education1.equalsIgnoreCase(second_prio) && education2.equalsIgnoreCase(first_prio)) {
                return 1;
            }
            if (education1.equalsIgnoreCase(third_prio) && education2.equalsIgnoreCase(first_prio)) {
                return 1;
            }
            return 0;

        }
    }

    private final PriorityQueue<Application> applications = new PriorityQueue<>(new Compare());

    public PriorityQueue<Application> getApplications() {
        return applications;
    }

    public boolean addApplication(Application application) {
        application.setSubmissionTime(LocalDateTime.now());
        return applications.offer(application);
    }

    public Application next() {
        return applications.poll();
    }

    private static void sleeper(long milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    {
        applications.add(new Application("Dwight Schrute", "HBO", Branch.IT, LocalDateTime.now()));
        sleeper(3000);
        applications.add(new Application("Michael Schmid", "Master", Branch.FINANCE, LocalDateTime.now()));
        sleeper(2000);
        applications.add(new Application("Rick Grimes", "HBO", Branch.HR, LocalDateTime.now()));
        applications.add(new Application("Jim Harper", "Master", Branch.SALES, LocalDateTime.now()));
        sleeper(1500);
        applications.add(new Application("John Smith", "MBO", Branch.IT, LocalDateTime.now()));
    }
}
