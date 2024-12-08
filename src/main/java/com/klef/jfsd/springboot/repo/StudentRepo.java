package com.klef.jfsd.springboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klef.jfsd.springboot.model.Register;

public interface StudentRepo extends JpaRepository<Register, String> {
	   
	
	@Query("select register from Register register where ( register.name =?1)And register.password=?2")
	Register findByNameAndPassword(String name, String password);

	

}

