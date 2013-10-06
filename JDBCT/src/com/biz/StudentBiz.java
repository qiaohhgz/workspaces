package com.biz;

import java.sql.Connection;
import java.sql.SQLException;

import com.db.dao.BaseDao;
import com.db.dao.StudentDao;
import com.db.model.Student;
import com.db.util.ITransaction;
import com.db.util.JDBCHelper;

public class StudentBiz extends BaseBiz {
	private StudentDao dao = new StudentDao();

	public void test() {
		Student stu = new Student();
		stu.setName("jim");
		stu.setPwd("12345");
		Connection conn = JDBCHelper.openConnection();
		try {
			if (conn == null) {
				throw new SQLException();
			}
			dao.setConnection(conn);
			dao.delete(2);
			dao.add(stu);
			conn.commit();
			System.out.println("-->commit");
		} catch (SQLException e) {
			try {
				if (conn != null) {
					conn.rollback();
					conn.close();
					System.out.println("-->rollback -->close");
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void testTransaction() {
		final Student stu = new Student();
		stu.setName("jim");
		stu.setPwd("12345");
		Object result = excute(new ITransaction() {
			public Object execute() throws SQLException {
				String msg = "";
				msg += "delete " + dao.delete(3) + "\n";
				msg += "add " + dao.add(stu) + "\n";
				return msg;
			}
		});
		System.out.println(result);
	}

	@Override
	public BaseDao[] getDao() {
		return new BaseDao[] { dao };
	}

	public static void main(String[] args) {
		new StudentBiz().testTransaction();
	}
}
