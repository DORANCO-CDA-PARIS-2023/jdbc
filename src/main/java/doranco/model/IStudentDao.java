package doranco.model;

import doranco.entity.Student;

import java.util.Set;

public interface IStudentDao extends ICrud<Student> {

    // CREATE
    int add(Student student) throws Exception;

    // READ
    Set<Student> get() throws Exception;
    Student getById(int id) throws Exception;


    // UPDATE
    void update(Student student) throws Exception;

    // DELETE
    void delete(Student student) throws Exception;
    void deleteById(int id) throws Exception;


}
