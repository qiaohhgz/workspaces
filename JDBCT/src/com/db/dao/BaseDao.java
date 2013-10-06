package com.db.dao;

import java.sql.Connection;

public abstract class BaseDao {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
