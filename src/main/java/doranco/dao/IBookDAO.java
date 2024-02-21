package doranco.dao;

import java.sql.SQLException;

import doranco.entity.Book;

public interface IBookDAO  extends ICrud<Book>{
    void updateBook(Book book) throws SQLException;
}