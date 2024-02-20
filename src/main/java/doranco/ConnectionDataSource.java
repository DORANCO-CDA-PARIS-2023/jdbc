package doranco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public final class  ConnectionDataSource {

	private static  ConnectionDataSource instance;
	
	private ConnectionDataSource() {
	}
	public static ConnectionDataSource getInstance() {
		if (instance == null) {
			instance = new ConnectionDataSource();
		}
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		
		ResourceBundle rb = ResourceBundle.getBundle("doranco.dataBaseFile");
		String user = rb.getString("user");
		String password = rb.getString("password");
		String url = rb.getString("url");
		
		Connection connection = DriverManager.getConnection(url, user, password);
		return connection;
	}
}
