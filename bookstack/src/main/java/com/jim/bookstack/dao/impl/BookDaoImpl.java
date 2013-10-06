package com.jim.bookstack.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.jdbc.Work;

import com.jim.bookstack.bean.PageBean;
import com.jim.bookstack.dao.BookDao;
import com.jim.bookstack.entity.Book;

public class BookDaoImpl implements BookDao {
	protected Logger log = Logger.getLogger(BookDaoImpl.class);
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)如果想看注释，请把鼠标放在方法名上
	 * 
	 * @see com.dao.BookDao#saveOrUpdate(com.entity.Book)
	 */
	public Book saveOrUpdate(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
		Book b = get(book.getId());
		if (null != b && b.equals(book)) {
			return b;
		}
		return null;
	}

	/*
	 * (non-Javadoc)如果想看注释，请把鼠标放在方法名上
	 * 
	 * @see com.dao.BookDao#delete(int[])
	 */
	public int delete(final int[] idss) {
		Session session = sessionFactory.getCurrentSession();
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				String sql = "delete from Book where Book.id in (?)";
				sql = sql.replace("?", arrayToString(idss));
				log.debug(sql);
				int r = conn.prepareStatement(sql).executeUpdate();
				if (r != idss.length) {
					throw new SQLException("批量删除失败");
				}
			}
		});
		return 0;
	}

	/*
	 * (non-Javadoc)如果想看注释，请把鼠标放在方法名上
	 * 
	 * @see com.dao.BookDao#get(int)
	 */
	public Book get(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Book) session.get(Book.class, id);
	}

	/*
	 * (non-Javadoc)如果想看注释，请把鼠标放在方法名上
	 * 
	 * @see com.dao.BookDao#delete(com.entity.Book)
	 */
	public Book delete(Book book) {
		delete(new int[] { book.getId() });
		return book;
	}

	/*
	 * (non-Javadoc)如果想看注释，请把鼠标放在方法名上
	 * 
	 * @see com.dao.BookDao#query(com.entity.Book, int)
	 */
	@SuppressWarnings("unchecked")
	public List<Book> query(Book book, int page) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Book b where 1=1";
		if (null != book) {
			if (book.getId() != 0) {
				hql += " and b.id = :id";
			}
			if (null != book.getName() && "" != book.getName()) {
				hql += " and b.name like :name";
			}
		}
		hql += " order by id";
		log.debug("hql:" + hql);
		Query query = session.createQuery(hql);
		if (null != book) {
			if (book.getId() != 0) {
				query.setInteger("id", book.getId());
			}
			if (null != book.getName() && "" != book.getName()) {
				query.setString("name", book.getName() + "%");
			}
		}
		return query.list();
	}

	/*
	 * (non-Javadoc)如果想看注释，请把鼠标放在方法名上
	 * 
	 * @see com.dao.BookDao#queryBean(com.entity.Book, int, int)
	 */
	public PageBean<Book> queryBean(Book book, int current, int row) {
		Session session = sessionFactory.getCurrentSession();
		//查询符合条件的集合
		String hql = "from Book b where 1=1";
		//查询符合条件的总数据个数
		String sql = "select count(*) from Book b where 1=1";
		if (null != book) {
			if (book.getId() != 0) {
				hql += " and b.id = :id";
				sql += " and b.id = " + book.getId();
			}
			if (null != book.getName() && "" != book.getName()) {
				hql += " and b.name like :name";
				sql += " and b.name like '" + book.getName() + "%'";
			}
		}
		hql += " order by id";
		sql += " order by b.id";
		log.debug("hql:" + hql);
		log.debug("sql:" + sql);
		Query query = session.createQuery(hql);
		if (null != book) {
			if (book.getId() != 0) {
				query.setInteger("id", book.getId());
			}
			if (null != book.getName() && "" != book.getName()) {
				query.setString("name", book.getName() + "%");
			}
		}
		final String s = sql;
		final PageBean<Book> pb = new PageBean<Book>();
		//使用jdbc来获取符合条件的总数据数
		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				int count = 0;
				PreparedStatement ps = conn.prepareStatement(s);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					count = rs.getInt(1);
				}
				pb.setCount(count);
				log.debug("count:" + count);
			}
		});
		//分页
		query.setFirstResult((current - 1) * row);
		query.setMaxResults(row);

		pb.setCurrent(current);
		pb.setResult(query.list());
		pb.setRow(row);
		pb.init();//如果想看注释，请把鼠标放在方法名上
		return pb;
	}

	/**
	 * 把一个int数组，转换为字符串格式，供批量删除用
	 * 
	 * @param ids
	 *            int数组
	 * @return 字符串格式
	 */
	public String arrayToString(int[] ids) {
		int len = ids.length;
		String str = "";
		for (int i = 0; i < len; i++) {
			str += ids[i] + ",";
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
