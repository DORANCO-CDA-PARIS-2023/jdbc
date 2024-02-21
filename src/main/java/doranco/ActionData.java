package doranco;

import doranco.entity.Author;
import doranco.entity.Book;
import doranco.entity.Genre;
import doranco.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class ActionData {

    private final ActionType actionType;
    private final DataType dataType;

    public ActionData(ActionType actionType, DataType dataType) {
        this.actionType = actionType;
        this.dataType = dataType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public DataType getDataType() {
        return dataType;
    }

    private int getInt(Scanner sc, String prompt, int minValue, int maxValue) {
        int result = 0;
        boolean done = false;
        do {
            System.out.println(prompt);
            try {
                int input = sc.nextInt();
                if (input >= minValue && input <= maxValue) {
                    result = input;
                    done = true;
                }
            } catch (Exception ignored) {

            } finally {
                sc.nextLine();
            }
        } while (!done);

        return result;
    }

    private String getString(Scanner sc, String prompt, boolean onlyNumeric) {
        String result = null;

        do {
            System.out.println(prompt);
            try {
                if (onlyNumeric) {
                    int input = sc.nextInt();
                    sc.nextLine();
                    result = String.valueOf(input);
                } else {
                    String input = sc.nextLine();
                    result = input;
                }
            } catch (Exception ignored) {

            } finally {
                if (onlyNumeric) {
                    sc.nextLine();
                }
            }
        } while (result == null);

        return result;
    }

    private Date getDate(Scanner sc, String prompt, SimpleDateFormat format) {
        Date date = null;

        do {
            System.out.println(prompt);
            try {
                String input = sc.nextLine();
                date = format.parse(input);
            } catch (Exception ignored) { }
        } while (date == null);

        return date;
    }

    public void execute(Scanner sc) {

        switch (dataType) {
            case BOOK -> {
                IBookDao bookDao = new BookDao();
                switch (actionType) {
                    case READ -> {
                        try {
                            Set<Book> books = bookDao.get();
                            books.stream().map(Book::toString).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case UPDATE -> {
                        Book book = new Book();
                        book.setId(getInt(sc, "Entrez l'id du " + dataType.name() + " à modifier:",
                                1, Integer.MAX_VALUE));
                        book.setTitle(getString(sc, "Entrez le nouveau titre:", false));
                        book.setYearPublish(getInt(sc, "Entrez la nouvelle année de publication (yyyy):",
                                Integer.MIN_VALUE, Integer.MAX_VALUE));
                        book.setIdAuthor(getInt(sc, "Entrez le nouvelle id de l'auteur:", 1,
                                Integer.MAX_VALUE));

                        try {
                            bookDao.update(book);
                            System.out.println(dataType.name() + " mis à jour avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case CREATE -> {
                        Book book = new Book();
                        book.setTitle(getString(sc, "Entrez le titre du nouveau livre:", false));
                        book.setYearPublish(getInt(sc, "Entrez l'année de publication du nouveau livre:",
                                Integer.MIN_VALUE, Integer.MAX_VALUE));
                        book.setIdAuthor(getInt(sc, "Entrez l'id de l'auteur du nouveau livre:", 1,
                                Integer.MAX_VALUE));

                        try {
                            int createdId = bookDao.add(book);
                            System.out.println(dataType.name() + " crée avec succès (id: " + createdId + ").");
                        } catch (Exception e) {

                        }
                    }
                    case DELETE -> {
                        Book book = new Book();
                        book.setId(getInt(sc, "Entrez l'id du livre à supprimer:", 1, Integer.MAX_VALUE));

                        try {
                            bookDao.delete(book);
                            System.out.println(dataType.name() + " supprimé avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            case GENRE -> {
                IGenreDao genreDao = new GenreDao();
                switch (actionType) {
                    case CREATE -> {
                        Genre genre = new Genre();
                        genre.setName(getString(sc, "Entrez le nom du nouveau genre:", false));

                        try {
                            int createdId = genreDao.add(genre);
                            System.out.println(dataType.name() + " crée avec succès (id: " + createdId + ").");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case READ -> {
                        try {
                            Set<Genre> genres = genreDao.get();
                            genres.stream().map(Genre::toString).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case UPDATE -> {
                        Genre genre = new Genre();
                        genre.setId(getInt(sc, "Entrez l'id du " + dataType.name() + " à modifier:", 1,
                                Integer.MAX_VALUE));
                        genre.setName(getString(sc, "Entrez le nouveau nom du genre:", false));

                        try {
                            genreDao.update(genre);
                            System.out.println(dataType.name() + " mis à jour avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case DELETE -> {
                        Genre genre = new Genre();
                        genre.setId(getInt(sc, "Entrez l'id du genre à supprimer:", 1, Integer.MAX_VALUE));

                        try {
                            genreDao.delete(genre);
                            System.out.println(dataType.name() + " supprimé avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            case AUTHOR -> {
                IAuthorDao authorDao = new AuthorDao();
                switch (actionType) {
                    case CREATE -> {
                        Author author = new Author();
                        author.setName(getString(sc, "Entrez le nom du nouvel auteur:", false));
                        author.setFirstname(getString(sc, "Entrez le prénom du nouvel auteur", false));
                        author.setBirthday(getDate(sc, "Entrez la date de naissance de l'auteur (dd/MM/yyyy):",
                                new SimpleDateFormat("dd/MM/yyyy")));

                        try {
                            int createdId = authorDao.add(author);
                            System.out.println(dataType.name() + " crée avec succès (id: " + createdId + ").");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case READ -> {
                        try {
                            Set<Author> authors = authorDao.get();
                            authors.stream().map(Author::toString).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case UPDATE -> {
                        Author author = new Author();
                        author.setId(getInt(sc, "Entrez l'id de l'auteur à modifier:", 1,
                                Integer.MAX_VALUE));
                        author.setName(getString(sc, "Entrez le nouveau nom de l'auteur:", false));
                        author.setFirstname(getString(sc, "Entrez le nouveau prénom de l'auteur", false));
                        author.setBirthday(getDate(sc, "Entrez la nouvelle date de naissance de l'auteur",
                                new SimpleDateFormat("dd/MM/yyyy")));

                        try {
                            authorDao.update(author);
                            System.out.println(dataType.name() + " mis à jour avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case DELETE -> {
                        Author author = new Author();
                        author.setId(getInt(sc, "Entrez l'id de l'auteur à supprimer:", 1, Integer.MAX_VALUE));

                        try {
                            authorDao.delete(author);
                            System.out.println(dataType.name() + " supprimé avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            case BORROW -> {
                IBorrowDao borrowDao = new BorrowDao();
            }
            case STUDENT -> {
                IStudentDao studentDao = new StudentDao();
            }
            case BOOK_GENRE -> {
                IBookGenreDao bookGenreDao = new BookGenreDao();
            }
        }

    }
}
