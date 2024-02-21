package doranco;

import java.sql.*;
import java.util.List;

import doranco.dao.BookDAO;
import doranco.entity.Book;

public class App 
{
    public static void main( String[] args ) throws Exception {
        BookDAO bookDao = new BookDAO();
        List<Book> books = bookDao.findAll();

        for (Book book: books)
        {
            System.out.println(book);
        }

    }
}
