package doranco.dao;

import doranco.database.Database;
import doranco.entity.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao{
    private final Connection connection;
    private final Statement statement;

    public StudentDaoImpl() throws SQLException {
        connection = Database.getINSTANCE().getConnection();
        statement = connection.createStatement();
    }
    @Override
    public Student find(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        String query = "SELECT * FROM student";
        ResultSet result = statement.executeQuery(query);
        List<Student> students = new ArrayList<>();

        while (result.next())
        {
            students.add(new Student(
                result.getInt("id"),
                    result.getString("name"),
                    result.getString("firstname"),
                    result.getString("student_number")
            ));

        }
        return students.isEmpty() ? null : students;
    }

    @Override
    public void create(Student entity) {

    }

    @Override
    public void delete(int id) {

    }
}
