package jdbc.dao;

import java.sql.SQLException;

import jdbc.entity.Livre;

public interface ILivreDAO {
	void getLivres() throws Exception;
	void getLivre(int id) throws Exception;
	void addLivre() throws SQLException, Exception;
	void removeLivre(int id) throws Exception;
}
