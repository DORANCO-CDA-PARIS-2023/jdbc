package doranco.dao;

import doranco.database.Database;
import doranco.entity.Author;
import doranco.entity.Borrow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl implements IBorrowDao{
    private final Connection connection;
    private final Statement statement;

    public BorrowDaoImpl() throws SQLException {
        connection = Database.getINSTANCE().getConnection();
        statement = connection.createStatement();
    }
    @Override
    public Borrow find(int id) {
        return null;
    }

    @Override
    public List<Borrow> findAll() throws SQLException {
        String query = "SELECT * FROM borrow";
        ResultSet result = statement.executeQuery(query);
        List<Borrow> borrows = new ArrayList<>();

        while (result.next())
        {
            borrows.add(new Borrow(
                    result.getInt("id"),
                    result.getDate("date_borrow"),
                    result.getDate("date_back_schedulde"),
                    result.getDate("date_back"),
                    result.getInt("id_book"),
                    result.getInt("id_student")
            ));
        }
        return borrows.isEmpty() ? null : borrows;
    }

    @Override
    public void create(Borrow entity) {

    }

    @Override
    public void delete(int id) {

    }
}
