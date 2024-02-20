package doranco;

import java.util.List;

public interface GenreDAO {
    Genre getGenreById(int id);

    List<Genre> getAllGenres();

    void addGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenre(int id);
}
