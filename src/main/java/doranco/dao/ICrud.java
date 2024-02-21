package doranco.dao;

import doranco.exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;

public interface ICrud <T> {

    public T find(int id) throws SQLException, NotFoundEntityException;

    public List<T> findAll() throws SQLException;

    public void create(T entity) throws SQLException, NotFoundEntityException;

    public void delete(int id) throws SQLException;
}
