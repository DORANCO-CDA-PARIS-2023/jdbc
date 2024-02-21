package doranco;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import doranco.dao.BookDao;
import doranco.entity.Book;


public class App 
{
    public static void main( String[] args ) throws Exception {
    	BookDao bookDao = new BookDao();
    	
    	//Book book1 = new Book("The Homecoming of Beorhtnoth Beorhthelm's Son",1953,2);
    	//bookDao.create(book1);
    	
    	List<Book> books = bookDao.findAll();

        for (Book book: books)
        {
        	System.out.println(book);
        }
        
        bookDao.delete(9);
    }
    
}
