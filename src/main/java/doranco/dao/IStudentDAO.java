package doranco.dao;

import doranco.entity.Student;

public interface IStudentDAO extends ICrud<Student>{
    void updateStudent(Student student);
}