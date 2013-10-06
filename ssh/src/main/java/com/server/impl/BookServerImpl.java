package com.server.impl;

import java.util.List;

import com.dao.BookDao;
import com.entity.Book;
import com.entity.PageBean;
import com.server.BookServer;

public class BookServerImpl implements BookServer {
	private BookDao bookDao;

	public Book saveOrUpdate(Book book) {
		return bookDao.saveOrUpdate(book);
	}

	public Book delete(int id) {
		Book book = new Book();
		book.setId(id);
		return bookDao.delete(book);
	}

	public Book delete(Book book) {
		return bookDao.delete(book);
	}

	public int deletes(int[] ids) {
		return bookDao.delete(ids);
	}

	public PageBean<Book> queryBean(Book book, int page, int row) {
		return bookDao.queryBean(book, page, row);
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public List<Book> query() {
		return bookDao.query(null, 1);
	}
}
