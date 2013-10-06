package com.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.entity.Product;

public class TestProductDao {

	private ClassPathXmlApplicationContext ctx;
	private ProductDao dao;

	@Before
	public void setConfig() {
		ctx = new ClassPathXmlApplicationContext(getConfigLocations());
		dao = getProductDao();
	}

	public ProductDao getProductDao() {
		ProductDao bean = ctx.getBean("productDao", ProductDao.class);
		assertNotNull(bean);
		return bean;
	}

	public String[] getConfigLocations() {
		return new String[] { "a*.xml" };
	}

	@Test
	public void testProductDao() {
		Product p = new Product();
		p.setId("1000");
		p.setName("orange");
		p.setPrice(99.99);
		p.setStatus(1);

		Product t = dao.getById(p.getId());
		while (null != t) {
			dao.delete(t);
			t = dao.getById(p.getId());
		}
		// add
		dao.add(p);

		// getById
		p = dao.getById(p.getId());
		assertNotNull(p);

		Product newp = new Product();
		newp.setId(p.getId());
		newp.setName("jim");
		// update
		p = dao.update(newp);
		assertEquals(dao.getById(p.getId()).getName(), "jim");

		// getAll
		for (Product item : dao.getAll()) {
			System.out.println(item);
		}

		// delete
		dao.deleteById(p.getId());

		p = dao.getById(p.getId());
		assertNull(p);
	}

	@Test
	public void testGetAll() {
		for (Product p : dao.getAll()) {
			System.out.println(p);
		}
	}

	@Test
	public void testDeleteById() {
		dao.deleteById("4fb172554d8ef506e6c0d33a");
	}

	@Test
	public void testAdd() {
		for (int i = 0; i < 1003; i++) {
			Product p = new Product();
			p.setId("1000" + i);
			p.setName("orange" + i);
			p.setPrice(99.99);
			p.setStatus(1);
			dao.add(p);
		}
	}

	@Test
	public void testGetAllIntPageIntLimit() {
		int page = 101;
		List<Product> list = null;
		list = dao.getAll(page,10);
		

		System.out.println("Number of list:"+list.size());
		for (Product p : list) {
			System.out.println(p.getId());
		}
	}
}
