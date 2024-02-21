package jdbc.dao;

import java.sql.SQLException;

import jdbc.entity.Livre;

public interface ILivreDAO extends ICrud {
	public void findByTitle() throws SQLException, Exception;
}
