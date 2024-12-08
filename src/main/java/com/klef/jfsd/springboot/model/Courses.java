package com.klef.jfsd.springboot.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Courses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseid;

    private String coursename;
    private String coursecoordinator;
    
    @OneToMany(mappedBy = "course")
    private List<Section> sections;

    // No-arg constructor
    public Courses() {
    }

    // Getters and setters
    public Long getCourseid() {
        return courseid;
    }

    public void setCourseid(Long courseid) {
        this.courseid = courseid;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursecoordinator() {
        return coursecoordinator;
    }

    public void setCoursecoordinator(String coursecoordinator) {
        this.coursecoordinator = coursecoordinator;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
