package com.db.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCHelper {

    public static Connection openConnection() {
        try {
            return getConnection("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/test1", "root", "root");
        } catch (Exception e) {
            return null;
        }
    }

    public static Connection getConnection(String driverClass, String url, String userName, String password) throws Exception {
        Class.forName(driverClass);
        return DriverManager.getConnection(url, userName, password);
    }

    public static void testConnection(String driverClass, String url, String userName, String password) throws Exception {
        getConnection(driverClass, url, userName, password);
    }
}
