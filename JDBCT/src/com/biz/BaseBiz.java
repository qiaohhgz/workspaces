package com.biz;

import java.sql.Connection;
import java.sql.SQLException;

import com.db.dao.BaseDao;
import com.db.util.ITransaction;
import com.db.util.JDBCHelper;

public abstract class BaseBiz {
	private Connection connection;

	public abstract BaseDao[] getDao();

	public Object excute(ITransaction tran) {
		Object result = null;
		try {
			connection = JDBCHelper.openConnection();
			beginTransaction(connection);
			connection.setAutoCommit(false);
			result = tran.execute();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void beginTransaction(Connection connection) {
		for (int i = 0; i < getDao().length; i++) {
			BaseDao baseDao = getDao()[i];
			baseDao.setConnection(connection);
		}
	}

	public Connection getConnection() {
		return connection;
	}
}
