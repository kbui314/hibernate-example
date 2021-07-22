package com.mycompany.app.models;

import java.util.Set;

public class Employee {

	private int id;

	private String firstName, lastName;
	
	private Set<Seminar> seminars;

	public Employee() {
		super();
	}
	
	public Employee(int id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Set<Seminar> getSeminars() {
		return seminars;
	}

	public void setSeminars(Set<Seminar> seminars) {
		this.seminars = seminars;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", seminars=" + seminars
				+ "]";
	}
	
}
