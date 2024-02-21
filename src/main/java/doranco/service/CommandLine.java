package doranco.service;

import doranco.dao.BookDaoImpl;
import doranco.entity.Book;

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

    public void start() throws SQLException {
        int cmd = 0;
        String title =null;
        do {
            printOption();
            cmd = sc.nextInt();
            if (cmd == 1) {
                displayBooks();
            }
            if(cmd==2){
                System.out.print("""
                   title : 
                   > """ );
                 title=sc.nextLine();
                searchbook(title);
            }2
        } while (cmd != 5);
        sc.close();
        System.out.println("Application closed");
    }

    private void searchbook(String title) throws SQLException {
        List<Book> book= bookDao.searchByTitle(title);
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
