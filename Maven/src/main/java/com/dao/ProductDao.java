package com.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.entity.Product;

public class ProductDao {

	public static final String collectionName = "product";
	private MongoOperations mongoOperations;

	public static ProductDao getInstance() {
		return new ClassPathXmlApplicationContext("a*.xml").getBean(
				"productDao", ProductDao.class);
	}

	public Product getById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		Product product = mongoOperations.findOne(query, Product.class);
		return product;
	}

	public List<Product> getAll() {
		List<Product> list = mongoOperations.findAll(Product.class);
		return list;
	}

	public List<Product> getAll(int page, int limit) {
		Query query = new Query().limit(limit).skip((page - 1) * limit);
		List<Product> list = mongoOperations.find(query, Product.class);
		return list;
	}

	public void add(Product product) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(new Date());
		product.setCreateDate(instance.getTime());
		mongoOperations.save(product, collectionName);
	}

	public void delete(Product product) {
		deleteById(product.getId());
	}

	public void deleteById(String id) {
		mongoOperations.remove(getById(id), collectionName);
	}

	public Product update(Product product) {
		if (product != null) {
			add(product);
		}
		product = getById(product.getId());
		return product;
	}

	public MongoOperations getMongoOperations() {
		return mongoOperations;
	}

	public void setMongoOperations(MongoOperations mongoOperations) {
		this.mongoOperations = mongoOperations;
	}
}
