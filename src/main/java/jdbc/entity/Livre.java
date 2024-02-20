package jdbc.entity;

public class Livre {
	int id;
	String title;
	int year_publish;
	int id_author;
	
	
	
	public Livre() {
		super();
	}

	public Livre(int id, String title, int year_publish, int id_author) {
		super();
		this.id = id;
		this.title = title;
		this.year_publish = year_publish;
		this.id_author = id_author;
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

	public int getId_author() {
		return id_author;
	}

	public void setId_author(int id_author) {
		this.id_author = id_author;
	}
	
	public String toString() {
		return id + ". " + title + " (paru en " + year_publish + ")";
	}
	
}
