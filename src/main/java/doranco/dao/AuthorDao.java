package doranco.dao;

import java.sql.SQLException;
import java.util.List;

import doranco.entity.Author;
import doranco.exception.NotFoundEntityException;

public class AuthorDao implements IAuthorDao{

	@Override
	public Author find(int id) throws SQLException, NotFoundEntityException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Author entity) throws SQLException, NotFoundEntityException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
