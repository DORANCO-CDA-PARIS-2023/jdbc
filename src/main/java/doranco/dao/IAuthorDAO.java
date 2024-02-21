package doranco.dao;


import java.sql.SQLException;

import doranco.entity.Author;

public interface IAuthorDAO extends ICrud<Author>{
    void updateAuthor(Author author) throws SQLException;
}