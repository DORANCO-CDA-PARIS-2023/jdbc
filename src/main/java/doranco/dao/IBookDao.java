package doranco.dao;

import java.sql.SQLException;
import java.util.List;

import doranco.entity.Book;

public interface IBookDao extends ICrud<Book> {
    public List<Book> searchByTitle(String title) throws SQLException;
}