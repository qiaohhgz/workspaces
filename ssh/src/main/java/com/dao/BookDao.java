package com.dao;

import java.util.List;

import com.entity.Book;
import com.entity.PageBean;

public interface BookDao {

	/**
	 * 增加或修改
	 * @param book 图书对象
	 * @return 如果增加或修改成功返回该对象，如果失败返回空
	 */
	public Book saveOrUpdate(Book book);

	/**
	 * 根据id获取一个Book对象
	 * @param id 编号
	 * @return 如果找到返回该对象，否则返回空
	 */
	public Book get(int id);

	/**
	 * 批量删除
	 * @param ids 编号数组
	 * @return 删除的数量
	 */
	public int delete(int[] ids);

	/**
	 * 删除一个对象
	 * @return 如果删除成功返回该对象，否则返回空
	 */
	public Book delete(Book book);

	/**
	 * 根据查询条件获得对象集合
	 * @param book 查询书的条件
	 * @param page 页码
	 * @return 集合
	 */
	public List<Book> query(Book book, int page);

	/**
	 * 根据查询条件获得分页PageBean对象
	 * @param book 查询书的条件
	 * @param page 页码
	 * @param row 显示行数
	 * @return 分页PageBean对象
	 */
	public PageBean<Book> queryBean(Book book, int page, int row);
}
