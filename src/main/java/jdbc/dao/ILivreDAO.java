package jdbc.dao;

import java.sql.SQLException;

import jdbc.entity.Livre;

public interface ILivreDAO extends ICrud {
	public Livre findByTitle(String title);
}
