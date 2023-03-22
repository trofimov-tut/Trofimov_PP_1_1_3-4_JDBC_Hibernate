package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test3";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Qasdxcrt27";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            Class.forName(DRIVER);
            System.out.println("We are connected");
            return connection;

    }



}

