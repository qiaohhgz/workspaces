package com.jim.bookstack.builder;

import com.jim.bookstack.bean.BookBean;
import com.jim.bookstack.entity.Book;

public class BookBuilder {
	public static BookBean getBookBean(Book book) {
		BookBean b = new BookBean();
		b.setName(book.getName());
		return b;
	}
}

