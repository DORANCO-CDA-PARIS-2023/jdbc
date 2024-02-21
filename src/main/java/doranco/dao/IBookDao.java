package doranco.dao;

import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDao extends ICrud<Book> {
    public List<Book> searchByTitle(String title) throws SQLException;
}
