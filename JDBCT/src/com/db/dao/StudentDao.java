package com.db.dao;

import java.sql.SQLException;

import com.db.model.Student;

public class StudentDao extends BaseDao {
	public int add(Student stu) throws SQLException {
		String sql = "insert into student(name,pwd) value('" + stu.getName() + "','" + stu.getPwd() + "')";
		return getConnection().createStatement().executeUpdate(sql);
	}

	public int delete(int id) throws SQLException {
		return getConnection().createStatement().executeUpdate("delete from student where id = " + id);
	}

}
