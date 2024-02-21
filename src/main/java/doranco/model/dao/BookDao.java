package doranco.model.dao;

import doranco.entity.Book;
import doranco.model.connection.DataSourceSingleton;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements IBookDao {

    @Override
    public int addBook(Book book) throws Exception {
        if (book == null) {
        throw new NullPointerException("Le livre à ajouter ne doit pas être nul");
    }
    if (book.getTitle() == null || book.getTitle().trim().isEmpty() ||
        book.getYearPublish() == 0 || book.getIdAuthor() == 0) {
        throw new IllegalArgumentException("Tous les paramètres du livre à ajouter sont obligatoires");
    }

    String query = "INSERT INTO book (title, year_publish, id_author) VALUES (?, ?, ?)";

    try (Connection connection = DataSourceSingleton.getInstance().getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setInt(2, book.getYearPublish());
        preparedStatement.setInt(3, book.getIdAuthor());
        preparedStatement.executeUpdate();

        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if (resultSet != null && resultSet.next()) {
            return resultSet.getInt(1);
        }
    }

    return -1;
    }

    @Override
    public void updateBook(Book book) throws Exception {
       if (book == null) {
            throw new NullPointerException("Le livre à mettre à jour ne doit pas être nul");
        }
        if (book.getTitle() == null || book.getTitle().trim().isEmpty() ||
            book.getYearPublish() == 0 || book.getIdAuthor() == 0 || book.getId() == 0) {
            throw new IllegalArgumentException("Tous les paramètres du livre à mettre à jour sont obligatoires");
        }

        String query = "UPDATE book SET title = ?, year_publish = ?, id_author = ? WHERE id = ?";

        try (Connection connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setInt(2, book.getYearPublish());
            preparedStatement.setInt(3, book.getIdAuthor());
            preparedStatement.setInt(4, book.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void removeBook(int id) throws Exception {
        if (id <= 0) {
            throw new IllegalArgumentException("L'ID du livre à supprimer doit être supérieur à zéro");
        }

        String query = "DELETE FROM book WHERE id = ?";

        try (Connection connection = DataSourceSingleton.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
    }

	@Override
	public List<Book> getAllBooks() throws Exception {
		List<Book> bookList = new ArrayList<>();

        String query = "SELECT * FROM book";

        try (Connection connection = DataSourceSingleton.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));
                book.setYearPublish(resultSet.getInt("year_publish"));
                book.setIdAuthor(resultSet.getInt("id_author"));
                bookList.add(book);
            }
        }

        return bookList;
	}

	@Override
	public Book getBookById(int id) throws Exception {
		 String query = "SELECT * FROM book WHERE id = ?";
	        Book book = null;

	        try (Connection connection = DataSourceSingleton.getInstance().getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, id);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    book = new Book();
	                    book.setId(resultSet.getInt("id"));
	                    book.setTitle(resultSet.getString("title"));
	                    book.setYearPublish(resultSet.getInt("year_publish"));
	                    book.setIdAuthor(resultSet.getInt("id_author"));
	                }
	            }
	        }

	        return book;
	}
}