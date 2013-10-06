package com.bean.dto;

import com.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDTO {
    private int id;
    private String name;

    public BookDTO() {

    }

    public BookDTO(Book book) {
        setId(book.getId());
        setName(book.getName());
    }

    public Book toBook() {
        return build2Book(this);
    }

    public static Book build2Book(BookDTO dto) {
        if (dto == null) {
            return null;
        }
        Book b = new Book();
        b.setName(dto.name);
        return b;
    }

    public static List<Book> build2Books(List<BookDTO> dtos) {
        List<Book> books = new ArrayList<Book>();
        for (BookDTO dto : dtos) {
            books.add(build2Book(dto));
        }
        return books;
    }

    public static BookDTO build2DTO(Book book) {
        if (book == null) {
            return null;
        }
        BookDTO dto = new BookDTO(book);
        return dto;
    }

    public static List<BookDTO> build2DTOs(List<Book> books) {
        List<BookDTO> dtos = new ArrayList<BookDTO>();
        for (Book book : books) {
            dtos.add(build2DTO(book));
        }
        return dtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
