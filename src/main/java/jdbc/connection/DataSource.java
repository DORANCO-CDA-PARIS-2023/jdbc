package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public final class DataSource {

	private static DataSource instance;
	
	private DataSource() {
	}
	
	public static DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}
	
	public static Connection getConnection() throws Exception {
		
		String user = "root";
		String password = "root";
		String url = "jdbc:mysql://127.0.0.1:3306/doranco_merise";
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
