package doranco.dao;

import doranco.exception.NotFoundEntityException;
import doranco.database.Database;
import doranco.entity.Book;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
=======
import java.sql.*;
>>>>>>> 87818ab7e4b18554c8bb5c22dc7ad0c9f1ceec97
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

<<<<<<< HEAD
        while (result.next()) {
=======
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
>>>>>>> 87818ab7e4b18554c8bb5c22dc7ad0c9f1ceec97
            books.add(new Book(
                    result.getInt("id"),
                    result.getString("title"),
                    result.getInt("year_publish"),
                    result.getInt("id_author")));
        }
        return books.isEmpty() ? null : books;
    }
<<<<<<< HEAD

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
=======
>>>>>>> 87818ab7e4b18554c8bb5c22dc7ad0c9f1ceec97
}
