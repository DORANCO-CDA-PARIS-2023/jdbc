package doranco.dao;

import java.sql.SQLException;
import java.util.List;

public interface ICrud <T> {

    public T find(int id);

    public List<T> findAll() throws SQLException;

    public void create(T entity);

    public void delete(int id);
}
