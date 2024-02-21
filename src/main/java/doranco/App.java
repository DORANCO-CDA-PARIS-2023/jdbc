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
        Book newBook1=new Book(7,"Experiments with truth",1957,7);
        Book newBook2=new Book(8,"God of small things",1993 ,9);
        bookDao.create(newBook1);
        bookDao.create(newBook2);
        bookDao.delete(2);
        System.out.println(bookDao.find(3));

        bookDao.update("new title",3);


    }
}
