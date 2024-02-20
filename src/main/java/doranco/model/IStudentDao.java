package doranco.model;

import doranco.entity.Student;

import java.util.Set;

public interface IStudentDao {

    // CREATE
    int addStudent(Student student);

    // READ
    Set<Student> getAllStudents();


    // UPDATE
    void updateStudent(Student student);

    // DELETE
    void deleteStudent(Student student);
    void deleteStudentById(int id);


}
