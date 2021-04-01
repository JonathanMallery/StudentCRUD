package com.cognixia.jump.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.cognixia.jump.springboot.model.Student;

@Service
public class StudentService {
	
	private static List<Student> studentDatabase = new ArrayList<>();
	private static int idCounter = 1;
	
	static {
		studentDatabase.add(new Student(idCounter++, "Naruto", "Uzumaki", LocalDate.of(1997, 10, 10), "Ninjutsu"));
		studentDatabase.add(new Student(idCounter++, "Spike", "Spiegel", LocalDate.of(2044, 6, 26), "Bounty Hunter"));
		studentDatabase.add(new Student(idCounter++, "Sonic", "The Hedgehog", LocalDate.of(1991, 6, 23), "Speed"));
		studentDatabase.add(new Student(idCounter++, "Peppa", "Pig", LocalDate.of(2004, 5, 30), "Ninjutsu"));
		studentDatabase.add(new Student(idCounter++, "Sharpay", "Evans", LocalDate.of(1985, 7, 2), "Theater"));
		studentDatabase.add(new Student(idCounter++, "Dio", "Brando", LocalDate.of(1868, 4, 4), "Theater"));
	}
	
	public List<Student> getAllStudents(){
		
		return studentDatabase;
	}

	public Student getStudentById(int id) {
		
		for(int i = 0; i < studentDatabase.size(); i++) {
			if (studentDatabase.get(i).getId() == id ) {
				return studentDatabase.get(i);
			}
		}
		
//		Student student = studentDatabase.parallelStream().filter(s -> s.getId() == id)
//																.findAny().orElse(null);
//		return student;
		
//		return (Student) studentDatabase.stream().filter(student -> student.getId()==id);
		
		return new Student();
	}
	
	public List<Student> getStudentByMajor(String major){
		
		List<Student> studentsInMajor = new ArrayList<>();
		
		studentsInMajor = studentDatabase.parallelStream().filter(s -> s.getMajor().equalsIgnoreCase(major))
														  .collect(Collectors.toList());
		
		return studentsInMajor;
	}
	
	//delete request - return a student to have confirmation on which is deleted
		public Student deleteStudent(int id) {
			Student studentToRemove = getStudentById(id);
			
			if(studentToRemove != null) {
				studentDatabase.remove(studentToRemove);
			}
			
			return studentToRemove;
		}
		
		//create a student
		public Student addStudent(String firstName, String lastName, LocalDate dob, String major) {
			Student newStudent = new Student(idCounter++, firstName, lastName, dob, major);
			
			return newStudent;
			
		}
		
		// to use in put request - update student
		public Student updateStudent(Student student) {
			
			Student toUpdate = getStudentById(student.getId());
			
			if(toUpdate != null) {
				toUpdate.setFirstName(student.getFirstName());;
				toUpdate.setLastName(student.getLastName());
				toUpdate.setDob(student.getDob());
				toUpdate.setMajor(student.getMajor());
			}
			
			return toUpdate;
		}
	
}
