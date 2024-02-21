package doranco.service;

import doranco.dao.BookDAO;
import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

    private Scanner sc;
    private BookDAO bookDao;


    public CommandLine() throws Exception {
        sc = new Scanner(System.in);
        bookDao = new BookDAO();
    }

    public void start() throws SQLException {
        int cmd = 0;
        do {
            printOption();
            cmd = sc.nextInt();
            if (cmd == 1) {
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
            System.out.print("\tYear : " + book.getYearPublish());
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