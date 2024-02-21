package doranco.service;

import doranco.dao.BookDaoImpl;
import doranco.entity.Book;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

    private Scanner scanner;
    private BookDaoImpl bookDao;

    public CommandLine() throws SQLException {
        scanner = new Scanner(System.in);
        bookDao = new BookDaoImpl();
    }

    public void start() throws SQLException {
        int cmd = 0;
        do {
            printOption(); // print all theoptions
            cmd = scanner.nextInt();

            switch (cmd) {
                case 1:
                    displayBooks(); // Display all books
                    break;
                case 2:
                    searchBookByTitle(); // Search by title
                    break;
                case 3:
                    createBook(); // Create new book
                    break;
                case 4:
                    deleteBook(); // Delete a book
                    break;
                case 5:
                    System.out.println("Application closed.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Option non valide. Veuillez réessayer.");
                    break;
            }
        } while (true);
    }

    private void displayBooks() throws SQLException {
        List<Book> books = bookDao.findAll();
        for (Book book : books) {
            System.out.println("ID: " + book.getId() + "\tTitle: " + book.getTitle() + "\tYear: " + book.getYear()
                    + "\tAuthor ID: " + book.getAuthorId());
        }
    }

    private void printOption() {
        System.out.println(
                "Option:\n\t1 - Display books\n\t2 - Search book (by title)\n\t3 - Create book\n\t4 - Delete book\n\t5 - Exit\n> ");
    }

    private void searchBookByTitle() {
        System.out.println("Entrez le titre du livre à rechercher :");
        scanner.nextLine();
        String title = scanner.nextLine(); // We read the input

        try {
            // we use the method of bookdao
            List<Book> foundBooks = bookDao.searchByTitle(title);

            if (foundBooks == null || foundBooks.isEmpty()) {
                System.out.println("Aucun livre trouvé avec le titre : \"" + title + "\"");
            } else {
                System.out.println("Livres trouvés :");
                for (Book book : foundBooks) {
                    // we print the books
                    System.out.println("ID: " + book.getId() + ", Titre: " + book.getTitle()
                            + ", Année de publication: " + book.getYear() + ", ID Auteur: " + book.getAuthorId());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error with the search book");
        }
    }

    private void createBook() {
        System.out.println("Test 2");
    }

    private void deleteBook() {
        System.out.println("Test 3");
    }
}
