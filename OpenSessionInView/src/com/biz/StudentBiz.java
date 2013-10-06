package com.biz;

import java.util.List;

import com.db.dao.StudentDao;
import com.entity.Student;

public class StudentBiz {
	private StudentDao studentDao;

	public List<Student> getStudentList() {
		List<Student> list = studentDao.getStudentList();
		// for (Student stu : list) {
		// Set<Teacher> set = stu.getTeachers();
		// for (Teacher t : set) {
		// t.getName();
		// }
		// }
		return list;
	}

	public void add(Student stu) {
		studentDao.add(stu);
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
}
