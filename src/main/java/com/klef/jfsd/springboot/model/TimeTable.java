package com.klef.jfsd.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class TimeTable {

    @Id
    private Long id;
    private String courseName;
    private String facultyName;
    private String days;
    private String timeSlot;

    // Default constructor
    public TimeTable() {}

    // Constructor with parameters
    public TimeTable(String courseName, String facultyName, String days, String timeSlot) {
        this.courseName = courseName;
        this.facultyName = facultyName;
        this.days = days;
        this.timeSlot = timeSlot;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
}
