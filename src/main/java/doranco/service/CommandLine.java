package doranco.service;

import doranco.dao.BookDaoImpl;
import doranco.entity.Book;
import doranco.exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

    private Scanner sc;
    private BookDaoImpl bookDao;


    public CommandLine() throws SQLException {
        sc = new Scanner(System.in);
        bookDao = new BookDaoImpl();
    }

    public void start() throws SQLException, NotFoundEntityException {
        int cmd = 0;
        String title;
        int year_publish;
        int id_author;
        int id;
        do {
            printOption();
            cmd = sc.nextInt();
            sc.nextLine();
            
            if (cmd == 1) {
                displayBooks();
            }
            if (cmd == 2) {
            	System.out.println("Veuillez saisir un titre");
            	title = sc.next();
                displayBookByTitle(title);
            }
            if (cmd == 3) {
                System.out.println("Veuillez saisir un titre");
                title = sc.nextLine();
                System.out.println("Veuillez saisir une année de publication");
                year_publish = Integer.parseInt(sc.nextLine());
                System.out.println("Veuillez saisir un ID d'auteur");
                id_author = Integer.parseInt(sc.nextLine());
                createBook(title, year_publish, id_author);
                displayBooks();
            }
            if (cmd == 4) {
            	System.out.println("Veuillez saisir l'id d'un livre");
            	id = sc.nextInt();
                deleteBook(id);
                displayBooks();
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
            System.out.print("\tYear : " + book.getYear());
            System.out.println("\tAuthor ID : " + book.getAuthorId());
            System.out.println("----------");
        }
    }
    
    private void displayBookByTitle(String title) throws SQLException {
        List<Book> books = bookDao.searchByTitle(title);
        for (Book book: books)
        {
            System.out.print("ID : " + book.getId());
            System.out.print("\tTitle : " + book.getTitle());
            System.out.print("\tYear : " + book.getYear());
            System.out.println("\tAuthor ID : " + book.getAuthorId());
            System.out.println("----------");
        }
    }
    
    private void createBook(String title, int year_publish, int id_author) throws SQLException, NotFoundEntityException {
    	Book book = new Book();
    	book.setTitle(title);
        book.setYear(year_publish); 
        book.setAuthorId(id_author); 
    	
         bookDao.create(book);
        
    }
    
    private void deleteBook(int id) throws SQLException {
    	
    	bookDao.delete(id);
    }

    private void printOption()
    {
        System.out.print(
                """
                   Option : 
                        1 - Display books
                        2 - Search book (by title)
                        3 - Create book
                        4 - Delete book
                        5 - exit 
                    > """
        );
    }

}
