package doranco.dao;

import doranco.database.Database;
import doranco.entity.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl implements IGenreDao{
    private final Connection connection;
    private final Statement statement;

    public GenreDaoImpl() throws SQLException{
        connection = Database.getINSTANCE().getConnection();
        statement = connection.createStatement();
    }

    @Override
    public Genre find(int id) {
        return null;
    }

    @Override
    public List<Genre> findAll() throws SQLException {
        String query = "SELECT * FROM genre";
        ResultSet result = statement.executeQuery(query);
        List<Genre> genres = new ArrayList<>();

        while (result.next())
        {
            genres.add(new Genre(
                    result.getInt("id"),
                    result.getString("name")
            ));
        }
        return genres.isEmpty() ? null : genres;
    }

    @Override
    public void create(Genre entity) {

    }

    @Override
    public void delete(int id) {

    }
}
