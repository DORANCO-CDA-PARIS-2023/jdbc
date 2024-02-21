package doranco.dao;

import doranco.entity.Author;

import java.sql.SQLException;
import java.util.List;

public class AuthorDaoImpl implements IAuthorDao{
    @Override
    public Author find(int id) {
        return null;
    }

    @Override
    public List<Author> findAll() throws SQLException {
        return null;
    }

    @Override
    public void create(Author entity) throws SQLException {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(String title, int id) throws SQLException {

    }
}
