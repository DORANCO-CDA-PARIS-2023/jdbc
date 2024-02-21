package doranco.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import doranco.entity.Author;
import doranco.singleton.DataSourceSingleton;

public class AuthorDAO implements IAuthorDAO {

    @Override
    public Author find(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Author author = null;

        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            String query = "SELECT * FROM author WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setFirstname(rs.getString("firstname"));
                author.setBirthday(rs.getDate("birthday"));
            }

        }  catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return author;
    }

    @Override
    public List<Author> findAll() throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Author> authors = new ArrayList<>();

        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            String query = "SELECT * FROM author";
            ps = connection.prepareStatement(query);

            rs = ps.executeQuery();

            while (rs.next()) {
                Author author = new Author();
                author.setId(rs.getInt("id"));
                author.setName(rs.getString("name"));
                author.setFirstname(rs.getString("firstname"));
                author.setBirthday(rs.getDate("birthday"));
                authors.add(author);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return authors;
    }

    @Override
    public void delete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            connection.setAutoCommit(false);

            String query = "DELETE FROM author WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ps.executeUpdate();

            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateAuthor(Author author) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            String query = "UPDATE author SET name = ?, firstname = ?, birthday = ? WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, author.getName());
            ps.setString(2, author.getFirstname());
            ps.setDate(3, new java.sql.Date(author.getBirthday().getTime()));
            ps.setInt(4, author.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void create(Author author) {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            String query = "INSERT INTO author (name, firstname, birthday) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, author.getName());
            ps.setString(2, author.getFirstname());
            ps.setDate(3, new java.sql.Date(author.getBirthday().getTime()));

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}