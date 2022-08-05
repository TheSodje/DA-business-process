package org.example.repositories;

import org.example.entity.Application;
import org.example.util.enums.Branch;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ApplicationRepository {


    static class Compare implements Comparator<Application> {

        @Override
        public int compare(Application o1, Application o2) {
            String education1 = o1.getEducation();
            String education2 = o2.getEducation();

            Map<String, Integer> map = new HashMap<>();
            map.put("MASTER", 3);
            map.put("HBO", 2);
            map.put("MBO", 1);

            int compareResult = 0;
            boolean isInMap = false;

            if (map.containsKey(education1) && map.containsKey(education2)){
                isInMap = true;
            }

            if (isInMap){
                int rank1 = map.get(education1);
                int rank2 = map.get(education2);
                int result = rank1 - rank2;

                if (result > 0) {
                    compareResult = -1;
                } if (result < 0) {
                    compareResult = 1;
                }
            }

            System.out.println(compareResult);
            return compareResult;
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
        applications.add(new Application("Michael Schmid", "MASTER", Branch.FINANCE, LocalDateTime.now()));
        sleeper(2000);
        applications.add(new Application("Rick Grimes", "HBO", Branch.HR, LocalDateTime.now()));
        applications.add(new Application("Jim Harper", "MASTER", Branch.SALES, LocalDateTime.now()));
        sleeper(1500);
        applications.add(new Application("John Smith", "MBO", Branch.IT, LocalDateTime.now()));
    }
}
