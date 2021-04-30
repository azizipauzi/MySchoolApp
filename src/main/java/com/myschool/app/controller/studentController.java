package com.myschool.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.app.exception.ResourceNotFoundException;
import com.myschool.app.model.students;
import com.myschool.app.repository.studentRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
public class studentController {
	
	@Autowired
	studentRepository stdRepo;
	
	//List all student and search by course
	@GetMapping("/students")
	public ResponseEntity<List<students>> getAllStudents(@RequestParam(required = false) String course) {
	    try {
	      List<students> std = new ArrayList<students>();

	      if (course == null)
	    	  stdRepo.findAll().forEach(std::add);
	      else
	    	  stdRepo.findByStdcourseContaining(course).forEach(std::add);

	      if (std.isEmpty()) {	    	 
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(std, HttpStatus.OK);
	    } catch (Exception e) {
	    	System.out.println("error----------------");
	    	return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }	
	
	//insert new student
	@PostMapping("/students")
	public students addStudents(@RequestBody students std) {
		
		return stdRepo.save(std);
		
	}
	
	//delete students
	@DeleteMapping("/students/{id}")
	public ResponseEntity < Map < String, Boolean >> deleteStudents(@PathVariable Long id) {
		students std = stdRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Student with id :" + id + " is not exist."));

		stdRepo.delete(std);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
	
	//update student
	@PutMapping("/students/{id}")	
	public ResponseEntity < students > updateStudents(@PathVariable Long id, @RequestBody students studentDetails) {
		students std = stdRepo.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Student with id :" + id + " is not exist."));
		
		std.setStdfname(studentDetails.getStdfname());
		std.setStdlname(studentDetails.getStdlname());
		std.setStdcourse(studentDetails.getStdcourse());
		
		students updateStd = stdRepo.save(std);
		
		return ResponseEntity.ok(updateStd);
		
	}
	
	//get student by id
    @GetMapping("/students/{id}")
    public ResponseEntity < students > getStudentsById(@PathVariable Long id) {
    	
    	students std = stdRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student with id :" + id + " is not exist."));
    	
        return ResponseEntity.ok(std);
    }        

}
