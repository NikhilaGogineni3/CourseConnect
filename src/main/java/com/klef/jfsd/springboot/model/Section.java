package com.klef.jfsd.springboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Section {

    @Id
    private Long sectionid;

    @ManyToOne
    private Courses course;

    // Getters and setters
    public Long getSectionid() {
        return sectionid;
    }

    public void setSectionid(Long sectionid) {
        this.sectionid = sectionid;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}
