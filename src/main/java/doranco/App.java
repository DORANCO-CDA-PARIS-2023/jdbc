package doranco;

<<<<<<< HEAD
import doranco.exception.NotFoundEntityException;
import doranco.service.CommandLine;
=======
import java.sql.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import doranco.entity.Book;
import doranco.model.dao.BookDao;
>>>>>>> 68461191a51cc6aa355da581e0c64ddb70d18aa3

import java.sql.SQLException;

public class App
{
<<<<<<< HEAD
    public static void main( String[] args ) throws NotFoundEntityException {
        try {
            CommandLine commandLine = new CommandLine();
            commandLine.start();
        } catch (SQLException e) {
            System.err.println("Error database connection");
=======
    public static void main( String[] args ) {
 try (Scanner scanner = new Scanner(System.in)) {
            BookDao bookDao = new BookDao();

            int choice = 0;

            while (choice != 4) {
                System.out.println("Menu:");
                System.out.println("1. Ajouter un livre");
                System.out.println("2. Supprimer un livre");
                System.out.println("3. Voir tous les livres");
                System.out.println("4. Quitter");
                System.out.print("Votre choix : ");

                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addBook(bookDao, scanner);
                        break;
                    case 2:
                        removeBook(bookDao, scanner);
                        break;
                    case 3:
                        displayAllBooks(bookDao);
                        break;
                    case 4:
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Choix invalide, veuillez réessayer.");
                }
            }
        }
    }

    private static void addBook(BookDao bookDao, Scanner scanner) {
        System.out.print("Titre du livre : ");
        String title = scanner.nextLine();

        System.out.print("Année de publication : ");
        int yearPublish = scanner.nextInt();
        scanner.nextLine();

        System.out.print("ID de l'auteur : ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        Book newBook = new Book(title, yearPublish, authorId);

        try {
            bookDao.addBook(newBook);
            System.out.println("Le livre a été ajouté avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du livre : " + e.getMessage());
        }
    }

    private static void removeBook(BookDao bookDao, Scanner scanner) {
        System.out.print("ID du livre à supprimer : ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        try {
            bookDao.removeBook(bookId);
            System.out.println("Le livre a été supprimé avec succès !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la suppression du livre : " + e.getMessage());
        }
    }

    private static void displayAllBooks(BookDao bookDao) {
        try {
            List<Book> allBooks = bookDao.getAllBooks();
            System.out.println("Liste des livres :");
            for (Book book : allBooks) {
                System.out.println(book);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des livres : " + e.getMessage());
>>>>>>> 68461191a51cc6aa355da581e0c64ddb70d18aa3
        }
    }
}
