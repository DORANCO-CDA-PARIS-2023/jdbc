package doranco.model;

import doranco.entity.Student;

import java.util.Set;

public class StudentDao extends Dao implements IStudentDao {

    @Override
    public int addStudent(Student student) {
        return 0;
    }

    @Override
    public Set<Student> getAllStudents() {
        return null;
    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudent(Student student) {

    }

    @Override
    public void deleteStudentById(int id) {

    }
}
