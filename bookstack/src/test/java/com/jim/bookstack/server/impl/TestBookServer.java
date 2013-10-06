package com.jim.bookstack.server.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.google.gson.Gson;
import com.jim.bookstack.entity.Book;
import com.jim.bookstack.server.BookServer;

@ContextConfiguration(locations = { "classpath:applicationContext-jdbc.xml" })
@TransactionConfiguration(transactionManager = "txManager")
public class TestBookServer extends AbstractTransactionalJUnit4SpringContextTests {

	private BookServer bookServer;

	public BookServer getBookServer() {
		return bookServer;
	}

	@Autowired
	public void setBookServer(BookServer bookServer) {
		this.bookServer = bookServer;
	}

	@Test
	public void testGetAllBookName() {
		Gson g = new Gson();
		Assert.assertNotNull(getBookServer());

		Book book = new Book();
		book.setName("西游记");
		int id = getBookServer().saveOrUpdate(book).getId();

		List<Book> query = getBookServer().query();
		Assert.assertTrue(query.size() > 0);
		for (Book b : query) {
			System.out.println(g.toJson(b));
		}
	}
}
