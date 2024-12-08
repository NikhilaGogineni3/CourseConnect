package com.klef.jfsd.springboot.springboot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.klef.jfsd.springboot.model.Courses;
import com.klef.jfsd.springboot.model.Register;
import com.klef.jfsd.springboot.repo.CoursesRepo;
import com.klef.jfsd.springboot.repo.StudentRepo;

@Component
public class studentserviceimpl implements StudentService {

    @Autowired
    private StudentRepo studentRepository;

    @Autowired
    private CoursesRepo coursesRepo;

    @Override
    public Register registrationPageDetails(Register register) {
        // Save the registration details (student registration for a course)
        return studentRepository.save(register);
    }

    @Override
    public Register loginPage(String name, String password) {
        // Search for the student by name and verify password
        return studentRepository.findByNameAndPassword(name, password);
    }

    public List<Courses> getAllCoursesData() {
        // Fetch and return all courses from the database
        return coursesRepo.findAll();
    }
}
