package com;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.biz.StudentBiz;
import com.db.dao.StudentDao;

@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public abstract class AbstractSpringTest extends AbstractTransactionalJUnit4SpringContextTests {
	protected StudentDao studentDao;
	protected StudentBiz studentBiz;

	public StudentDao getStudentDao() {
		return studentDao;
	}

	@Resource(name = "studentDao")
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public StudentBiz getStudentBiz() {
		return studentBiz;
	}

	@Resource(name = "studentBiz")
	public void setStudentBiz(StudentBiz studentBiz) {
		this.studentBiz = studentBiz;
	}
	
	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

}
