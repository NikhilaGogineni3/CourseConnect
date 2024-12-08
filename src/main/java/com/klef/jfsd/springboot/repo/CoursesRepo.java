package com.klef.jfsd.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.klef.jfsd.springboot.model.Courses;

// Add a custom query method to fetch courses by course name
public interface CoursesRepo extends JpaRepository<Courses, Long> {
}
