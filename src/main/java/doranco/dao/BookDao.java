package doranco.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import doranco.entity.Book;
import doranco.model.DataSource;

public class BookDao implements IBookDao{
	
	private final Connection connection;
	private final Statement statement;
	
	public BookDao() throws Exception{
		connection = DataSource.getInstance().getConnection();
		statement = connection.createStatement();
	}

	@Override
	public Book find(int id) {
		String query = "SELECT * FROM book WHERE id = " + id;
    	try {
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				return new Book(result.getInt("id"), result.getString("title"), result.getInt("year_publish"), result.getInt("id_author"));
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
	public void create(Book entity) {

	    try {
	    	String query = "INSERT INTO book (title, year_publish, id_author) VALUES (?, ?, ?)";
	        PreparedStatement ps = connection.prepareStatement(query);
	        ps.setString(1, entity.getTitle());
	        ps.setInt(2, entity.getYear_publish());
	        ps.setInt(3, entity.getAuthorId());
	        ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void delete(int id) {
		 String query = "DELETE FROM book WHERE id = " + id;
		    try {
		        statement.executeUpdate(query);
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}

}
