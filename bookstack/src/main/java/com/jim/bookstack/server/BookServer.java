package com.jim.bookstack.server;

import java.util.List;

import com.jim.bookstack.bean.PageBean;
import com.jim.bookstack.entity.Book;

public interface BookServer {
	public Book saveOrUpdate(Book book);

	public Book delete(Book book);

	public Book delete(int id);

	public int deletes(int[] ids);

	public PageBean<Book> queryBean(Book book, int page, int row);

	public List<Book> query();
}
