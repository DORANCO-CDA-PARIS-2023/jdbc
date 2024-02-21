package doranco.service;

import doranco.entity.*;
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

    private Date getDate(Scanner sc, String prompt, SimpleDateFormat format, boolean canBeNull) {
        Date date = null;

        do {
            System.out.println(prompt);
            try {
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("null") && canBeNull) {
                    return null;
                }
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
                                new SimpleDateFormat("dd/MM/yyyy"), false));

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
                                new SimpleDateFormat("dd/MM/yyyy"), false));

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
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                switch (actionType) {
                    case CREATE -> {
                        Borrow borrow = new Borrow();
                        borrow.setDateBorrow(getDate(sc, "Entrez la date du nouvel emprunt (dd/MM/yyyy):",
                                dateFormat, false));
                        borrow.setDateBackSchedulde(getDate(sc,
                                "Entrez la date prévu de retour du nouvel emprunt (dd/MM/yyyy):",
                                dateFormat, false));
                        borrow.setDateBack(getDate(sc, "Entrez la date de retour du nouvel emprunt (dd/MM/yyyy|NULL):",
                                dateFormat, true));
                        borrow.setIdBook(getInt(sc, "Entrez l'id du livre emprunté du nouvel emprunt:",
                                1, Integer.MAX_VALUE));
                        borrow.setIdStudent(getInt(sc, "Entrez l'id de l'étudiant qui emprunte du nouvel emprunt:",
                                1, Integer.MAX_VALUE));

                        try {
                            int createdId = borrowDao.add(borrow);
                            System.out.println(dataType.name() + " crée avec succès (id: " + createdId + ").");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case READ -> {
                        try {
                            Set<Borrow> borrows = borrowDao.get();
                            borrows.stream().map(Borrow::toString).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case UPDATE -> {
                        Borrow borrow = new Borrow();
                        borrow.setId(getInt(sc, "Entrez l'id de l'emprunt à modifier:", 1, Integer.MAX_VALUE));
                        borrow.setDateBorrow(getDate(sc, "Entrez la nouvelle date d'emprunt de l'emprunt (dd/MM/yyyy):",
                                dateFormat, false));
                        borrow.setDateBackSchedulde(getDate(sc, "Entrez la date de retour prévu de l'emprunt (dd/MM/yyyy):",
                                dateFormat, false));
                        borrow.setDateBack(getDate(sc, "Entrez la date de retour de l'emprunt (dd/MM/yyyy|NULL):",
                                dateFormat, true));
                        borrow.setIdBook(getInt(sc, "Entrez l'id du livre emprunté de l'emprunt:", 1,
                                Integer.MAX_VALUE));
                        borrow.setIdStudent(getInt(sc, "Entrez l'id de l'étudiant de l'emprunt:", 1,
                                Integer.MAX_VALUE));

                        try {
                            borrowDao.update(borrow);
                            System.out.println(dataType.name() + " mis à jour avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case DELETE -> {
                        Borrow borrow = new Borrow();
                        borrow.setId(getInt(sc, "Entrez l'id de l'emprunt à supprimer:", 1,
                                Integer.MAX_VALUE));

                        try {
                            borrowDao.delete(borrow);
                            System.out.println(dataType.name() + " supprimé avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            case STUDENT -> {
                IStudentDao studentDao = new StudentDao();
                switch (actionType) {
                    case CREATE -> {
                        Student student = new Student();
                        student.setName(getString(sc, "Entrez le nom du nouvel étudiant:", false));
                        student.setFirstname(getString(sc, "Entrez le prénom du nouvel étudiant", false));
                        student.setStudentNumber(getString(sc, "Entrez le numéro du nouvel étudiant:", false));

                        try {
                            int createdId = studentDao.add(student);
                            System.out.println(dataType.name() + " crée avec succès (id: " + createdId + ").");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case READ -> {
                        try {
                            Set<Student> students = studentDao.get();
                            students.stream().map(Student::toString).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case UPDATE -> {
                        Student student = new Student();
                        student.setId(getInt(sc, "Entrez l'id de l'étudiant à modifier:", 1,
                                Integer.MAX_VALUE));
                        student.setName(getString(sc, "Entrez le nouveau nom de l'étudiant:", false));
                        student.setFirstname(getString(sc, "Entrez le nouveau prénom de l'étudiant:",
                                false));
                        student.setStudentNumber(getString(sc, "Entrez le nouveau numéro de l'étudiant:",
                                false));

                        try {
                            studentDao.update(student);
                            System.out.println(dataType.name() + " mis à jour avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case DELETE -> {
                        Student student = new Student();
                        student.setId(getInt(sc, "Entrez l'id de l'étudiant à supprimer:", 1,
                                Integer.MAX_VALUE));

                        try {
                            studentDao.delete(student);
                            System.out.println(dataType.name() + " supprimé avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
            case BOOK_GENRE -> {
                IBookGenreDao bookGenreDao = new BookGenreDao();
                switch (actionType) {
                    case CREATE -> {
                        BookGenre bookGenre = new BookGenre();
                        bookGenre.setIdBook(getInt(sc, "Entrez l'id du livre à qui appliquer un nouveau genre:",
                                1, Integer.MAX_VALUE));
                        bookGenre.setIdGenre(getInt(sc, "Entrez l'id du genre à appliquer au livre", 1,
                                Integer.MAX_VALUE));

                        try {
                            int createdId = bookGenreDao.add(bookGenre);
                            System.out.println(dataType.name() + " crée avec succès (id: " + createdId + ").");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    case READ -> {
                        try {
                            Set<BookGenre> bookGenres = bookGenreDao.get();
                            bookGenres.stream().map(BookGenre::toString).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case UPDATE -> {
                        System.out.println("La modification d'un " + dataType.name() + " est impossible.");
                    }
                    case DELETE -> {
                        BookGenre bookGenre = new BookGenre();
                        bookGenre.setIdBook(getInt(sc, "Entrez l'id du livre pour lequel vous souhaitez supprimer un genre:",
                                1, Integer.MAX_VALUE));
                        bookGenre.setIdGenre(getInt(sc, "Entrez l'id du genre que vous souhaitez supprimer pour le livre donné:",
                                1, Integer.MAX_VALUE));

                        try {
                            bookGenreDao.delete(bookGenre);
                            System.out.println(dataType.name() + " supprimé avec succès.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        }

    }
}
