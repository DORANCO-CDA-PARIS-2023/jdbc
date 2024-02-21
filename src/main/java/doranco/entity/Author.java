package doranco.entity;

import java.util.Date;

public class Author {
    private int id;
    private String name;
    private String firstname;
    private Date birthday;


    public Author() {}

    public Author(int id, String name, String firstname, Date birthday) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.birthday = birthday;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", year=" + firstname +
                ", authorId=" + birthday +
                '}';
    }
}