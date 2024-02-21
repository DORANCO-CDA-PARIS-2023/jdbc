package doranco.model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSourceSingleton {
	
	private static DataSourceSingleton instance;
	
	private DataSourceSingleton() {}
	
	public static synchronized DataSourceSingleton getInstance() {
		if(instance == null) {
			instance = new DataSourceSingleton();
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
