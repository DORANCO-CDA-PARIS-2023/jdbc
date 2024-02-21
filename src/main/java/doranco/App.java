package doranco;

import doranco.dao.BookDaoImpl;
import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws SQLException {
        BookDaoImpl bookDao = new BookDaoImpl();
        
        Book book_1 = new Book("The Lord of the Rings", 1954, 1);
        Book book_2 = new Book("The Hobbit", 1937, 1);
        Book book_3 = new Book("The Silmarillion", 1977, 1);
        
        bookDao.create(book_1);
        bookDao.create(book_2);
        bookDao.create(book_3);
        
        
        List<Book> books = bookDao.findAll();

        for (Book book: books)
        {
            System.out.println(book);
        }
        
        bookDao.delete(7);
        
        List<Book> booksAfterDelete = bookDao.findAll();

        for (Book book: booksAfterDelete)
        {
            System.out.println(book);
        }

    }
}
