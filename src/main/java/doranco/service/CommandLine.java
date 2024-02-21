package doranco.service;

import doranco.dao.BookDaoImpl;
import doranco.database.Database;
import doranco.entity.Book;
import doranco.exception.NotFoundEntityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandLine {

    private Scanner sc;
    private BookDaoImpl bookDao;
    
	private final Connection connection;
	private final Statement statement;


    public CommandLine() throws SQLException {
        sc = new Scanner(System.in);
        bookDao = new BookDaoImpl();
        connection = Database.getINSTANCE().getConnection();
		statement = connection.createStatement();
    }

    public void start() throws SQLException, NotFoundEntityException {
        int cmd = 0;
        do {
            printOption();
            cmd = sc.nextInt();

            switch (cmd) {
                case 1 -> displayBooks();
                case 2 -> {
                    System.out.print("Enter title : ");
                    String title = sc.next();
                	searchByTitle(title);
                }
                case 3 -> {
                    System.out.print("Enter title : ");
                    String title = sc.next();
                    System.out.print("Enter year : ");
                    int year = sc.nextInt();
                    System.out.print("Enter author ID : ");
                    int authorId = sc.nextInt();
                    
                    createBook(new Book(title, year, authorId));
                }
                case 4 -> {
                    System.out.print("Enter ID : ");
                    int id = sc.nextInt();
                    deleteBook(id);
                }
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
    
    public List<Book> searchByTitle(String title) throws SQLException {
        String query = "SELECT * FROM book WHERE title LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "%" + title + "%");
        ResultSet result = statement.executeQuery();

        List<Book> books = new ArrayList<>();
        while (result.next())
        {
            books.add(new Book(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getInt("year_publish"),
                    result.getInt("id_author")
            ));
        }
        for (Book book: books)
        {
            System.out.print("ID : " + book.getId());
            System.out.print("\tTitle : " + book.getTitle());
            System.out.print("\tYear : " + book.getYear());
            System.out.println("\tAuthor ID : " + book.getAuthorId());
            System.out.println("----------");
        }
        return books.isEmpty() ? null : books;
    }
    
    
	public void createBook(Book book) throws SQLException, NotFoundEntityException {
		String query = """
				    INSERT INTO book (title, year_publish, id_author)
				    VALUE (?, ?, ?)
				""";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, book.getTitle());
		statement.setInt(2, book.getYear());
		statement.setInt(3, book.getAuthorId());
		try {
			statement.execute();
		} catch (SQLException e) {
			throw new NotFoundEntityException("Author ID : " + book.getAuthorId() + " doesn't exist");
		}
	}
	
	
	public void deleteBook(int id) throws SQLException {
        String query = "DELETE FROM book WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.execute();
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
