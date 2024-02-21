package doranco.dao;

import doranco.exception.NotFoundEntityException;
import doranco.database.Database;
import doranco.entity.Book;

import java.sql.*;
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
    public Book find(int id) throws SQLException, NotFoundEntityException {
        String query = "SELECT * FROM book WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result =  statement.executeQuery();
        if (result.next()) {
            return new Book(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getInt("year_publish"),
                    result.getInt("id_author")
            );
        }
        throw new NotFoundEntityException("id : " + id + " doesn't exist");
    }

	@Override
	public List<Book> findAll() throws SQLException {
		String query = "SELECT * FROM book";
		ResultSet result = statement.executeQuery(query);
		List<Book> books = new ArrayList<>();

        while (result.next())
        {
            Book book = new Book();
            book.setId(result.getInt("id"));
            book.setTitle( result.getString("title"));
            book.setYear(result.getInt("year_publish"));
            book.setAuthorId(result.getInt("id_author"));

            books.add(book);
/*            books.add(new Book(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getInt("year_publish"),
                    result.getInt("id_author")
            ));*/
        }
        return books.isEmpty() ? null : books;
    }

    @Override
    public void create(Book book) throws SQLException, NotFoundEntityException {
        String query = """
                INSERT INTO book (title, year_publish, id_author) 
                VALUE (?, ?, ?)
            """;
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setString(1, book.getTitle());
        statement.setInt(2, book.getYear());
        statement.setInt(3, book.getAuthorId());
        try {
            statement.execute();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new NotFoundEntityException("Author ID : " + book.getAuthorId() + " doesn't exist");
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM book WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.execute();
    }

    @Override
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
        return books.isEmpty() ? null : books;
    }
}
