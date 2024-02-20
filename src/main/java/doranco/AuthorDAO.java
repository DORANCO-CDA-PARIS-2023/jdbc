package doranco;

import java.util.List;

public interface AuthorDAO {
    Author getAuthorById(int id);

    List<Author> getAllAuthors();

    void addAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(int id);
}
