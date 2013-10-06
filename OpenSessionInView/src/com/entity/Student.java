package com.entity;

import java.util.HashSet;
import java.util.Set;

public class Student {
	private int id;
	private String name;
	private Set<Teacher> teachers = new HashSet<Teacher>();

	public Student(String name) {
		this.name = name;
	}

	public Student() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
}
