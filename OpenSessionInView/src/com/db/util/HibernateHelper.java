package com.db.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateHelper {
	private static SessionFactory sessionFactory = null;

	public static Object execute(HibernateCallback callback) {
		Session openSession = getSession();
		try {
			openSession.beginTransaction();
			Object result = callback.doInHibernate(openSession);
			openSession.flush();
			openSession.close();
			openSession.getTransaction().commit();
			return result;
		} catch (Exception e) {
			openSession.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	public interface HibernateCallback {
		Object doInHibernate(Session session);
	}

	public static Session getSession() {
		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
}
