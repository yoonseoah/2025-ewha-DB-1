package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	public static Connection getConnection() throws SQLException {
		String userID = "testuser";
		String userPW = "testpw";
		String dbName = "photodb";
		String header = "jdbc:mysql://localhost:3306/";
		String encoding = "useUnicode=true&characterEncoding=UTF-8";
		String url = header + dbName + "?" + encoding;
		
		return DriverManager.getConnection(url, userID, userPW);
	}
	
}
