package com.mycompany.app.models;

import java.util.Set;

public class Seminar {
	
	private int id;
	
	private String title;
	
	private String description;
	
	private Set<Employee> employees;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Seminar [id=" + id + ", title=" + title + ", description=" + description + ", employees=" + employees
				+ "]";
	}

}
