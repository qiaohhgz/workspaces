package com.pres;

import com.bean.dto.BookDTO;
import com.entity.Book;
import com.entity.PageBean;
import com.server.BookServer;
import org.apache.log4j.Logger;

import java.util.List;

public class BookPresManage {
    protected static final Logger log = Logger.getLogger(BookPresManage.class);
    private BookServer bookServer;

    public List<BookDTO> getBooks() {
        return BookDTO.build2DTOs(bookServer.query());
    }

    public BookDTO add(BookDTO dto) {
        log.info("dto.getName() = " + dto.getName());
        if (dto.getName() == null || dto.getName().equals("")) {
            return null;
        }
        Book b = bookServer.saveOrUpdate(dto.toBook());
        return BookDTO.build2DTO(b);
    }

    public void deleteBook(int id) {
        log.info("delete id = " + id);
        bookServer.delete(id);
    }

    public List<Book> searchBook(String bookNames) {
        if (bookNames == null || "".equals(bookNames)) {
            return null;
        }
        Book book = new Book();
        book.setName(bookNames);
        PageBean<Book> bean = bookServer.queryBean(book, 1, 1000);
        List<Book> result = bean.getResult();
        return result;
    }

    public BookServer getBookServer() {
        return bookServer;
    }

    public void setBookServer(BookServer bookServer) {
        this.bookServer = bookServer;
    }
}
