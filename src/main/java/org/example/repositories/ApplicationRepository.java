package org.example.repositories;

import org.example.entity.Application;
import org.example.util.enums.Branch;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ApplicationRepository {


    static class ApplicationSorter implements Comparator<Application> {

        @Override
        public int compare(Application o1, Application o2) {

            String education1 = o1.getEducation();
            String education2 = o2.getEducation();

            Map<String, Integer> map = new HashMap<>();
            map.put("HBO", 2);
            map.put("MBO", 1);

            int compareResult = 0;

            if (map.containsKey(education1) && map.containsKey(education2)) {
                int rank1 = map.get(education1);
                int rank2 = map.get(education2);
                int result = rank1 - rank2;

                if (result > 0) {
                    compareResult = -1;
                }
                if (result < 0) {
                    compareResult = 1;
                }

            }
            return compareResult;
        }
    }

    private final PriorityQueue<Application> applications = new PriorityQueue<>(new ApplicationSorter());

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
        applications.add(new Application("Dwight Schrute", "MBO", Branch.SALES, LocalDateTime.now().plusHours(2)));
        applications.add(new Application("Stephen A Smith", "HBO", Branch.PR, LocalDateTime.now().minusHours(5)));
        applications.add(new Application("Michael Schmid", "MBO", Branch.FINANCE, LocalDateTime.now().minusDays(4)));
        applications.add(new Application("Trey Lance", "HBO", Branch.FINANCE, LocalDateTime.now().minusDays(10)));
        applications.add(new Application("Rick Grimes", "MBO", Branch.HR, LocalDateTime.now().plusHours(3)));
        applications.add(new Application("Jim Harper", "HBO", Branch.SALES, LocalDateTime.now().minusHours(8)));
        applications.add(new Application("John Smith", "MBO", Branch.IT, LocalDateTime.now().minusDays(6)));
        applications.add(new Application("Mike Jones", "HBO", Branch.HR, LocalDateTime.now().minusWeeks(1)));
    }
}
