package doranco.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {

	private static DataSource instance;
	
	private DataSource() {
		
	}
	
	public static synchronized DataSource getInstance() {
		if (instance == null) {
			instance = new DataSource();
		}
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		String user = "root";
		String password = "rootroot";
		String url = "jdbc:mysql://localhost:3306/doranco_merise";
		
		String driver = "com.mysql.cj.jdbc.Driver";
		Class.forName(driver);
		
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
