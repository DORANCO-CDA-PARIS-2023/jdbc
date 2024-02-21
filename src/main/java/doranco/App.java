package doranco;

import doranco.dao.*;
import doranco.entity.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws SQLException {
        BookDaoImpl bookDao = new BookDaoImpl();
        List<Book> books = bookDao.findAll();

        AuthorDaoImpl authorDao = new AuthorDaoImpl();
        List<Author> authors = authorDao.findAll();

        StudentDaoImpl studentDao = new StudentDaoImpl();
        List<Student> students = studentDao.findAll();

        BorrowDaoImpl borrowDao = new BorrowDaoImpl();
        List<Borrow> borrows = borrowDao.findAll();

        GenreDaoImpl genreDao = new GenreDaoImpl();
        List<Genre> genres = genreDao.findAll();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();


        System.out.println("--------------------");
        System.out.println("Liste des livres");
        System.out.println("--------------------");

        for (Book book: books)
        {
            System.out.println(book);
        }

        System.out.println("--------------------");
        System.out.println("Listes des auteurs");
        System.out.println("--------------------");

        for (Author author: authors)
        {
            System.out.println(author);
        }

        System.out.println("--------------------");
        System.out.println("Listes des étudiants");
        System.out.println("--------------------");

        for (Student student: students)
        {
            System.out.println(student);
        }

        System.out.println("--------------------");
        System.out.println("Listes des emprunts");
        System.out.println("--------------------");

        for (Borrow borrow: borrows)
        {
            System.out.println(borrow);
        }

        System.out.println("--------------------");
        System.out.println("Listes des genres");
        System.out.println("--------------------");

        for (Genre genre: genres)
        {
            System.out.println(genres);
        }
    }
}
