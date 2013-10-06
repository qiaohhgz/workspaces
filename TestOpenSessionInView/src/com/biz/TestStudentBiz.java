package com.biz;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.AbstractSpringTest;
import com.entity.Student;
import com.entity.Teacher;

public class TestStudentBiz extends AbstractSpringTest {
	@Test
	@Rollback(true)
	public void testAdd() {
		getStudentBiz().add(new Student("jimtest"));
	}
	
	@Test
	public void testGet(){
		List<Student> list = getStudentBiz().getStudentList();
		for (Student s : list) {
			Set<Teacher> ste = s.getTeachers();
			for (Teacher t : ste) {
				System.out.println(t.getName());
			}
		}
	}
}
