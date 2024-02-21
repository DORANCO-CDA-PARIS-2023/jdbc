package doranco.dao;

import doranco.entity.Book;

<<<<<<< HEAD
public interface IBookDao extends ICrud<Book> {
    void update(Book book);
=======
import java.sql.SQLException;
import java.util.List;

public interface IBookDao extends ICrud<Book> {
    public List<Book> searchByTitle(String title) throws SQLException;
>>>>>>> 87818ab7e4b18554c8bb5c22dc7ad0c9f1ceec97
}
