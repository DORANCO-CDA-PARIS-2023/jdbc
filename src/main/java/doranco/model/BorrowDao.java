package doranco.model;

import doranco.entity.Book;
import doranco.entity.Borrow;
import doranco.utils.Dates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class BorrowDao extends Dao implements IBorrowDao {

    @Override
    public int add(Borrow entity) throws Exception {
        Connection connection = null;
        String request = "INSERT INTO borrow (Date_borrow, Date_back_schedulde, Date_back, id_book, id_student) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Dates.convertDateUtilToDateSql(entity.getDateBorrow()));
            ps.setDate(2, Dates.convertDateUtilToDateSql(entity.getDateBackSchedulde()));
            ps.setDate(3, Dates.convertDateUtilToDateSql(entity.getDateBack()));
            ps.setInt(4, entity.getIdBook());
            ps.setInt(5, entity.getIdStudent());
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
    public Set<Borrow> get() throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM borrow";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<Borrow> borrows = new HashSet<>();

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Borrow borrow = new Borrow();
                    borrow.setId(rs.getInt("id"));
                    borrow.setDateBorrow(Dates.convertDateSqlToDateUtil(rs.getDate("Date_borrow")));
                    borrow.setDateBackSchedulde(Dates.convertDateSqlToDateUtil(rs.getDate("Date_back_schedulde")));
                    borrow.setDateBack(Dates.convertDateSqlToDateUtil(rs.getDate("Date_back")));
                    borrow.setIdBook(rs.getInt("id_book"));
                    borrow.setIdStudent(rs.getInt("id_student"));
                    borrows.add(borrow);
                }
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return borrows;
    }

    @Override
    public void update(Borrow entity) throws Exception {
        Connection connection = null;
        String request = "UPDATE borrow SET Date_borrow=?, Date_back_schedulde=?, Date_back=?, id_book=?, id_student=? WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setDate(1, Dates.convertDateUtilToDateSql(entity.getDateBorrow()));
            ps.setDate(2, Dates.convertDateUtilToDateSql(entity.getDateBackSchedulde()));
            ps.setDate(3, Dates.convertDateUtilToDateSql(entity.getDateBack()));
            ps.setInt(4, entity.getIdBook());
            ps.setInt(5, entity.getIdStudent());
            ps.setInt(6, entity.getId());
            ps.executeUpdate();

        } finally {
            closeDataFlow(connection, ps);
        }
    }

    @Override
    public void delete(Borrow entity) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM borrow WHERE id=?";
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
