package doranco.entity;

public class Student {

    // We cretae all the var
    private int id;
    private String name;
    private String firstname;
    private String studentNumber;

    // The constructor
    public Student(int id, String name, String firstname, String studentNumber) {
        this.id = id;
        this.name = name;
        this.firstname = firstname;
        this.studentNumber = studentNumber;
    }

    // The getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    // The student object to get in the others scripts
    public String toString() {
        return "Student{" +
                "ID=" + id +
                ", Name='" + name +
                ", Firstname='" + firstname +
                ", Student Number='" + studentNumber +
                '}';
    } // to upgrade to get a better style
}
