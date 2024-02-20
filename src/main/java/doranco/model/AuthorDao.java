package doranco.model;

import doranco.entity.Author;
import doranco.utils.Dates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class AuthorDao extends Dao implements IAuthorDao {

    @Override
    public int add(Author entity) throws Exception {
        Connection connection = null;
        String request = "INSERT INTO author (name, firstname, birthday) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getFirstname());
            ps.setDate(3, Dates.convertDateUtilToDateSql(entity.getBirthday()));
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
    public Set<Author> get() throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM author";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<Author> authors = new HashSet<>();

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Author author = new Author();
                    author.setId(rs.getInt("id"));
                    author.setName(rs.getString("name"));
                    author.setFirstname(rs.getString("firstname"));
                    author.setBirthday(Dates.convertDateSqlToDateUtil(rs.getDate("birthday")));
                    authors.add(author);
                }
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return authors;
    }

    @Override
    public void update(Author entity) throws Exception {
        Connection connection = null;
        String request = "UPDATE author SET name=?, firstname=?, birthday=? WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getFirstname());
            ps.setDate(3, Dates.convertDateUtilToDateSql(entity.getBirthday()));
            ps.setInt(4, entity.getId());
            ps.executeUpdate();

        } finally {
            closeDataFlow(connection, ps);
        }
    }

    @Override
    public void delete(Author entity) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM author WHERE id=?";
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
