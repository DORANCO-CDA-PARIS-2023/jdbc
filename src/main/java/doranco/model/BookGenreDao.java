package doranco.model;

import doranco.entity.BookGenre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class BookGenreDao extends Dao implements IBookGenreDao {

    @Override
    public int add(BookGenre entity) throws Exception {
        Connection connection = null;
        String request = "INSERT INTO book_genre (id_book, id_genre) VALUES (?, ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, entity.getIdBook());
            ps.setInt(2, entity.getIdGenre());
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
    public Set<BookGenre> get() throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM book_genre";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<BookGenre> bookGenres = new HashSet<>();

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    BookGenre bookGenre = new BookGenre();
                    bookGenre.setIdBook(rs.getInt("id_book"));
                    bookGenre.setIdGenre(rs.getInt("id_genre"));
                    bookGenres.add(bookGenre);
                }
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return bookGenres;
    }

    @Override
    public void update(BookGenre entity) throws Exception {
        Connection connection = null;
        String request = "UPDATE book_genre SET id_book=?, id_genre=? WHERE id_book=? AND id_genre=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setInt(1, entity.getIdBook());
            ps.setInt(2, entity.getIdGenre());
            ps.setInt(3, entity.getIdBook());
            ps.setInt(4, entity.getIdGenre());
            ps.executeUpdate();

        } finally {
            closeDataFlow(connection, ps);
        }
    }

    @Override
    public void delete(BookGenre entity) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM book_genre WHERE id_book=? AND id_genre=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setInt(1, entity.getIdBook());
            ps.setInt(1, entity.getIdGenre());
            ps.executeUpdate();
        } finally {
            closeDataFlow(connection, ps);
        }
    }

}
