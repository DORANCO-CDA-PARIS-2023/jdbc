package doranco.entity;

public class Student {
    private int id;
    private String name;
    private String firstname;
    private String studentNumber;


    public Student() {}

    public Student(int id, String name, String firstname, String studentNumber) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.studentNumber = studentNumber;
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

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + name + '\'' +
                ", year=" + firstname +
                ", authorId=" + studentNumber +
                '}';
    }
}
