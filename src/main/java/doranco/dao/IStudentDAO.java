package doranco.dao;

import java.sql.SQLException;

import doranco.entity.Student;

public interface IStudentDAO extends ICrud<Student>{
    void updateStudent(Student student) throws SQLException;
}