package doranco.dao;

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
    public Book find(int id) {
        return new Book();
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
    public void create(Book book1) throws SQLException {
        String query = "INSERT INTO book (id, title, year_publish, id_author) VALUES(id,title,year_publish,id_author)";
        Statement statement = null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = null;
        preparedStatement.execute(query);



    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM book WHERE id = id";
        Statement statement = null;
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = null;
        preparedStatement.execute(query);

            System.out.println("the book is deleted");
            findAll();



    }

    @Override
    public void update(String title, int id) throws SQLException {
        String query ="UPDATE book SET title = ? WHERE id = ?";
        statement.executeQuery(query);

        findAll();

    }
}
