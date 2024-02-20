package doranco;

import doranco.dao.BookDaoImpl;
import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws SQLException {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> books = bookDao.findAll();

        for (Book book: books)
        {
            System.out.println(book);
        }

    }
}
