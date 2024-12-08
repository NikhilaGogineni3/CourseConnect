package com.klef.jfsd.springboot.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Courses;
import com.klef.jfsd.springboot.model.Register;

@Service
public interface StudentService {
	

	Register registrationPageDetails(Register register);
	
	Register loginPage(String id, String password);

	
	static List<Courses> getAllCoursesData() {
		// TODO Auto-generated method stub
		return null;
	} 
	
}
