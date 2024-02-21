package doranco.dao;

import doranco.database.Database;
import doranco.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {

    private final Connection connection;
    private final Statement statement;

    public BookDaoImpl() throws SQLException {
        connection = Database.getINSTANCE().getConnection();
        statement = connection.createStatement();
    }

    @Override
    public Book find(int id) {
    	String query = "SELECT * FROM book WHERE id = " + id;
    	try {
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				return new Book(result.getInt("id"), result.getString("title"), result.getInt("year_publish"),
						result.getInt("id_author"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
    	}
        return null;
    }

    @Override
    public List<Book> findAll() throws SQLException {
        String query = "SELECT * FROM book";
        ResultSet result = statement.executeQuery(query);
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
        return books.isEmpty() ? null : books;
    }

    @Override
    public void create(Book book) {
        String query = "INSERT INTO book (title, year_publish, id_author) VALUES ('" + book.getTitle() + "', " + book.getYear() + ", " + book.getAuthorId() + ")";
        try {
            statement.executeUpdate(query);
            System.out.println("Book created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
    	String query = "DELETE FROM book WHERE id = " + id;
		try {
			statement.executeUpdate(query);
			System.out.println("Book deleted");
		} catch (SQLException e) {
			e.printStackTrace();
		}

    }
}
