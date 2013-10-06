package com.action;

import java.util.List;
import java.util.Map;

import com.bean.dto.BookDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.RequestAware;

import com.entity.Book;
import com.entity.PageBean;
import com.opensymphony.xwork2.ActionSupport;
import com.server.BookServer;

public class BookAction extends ActionSupport implements RequestAware {
    private static final long serialVersionUID = 7663264157681043425L;
    protected Log log = LogFactory.getLog(getClass());
    /* 需要注入的属性 */
    private Map<String, Object> request;
    private BookServer bookServer;
    /* 返回结果的属性 */
    private List<Book> books;
    private PageBean<Book> bean;
    /* 自动封装的属性 */
    private int[] bids;
    private Book book;
    private Book queryBook;
    private int pi;
    private int row;
    private int bookId;

    public void validateAdd() {
        log.debug("开始增加验证...");
        log.debug("book.name:" + book.getName());
        if (null == book || null == book.getName() || book.getName().length() == 0) {
            log.info("add error");
            addFieldError("book.name", "请输入书名!");
        }
        log.debug("增加验证结束...");
    }

    public String add() throws Exception {
        log.debug("开始增加...");
        bookServer.saveOrUpdate(book);
        request.put("msg", "增加" + book + "成功");
        log.debug("增加成功...");
        return SUCCESS;
    }

    public void validateDelete() {
        log.debug("开始删除验证...");
        if (null == book || book.getId() == 0) {
            log.debug("delete error");
            addFieldError("bookId", "请选择要删除的项!");
            request.put("msg", "删除" + book.getId() + "失败");
        }
        log.debug("删除验证结束...");
    }

    public String delete() throws Exception {
        log.debug("开始删除...");
        bookServer.delete(book.getId());
        request.put("msg", "删除" + book + "成功");
        log.debug("删除成功...");
        return SUCCESS;
    }

    public void validateDeletes() {
        log.debug("开始批量删除验证...");
        if (null == bids || bids.length == 0) {
            log.debug("deletes error");
            addFieldError("bids", "请选择要删除的项!");
        }
        log.debug("批量删除验证结束...");
    }

    public String deletes() throws Exception {
        log.debug("开始批量删除...");
        bookServer.deletes(bids);
        request.put("msg", "删除" + bids.length + "条数据成功");
        log.debug("批量删除成功...");
        return SUCCESS;
    }

    public void validateQuery() {
        log.debug("开始查询验证...");
        log.debug("查询验证结束...");
    }

    public String query() throws Exception {
        log.debug("开始查询...");
        row = row <= 0 ? 5 : row;
        this.bean = bookServer.queryBean(queryBook, pi, row);
        log.debug("查询成功...");
        return SUCCESS;
    }

    public void validateGetBookById() {
        log.info("开始查询验证...");
        if (bookId == 0) {
            addFieldError("bookId", "id is null");
        }
        log.info("查询验证结束...");
    }

    public String getBookById() {
        log.info("get book by id = " + bookId);
        book = new Book();
        book.setId(bookId);
        book.setName(null);
        List<Book> result = getBookServer().queryBean(book, 1, 1000).getResult();
        BookDTO bookDTO = new BookDTO(result.get(0));
        request.put("book", bookDTO);
        return SUCCESS;
    }

    public PageBean<Book> getBean() {
        return bean;
    }

    public int[] getBids() {
        return bids;
    }

    public Book getBook() {
        return book;
    }

    public List<Book> getBooks() {
        return books;
    }

    public int getPi() {
        return pi;
    }

    public void setBean(PageBean<Book> bean) {
        this.bean = bean;
    }

    public void setBids(int[] bids) {
        this.bids = bids;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setPi(int pi) {
        this.pi = pi;
    }

    public void setRequest(Map<String, Object> session) {
        this.request = session;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public Book getQueryBook() {
        return queryBook;
    }

    public void setQueryBook(Book queryBook) {
        this.queryBook = queryBook;
    }

    public BookServer getBookServer() {
        return bookServer;
    }

    public void setBookServer(BookServer bookServer) {
        this.bookServer = bookServer;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
