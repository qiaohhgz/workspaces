package com.mkyong.config;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

public class TestSpringMongoConfig {

	@Test
	public void testInitApplicationContext() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("a*.xml");
		MongoOperations bean = ctx.getBean("mongoTemplate",
				MongoOperations.class);
		Assert.assertNotNull(bean);
	}
}
