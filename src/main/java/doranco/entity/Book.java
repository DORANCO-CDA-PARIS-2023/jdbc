package doranco.entity;

public class Book {
    private int id;
    private String title;
<<<<<<< HEAD
    private int year;
    private int authorId;

    public Book() {}

    public Book(int id, String title, int year, int authorId) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.authorId = authorId;
    }

    public Book(String title, int year, int authorId) {
        this.title = title;
        this.year = year;
        this.authorId = authorId;
    }

=======
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
    
>>>>>>> 68461191a51cc6aa355da581e0c64ddb70d18aa3
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

<<<<<<< HEAD
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", authorId=" + authorId +
                '}';
    }
}
=======
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
>>>>>>> 68461191a51cc6aa355da581e0c64ddb70d18aa3
