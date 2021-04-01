package com.cognixia.jump.springboot.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cognixia.jump.springboot.model.Student;
import com.cognixia.jump.springboot.service.StudentService;


@RequestMapping("/api")
@RestController
public class StudentController {

	@Autowired
	StudentService service;
	
	@GetMapping("/students")
	public List<Student> getAllStudents(){
		return service.getAllStudents();
	}
	
	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable String id) {
		return service.getStudentById(Integer.parseInt(id));
	}
	
	@GetMapping("/students/major/{major}")
	public List<Student> getStudentInMajor(@PathVariable String major){
		return service.getStudentByMajor(major);
	}
	
	@DeleteMapping("/deleteStudent/{id}")
	public void deleteStudent(@PathVariable String id) {
		Student deleted = service.deleteStudent(Integer.parseInt(id));
		
		System.out.println("Student deleted " + deleted);
	}
	
	@PostMapping("/addStudent")
	public ResponseEntity<String> addStudent(@RequestBody Student student){
		
		Student newStudent = service.addStudent(student.getFirstName(), 
												student.getLastName(), 
												student.getDob(), 
												student.getMajor());
		
		System.out.println("New student added: " + newStudent);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newStudent.getId()).toUri();
		
		return ResponseEntity.created(location).header("student", newStudent.getId() + "")
														.body(newStudent.getFirstName() + " " + newStudent.getLastName());
	}
	
	@PutMapping("/updateStudent")
	public String updateStudent(@RequestBody Student student) {
		
		Student updated = service.updateStudent(student);
		
		System.out.println("Student updated: " + updated);
		
		return updated.toString();
	}
}
