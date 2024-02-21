package doranco.dao;

import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface ICrud <T> {

    public T find(int id) throws SQLException, NotFoundEntityException;

    public List<T> findAll() throws SQLException;

    public void create(T entity) throws SQLException;

    public void delete(int id) throws SQLException;
    public void update(String title,int id) throws SQLException;
}
