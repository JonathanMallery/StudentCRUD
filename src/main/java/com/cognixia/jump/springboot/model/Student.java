package com.cognixia.jump.springboot.model;

import java.time.LocalDate;

public class Student {
	
	private int id;
	private String firstName;
	private String lastName;
	private LocalDate dob;
	private String major;
	
	public Student() {
		this(-1, "N/A", "N/A", LocalDate.now(), "N/A");
	}
	
	public Student(int id, String firstName, String lastName, LocalDate dob, String major) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.major = major;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", major="
				+ major + "]";
	}
	
	

}
