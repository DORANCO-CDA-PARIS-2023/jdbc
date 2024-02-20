package doranco.model;

import doranco.entity.Student;

import java.util.Set;

public interface IStudentDao {

    // CREATE
    int addStudent(Student student) throws Exception;

    // READ
    Set<Student> getAllStudents() throws Exception;
    Student getStudentById(int id) throws Exception;


    // UPDATE
    void updateStudent(Student student) throws Exception;

    // DELETE
    void deleteStudent(Student student) throws Exception;
    void deleteStudentById(int id) throws Exception;


}
