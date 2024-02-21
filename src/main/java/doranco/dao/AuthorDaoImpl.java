package doranco.dao;

import doranco.database.Database;
import doranco.entity.Author;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements IAuthorDao{
    private final Connection connection;
    private final Statement statement;

    public AuthorDaoImpl() throws SQLException {
        connection = Database.getINSTANCE().getConnection();
        statement = connection.createStatement();
    }
    @Override
    public Author find(int id) {
        return null;
    }

    @Override
    public List<Author> findAll() throws SQLException {
        String query = "SELECT * FROM author";
        ResultSet result = statement.executeQuery(query);
        List<Author> authors = new ArrayList<>();

        while (result.next())
        {
            authors.add(new Author(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("firstname"),
                    result.getDate("birthday")
            ));
        }
        return authors.isEmpty() ? null : authors;
    }

    @Override
    public void create(Author entity) {

    }

    @Override
    public void delete(int id) {

    }
}
