package doranco.model.dao;

import doranco.entity.Book;
import java.util.List;

public interface IBookDao {
    int addBook(Book book) throws Exception;
    List<Book> getAllBooks() throws Exception;
    Book getBookById(int id) throws Exception;
    void updateBook(Book book) throws Exception;
    void removeBook(int id) throws Exception;
}