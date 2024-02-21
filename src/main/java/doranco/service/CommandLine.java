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
        do {
            printOption();
            cmd = sc.nextInt();
            if (cmd == 1) {
                searchBook();
            }
            if (cmd == 2) {
                displayBooks();
            }
            if (cmd == 3) {
                displayBooks();
            }
            if (cmd == 4) {
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

    private void searchBook() throws SQLException, NotFoundEntityException {
        System.out.println("Sélectionnez l'identifiant du livre");
        int id = sc.nextInt();
        Book book = bookDao.find(id);
        System.out.println(book);
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
