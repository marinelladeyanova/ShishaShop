package com.example.model.database_connection;

import com.example.model.exeptions.DatabaseExepton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static final String JDBC_MYSQL = "jdbc:mysql://";
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = ":3306";
    private static final String DB_SCHEMA = "/shisha";
    private static final String SSL = "?useSSL=false";

    private static final String DB_USER = "root";
    private static final String DB_PASS = "poni4ki";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";


    private static DataBaseConnection instance;
    private Connection connection;

    private DataBaseConnection() throws DatabaseExepton {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    String.format("%s%s%s%s%s", JDBC_MYSQL, DB_HOST, DB_PORT, DB_SCHEMA, SSL)
                    , DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new DatabaseExepton("Database Driver missing", e);
        } catch (SQLException e) {
            throw new DatabaseExepton("Database missing", e);
        }
    }

    public static DataBaseConnection getInstance() throws DatabaseExepton {
        if (instance == null) {
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }







}
