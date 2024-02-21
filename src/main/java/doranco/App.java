package doranco;

import doranco.service.CommandLine;

import java.sql.SQLException;
<<<<<<< HEAD
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        BookDaoImpl bookDao = new BookDaoImpl();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 = Ajouter");
        System.out.println("2 = Supprimer");
        System.out.println("3 = Mise a jour");
        System.out.println("4 = Afficher");
        int choice = scanner.nextInt();

        switch (choice) {

            case 1:
                addBook(bookDao, scanner);
                break;
            case 2:
                deleteBook(bookDao, scanner);
                break;
            case 3:
                updateBook(bookDao, scanner);
                break;
            case 4:
                displayAllBooks(bookDao);
                break;
            default:
                System.out.println("Erreur");
        }

        scanner.close();
    }

    private static void displayAllBooks(BookDaoImpl bookDao) throws SQLException {
        List<Book> books = bookDao.findAll();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private static void addBook(BookDaoImpl bookDao, Scanner scanner) throws SQLException {
        System.out.print("Titre");
        String title = scanner.next();
        System.out.print("Date");
        int year = scanner.nextInt();
        System.out.print("ID Auteur");
        int authorId = scanner.nextInt();
        Book newBook = new Book(0, title, year, authorId);
        bookDao.create(newBook);
        System.out.println("Livre ajouté");
    }

    private static void deleteBook(BookDaoImpl bookDao, Scanner scanner) throws SQLException {
        System.out.print("ID");
        int idToDelete = scanner.nextInt();
        bookDao.delete(idToDelete);
        System.out.println("Livre supprimé");
    }

    private static void updateBook(BookDaoImpl bookDao, Scanner scanner) throws SQLException {
        System.out.print("ID");
        int idToUpdate = scanner.nextInt();
        System.out.print("New titre");
        String newTitle = scanner.next();
        System.out.print("New Date ");
        int newYear = scanner.nextInt();
        System.out.print("New ID Auteur");
        int newAuthorId = scanner.nextInt();
        Book updatedBook = new Book(idToUpdate, newTitle, newYear, newAuthorId);
        bookDao.update(updatedBook);
        System.out.println("Mise a jour faites");
=======

public class App
{
    public static void main( String[] args ) {
        try {
            CommandLine commandLine = new CommandLine();
            commandLine.start();
        } catch (SQLException e) {
            System.err.println("Error database connection");
        }
>>>>>>> 87818ab7e4b18554c8bb5c22dc7ad0c9f1ceec97
    }
}
