package doranco.entity;

import java.util.Date;

public class Author {
	
	private int id;
	private String name;
	private String firstName;
	private Date birthday;
	
	public Author() {}
	
	
	
	public Author(String name, String firstName, Date birthday) {
		this.name = name;
		this.firstName = firstName;
		this.birthday = birthday;
	}



	public Author(int id, String name, String firstName, Date birthday) {
		this.id = id;
		this.name = name;
		this.firstName = firstName;
		this.birthday = birthday;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public Date getBirthday() {
		return birthday;
	}



	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}



	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", firstName=" + firstName + ", birthday=" + birthday + "]";
	}
}
