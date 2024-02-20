package doranco.model;

import doranco.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class BookDao extends Dao implements ICrud<Book> {

    @Override
    public int add(Book entity) throws Exception {
        Connection connection = null;
        String request = "INSERT INTO book (title, year_publish, id_author) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getTitle());
            ps.setInt(2, entity.getYearPublish());
            ps.setInt(3, entity.getIdAuthor());
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
    public Set<Book> get() throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM book";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<Book> books = new HashSet<>();

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getInt("id"));
                    book.setTitle(rs.getString("title"));
                    book.setYearPublish(rs.getInt("year_publish"));
                    book.setIdAuthor(rs.getInt("id_author"));
                    books.add(book);
                }
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return books;
    }

    @Override
    public void update(Book entity) throws Exception {
        Connection connection = null;
        String request = "UPDATE book SET title=?, year_publish=?, id_author=? WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setString(1, entity.getTitle());
            ps.setInt(2, entity.getYearPublish());
            ps.setInt(3, entity.getIdAuthor());
            ps.setInt(4, entity.getId());
            ps.executeUpdate();

        } finally {
            closeDataFlow(connection, ps);
        }
    }

    @Override
    public void delete(Book entity) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM book WHERE id=?";
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
