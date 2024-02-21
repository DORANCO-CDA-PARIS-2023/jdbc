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

        while (result.next()) {
            books.add(new Book(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getInt("year_publish"),
                    result.getInt("id_author")));
        }
        return books.isEmpty() ? null : books;
    }

    @Override
    public void create(Book book) {
        try {
            String query = "INSERT INTO book (title, year_publish, id_author) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getYear());
            preparedStatement.setInt(3, book.getAuthorId());
            preparedStatement.executeUpdate();
            System.out.println("Ajout fait");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM book WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Supression faites");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try {
            String query = "UPDATE book SET title = ?, year_publish = ?, id_author = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getYear());
            preparedStatement.setInt(3, book.getAuthorId());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.executeUpdate();
            System.out.println("Mise  jour faites");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
