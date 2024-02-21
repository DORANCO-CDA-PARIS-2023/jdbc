package doranco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/doranco_merise";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    @Override
    public Book getBookById(int id) {
        // Implementation of getting a book by ID from the database
    }

    @Override
    public List<Book> getAllBooks() {
        // Implementation of getting all books from the database
    }

    @Override
    public void addBook(Book book) {
        // Implementation of adding a book to the database
    }

    @Override
    public void updateBook(Book book) {
        // Implementation of updating a book in the database
    }

    @Override
    public void deleteBook(int id) {
        // Implementation of deleting a book from the database
    }

    // Helper method to establish database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
