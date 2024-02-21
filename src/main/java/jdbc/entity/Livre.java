package jdbc.entity;

public class Livre {
	int id;
	String title;
	int year_publish;
	Auteur author;
	
	
	
	public Livre() {
		super();
	}

	public Livre(int id, String title, int year_publish, Auteur author) {
		super();
		this.id = id;
		this.title = title;
		this.year_publish = year_publish;
		this.author = author;
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

	public Auteur getAuthor() {
		return author;
	}

	public void setAuthor(Auteur author) {
		this.author = author;
	}
	
	public String toString() {
		return id + ". " + title + " (écrit par " + author.getFirstname() + " " + author.getName() + ", paru en " + year_publish + ")";
	}
	
}
