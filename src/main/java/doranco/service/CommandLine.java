package doranco.service;

import doranco.dao.BookDaoImpl;
import doranco.entity.Book;
import doranco.exception.NotFoundEntityException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

	 private final Scanner sc;
	    private final BookDaoImpl bookDao;

	    public CommandLine() throws SQLException {
	        sc = new Scanner(System.in);
	        bookDao = new BookDaoImpl();
	    }

	    public void start() throws NotFoundEntityException {
	        int cmd;
	        do {
	            printOption();
	            cmd = sc.nextInt();
	            switch (cmd) {
	                case 1:
	                    displayBooks();
	                    break;
	                case 2:
	                    searchBookByTitle();
	                    break;
	                case 3:
	                    addBook();
	                    break;
	                case 4:
	                    deleteBook();
	                    break;
	                case 5:
	                    break;
	                default:
	                    System.out.println("Choix invalide !");
	            }
	        } while (cmd != 5);
	        sc.close();
	        System.out.println("Application fermée.");
	    }

	    private void displayBooks() {
	        try {
	            List<Book> books = bookDao.findAll();
	            if (books != null) {
	                for (Book book : books) {
	                    System.out.println("ID : " + book.getId());
	                    System.out.println("Title : " + book.getTitle());
	                    System.out.println("Year : " + book.getYear());
	                    System.out.println("Author ID : " + book.getAuthorId());
	                    System.out.println("----------");
	                }
	            } else {
	                System.out.println("Aucun livre trouvé.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void searchBookByTitle() {
	        System.out.print("Entrez le titre du livre à rechercher : ");
	        sc.nextLine(); // Pour consommer le saut de ligne
	        String title = sc.nextLine();
	        try {
	            List<Book> books = bookDao.searchByTitle(title);
	            if (books != null) {
	                for (Book book : books) {
	                    System.out.println("ID : " + book.getId());
	                    System.out.println("Title : " + book.getTitle());
	                    System.out.println("Year : " + book.getYear());
	                    System.out.println("Author ID : " + book.getAuthorId());
	                    System.out.println("----------");
	                }
	            } else {
	                System.out.println("Aucun livre trouvé avec le titre : " + title);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void addBook() throws NotFoundEntityException {
	        System.out.print("Entrez le titre du livre : ");
	        sc.nextLine(); // Pour consommer le saut de ligne
	        String title = sc.nextLine();
	        System.out.print("Entrez l'année de publication : ");
	        int year = sc.nextInt();
	        System.out.print("Entrez l'ID de l'auteur : ");
	        int authorId = sc.nextInt();
	        Book book = new Book(title, year, authorId);
	        try {
	            bookDao.create(book);
	            System.out.println("Livre ajouté avec succès !");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    private void deleteBook() {
	        System.out.print("Entrez l'ID du livre à supprimer : ");
	        int id = sc.nextInt();
	        try {
	            bookDao.delete(id);
	            System.out.println("Livre supprimé avec succès !");
	        } catch (SQLException e) {
	            e.printStackTrace();
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
