package org.example.entity;

import org.example.util.enums.Branch;

import java.time.LocalDateTime;

public class Application{

    private String name;
    private String education;
    private Branch vacature;
    private LocalDateTime submissionTime;

    public Application(String name, String education, Branch vacature, LocalDateTime submissionTime) {
        this.name = name;
        this.education = education;
        this.vacature = vacature;
        this.submissionTime = submissionTime;
    }

    public Application(String name, String education, Branch vacature) {
        this.name = name;
        this.education = education;
        this.vacature = vacature;
    }

    public Application() {

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Branch getVacature() {
        return vacature;
    }

    public void setVacature(Branch vacature) {
        this.vacature = vacature;
    }

    public LocalDateTime getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(LocalDateTime submissionTime) {
        this.submissionTime = submissionTime;
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", education='" + education + '\'' +
                ", vacature='" + vacature + '\'' +
                ", submissionTime= " + submissionTime.getDayOfMonth()+ submissionTime.getMonth()+ submissionTime.getYear() +" "+
                submissionTime.getHour() + ":" + submissionTime.getMinute()+ ":" + submissionTime.getSecond() +
                '}';
    }


}
