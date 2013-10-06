package com.db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.Student;

public class StudentDao extends HibernateDaoSupport {
	@SuppressWarnings("unchecked")
	public List<Student> getStudentList() {
		return getHibernateTemplate().execute(new HibernateCallback<List<Student>>() {
			public List<Student> doInHibernate(Session session) throws HibernateException, SQLException {
				Query query = session.createQuery("from Student");
				List list = query.list();
				if (list != null && list.size() > 0) {
					return list;
				}
				return new ArrayList();
			}
		});
	}

	public void add(Student stu) {
		getHibernateTemplate().save(stu);
	}
}
