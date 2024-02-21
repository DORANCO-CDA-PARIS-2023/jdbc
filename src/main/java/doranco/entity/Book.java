package doranco.entity;

public class Book {
    private int id;
    private String title;
    private int yearPublish;
    private int idAuthor;

    public Book() {
    }

    public Book(int id, String title, int yearPublish, int idAuthor) {
        this.id = id;
        this.title = title;
        this.yearPublish = yearPublish;
        this.idAuthor = idAuthor;
    }
    
    public Book(String title, int yearPublish, int idAuthor) {
        this.title = title;
        this.yearPublish = yearPublish;
        this.idAuthor = idAuthor;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYearPublish() {
        return yearPublish;
    }

    public void setYearPublish(int yearPublish) {
        this.yearPublish = yearPublish;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }
}