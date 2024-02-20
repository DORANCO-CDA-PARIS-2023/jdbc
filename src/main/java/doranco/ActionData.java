package doranco;

import doranco.entity.Book;
import doranco.entity.Genre;
import doranco.model.*;

import java.util.Scanner;

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

    public void execute(Scanner sc) throws Exception {

        switch (dataType) {
            case BOOK -> {
                IBookDao bookDao = new BookDao();
            }
            case GENRE -> {
                IGenreDao genreDao = new GenreDao();
            }
            case AUTHOR -> {
                IAuthorDao authorDao = new AuthorDao();
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
