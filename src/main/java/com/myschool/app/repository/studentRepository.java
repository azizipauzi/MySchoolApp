package com.myschool.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myschool.app.model.students;

public interface studentRepository extends CrudRepository<students, Long>{
	List<students> findByStdcourseContaining(String stdcourse);
}
