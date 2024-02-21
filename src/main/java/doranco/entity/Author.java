package doranco.entity;

import java.util.Date;

public class Author {
    private int id;
    private String firstname;
    private String name;
    private Date birthday;

    public Author() {
    }

    public Author(int id, String name, String firstname, Date birthday) {
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
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", firstname='" + firstname + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}