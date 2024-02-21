package jdbc.entity;

import java.util.Date;

public class Auteur {

	int id;
	String name;
	String firstname;
	Date birthday;
	
	public Auteur() {
		super();
	}

	public Auteur(int id, String name, String firstname, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.firstname = firstname;
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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Auteur [id=" + id + ", name=" + name + ", firstname=" + firstname + ", birthday=" + birthday + "]";
	}
	
	
	
	
}
