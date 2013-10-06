package com.entity;

import java.util.HashSet;
import java.util.Set;

public class Teacher {
	private int id;
	private String name;
	private Set<Student> stus = new HashSet<Student>();

	public Teacher() {
	}

	public Teacher(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Student> getStus() {
		return stus;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStus(Set<Student> stus) {
		this.stus = stus;
	}
}
