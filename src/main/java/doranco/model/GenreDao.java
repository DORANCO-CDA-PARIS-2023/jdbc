package doranco.model;

import doranco.entity.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class GenreDao extends Dao implements IGenreDao {

    @Override
    public int add(Genre entity) throws Exception {
        Connection connection = null;
        String request = "INSERT INTO genre (name) VALUES (?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return id;
    }

    @Override
    public Set<Genre> get() throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM genre";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<Genre> genres = new HashSet<>();

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Genre genre = new Genre();
                    genre.setId(rs.getInt("id"));
                    genre.setName(rs.getString("name"));
                    genres.add(genre);
                }
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return genres;
    }

    @Override
    public void update(Genre entity) throws Exception {
        Connection connection = null;
        String request = "UPDATE genre SET name=? WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getId());
            ps.executeUpdate();

        } finally {
            closeDataFlow(connection, ps);
        }
    }

    @Override
    public void delete(Genre entity) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM genre WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setInt(1, entity.getId());
            ps.executeUpdate();
        } finally {
            closeDataFlow(connection, ps);
        }
    }

}
