package doranco;

import java.util.List;

public interface BookDAO {
    Book getBookById(int id);

    List<Book> getAllBooks();

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int id);
}
