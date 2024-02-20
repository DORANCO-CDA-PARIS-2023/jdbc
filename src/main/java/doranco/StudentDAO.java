package doranco;

import java.util.List;

public interface StudentDAO {
    Student getStudentById(int id);

    List<Student> getAllStudents();

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int id);
}