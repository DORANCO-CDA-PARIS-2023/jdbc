package jdbc.dao;

import java.sql.SQLException;

public interface ICrud {
	void findAll() throws Exception;
	void getById() throws Exception;
	void add() throws SQLException, Exception;
	void remove() throws Exception;
	void closeConnection() throws SQLException;
}
