package doranco.entity;

public class Book {

	private int id;
	private String title;
	private int year_publish;
	private int authorId;
	
	public Book() {}

    public Book(String title, int year, int authorId) {
        this.title = title;
        this.year_publish = year;
        this.authorId = authorId;
    }
    
	public Book(int id, String title, int year, int authorId) {
		this.id = id;
		this.title = title;
		this.year_publish = year;
		this.authorId = authorId;
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

	public int getYear_publish() {
		return year_publish;
	}

	public void setYear_publish(int year_publish) {
		this.year_publish = year_publish;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", year_publish=" + year_publish + ", authorId=" + authorId
				+ "]";
	}
	
	
	
}
