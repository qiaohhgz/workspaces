package com.db.util;

import java.sql.SQLException;

public interface ITransaction {
	public abstract Object execute() throws SQLException;
}
