package doranco.dao;

import doranco.database.Database;

import doranco.entity.Book;


import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public void create(Book book) throws Exception {
    	if (book == null) {
			throw new NullPointerException("Le livre à ajouter ne doit pos être NULL !");
		}
		
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer id = null;
		try {
			connection = Database.getINSTANCE().getConnection();
			String requete = "INSERT INTO book(title, year, authorId)"
					+ " VALUES(?, ?, ?)";
			ps = connection.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, book.getTitle());
			ps.setInt(2, book.getYear());
			ps.setInt(3, book.getAuthorId());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs != null && rs.next()) {
				id = rs.getInt(1);
			}
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}
		return;
	}

    

    @Override
    public void delete(int id) throws Exception {
    
    Connection connection = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
			
    	try {
    		
    		connection = Database.getINSTANCE().getConnection();
			connection.setAutoCommit(false);
			
			
    		String requete = "DELETE FROM BOOK WHERE id = ?";
			ps = connection.prepareStatement(requete);
			ps.setInt(1, id);
			ps.executeUpdate();

			connection.commit();

		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		} finally {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		}

	}
}

