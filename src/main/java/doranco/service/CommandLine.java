 package doranco.service;

import doranco.dao.BookDao;
import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

    private Scanner sc;
    private BookDao bookDao;


    public CommandLine() throws Exception {
        sc = new Scanner(System.in);
        bookDao = new BookDao();
    }

    public void start() throws SQLException {
        int cmd = 0;
        do {
            printOption();
            cmd = sc.nextInt();
            switch (cmd) {
            case 1:
                displayBooks();
                break;
            case 2:
                searchByTittle();
                break;
            case 3:
                createBook();
                break;
            case 4:
            	deleteBook();
                break;
        }
      
        } while (cmd != 5);
        sc.close();
        System.out.println("Application closed");
    }

	private void displayBooks() throws SQLException {
        List<Book> books = bookDao.findAll();
        for (Book book: books)
        {
            System.out.print("ID : " + book.getId());
            System.out.print("\tTitle : " + book.getTitle());
            System.out.print("\tYear : " + book.getYear_publish());
            System.out.println("\tAuthor ID : " + book.getAuthorId());
            System.out.println("----------");
        }
    }
    
    private void searchByTittle() {

    }
    
    private void createBook() {	
	}
    
    private void deleteBook() {
	}

    private void printOption()
    {
        System.out.print("Option :");
        System.out.print("1 - Display books");
        System.out.print("2 - Search book (by title)");
        System.out.print("3 - Create book");
        System.out.print("4 - Delete book");
        System.out.print("5 - exit");
    }

}