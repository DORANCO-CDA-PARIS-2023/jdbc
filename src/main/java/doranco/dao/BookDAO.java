package doranco.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import doranco.singleton.*;
import doranco.entity.Book;

public class BookDAO implements IBookDAO{

    private final Connection connection;
    private final Statement statement;

    public BookDAO() throws Exception {
        connection = DataSourceSingleton.getInstance().getConnection();
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
    public void create(Book entity) throws SQLException {
        String query = "INSERT INTO book (title, year_publish, id_author) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getTitle());
            ps.setInt(2, entity.getYearPublish());
            ps.setInt(3, entity.getIdAuthor());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM book WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    @Override
    public void updateBook(Book book) throws SQLException{
        String query = "UPDATE book SET title = ?, year_publish = ?, id_author = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getTitle());
            ps.setInt(2, book.getYearPublish());
            ps.setInt(3, book.getIdAuthor());
            ps.setInt(4, book.getId());

            ps.executeUpdate();
        }
    }

}