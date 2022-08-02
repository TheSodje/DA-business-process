package org.example.repositories;

import org.example.entity.Application;
import org.example.util.enums.Branch;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ApplicationRepository {

    private static void sleeper(long milisec){
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

     static class Compare implements Comparator<Application> {

         @Override
         public int compare(Application o1, Application o2) {
             String education1 = o1.getEducation();
             String education2 = o2.getEducation();

             String first_prio = "Master";
             String second_prio = "Bachelor";
             String third_prio = "MBO";

             if (education1.equals(second_prio) && education2.equals(third_prio)){
                 return -1;
             }
             if (education1.equals(first_prio) && (education2.equals(second_prio) || education2.equals(third_prio))) {
                 return -1;
             }
             if ((education1.equals(second_prio) || education1.equals(third_prio)) && education2.equals(first_prio)){
                     return 1;
             }

             if (education1.equals(third_prio) && education2.equals(second_prio)){
                 return 1;
             }

             if(o1.getSubmissionTime().isBefore(o2.getSubmissionTime())){
                 return -1;
             }

             return 0;
         }
     }

    private final Queue<Application> applications = new PriorityQueue<>(new Compare());

    public Queue<Application> getApplications() {
        return applications;
    }

    public boolean addApplication(Application application){
        application.setSubmissionTime(LocalDateTime.now());
        return applications.add(application);
    }

    {
        applications.add(new Application("Dwight Schrute", "MBO", Branch.IT, LocalDateTime.now()));
        sleeper(3000);
        applications.add(new Application("Michael Schmid", "Master", Branch.FINANCE, LocalDateTime.now()));
        sleeper(2000);
        applications.add(new Application("Rick Grimes", "Bachelor", Branch.IT, LocalDateTime.now()));
        applications.add(new Application("Jim Harper", "Master", Branch.SALES, LocalDateTime.now()));
    }
}
