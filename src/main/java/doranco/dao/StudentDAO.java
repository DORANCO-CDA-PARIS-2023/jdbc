package doranco.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import doranco.entity.Student;
import doranco.singleton.DataSourceSingleton;

public class StudentDAO implements IStudentDAO{

    private final Connection connection;
    private final Statement statement;

    public StudentDAO() throws Exception {
        
        connection = DataSourceSingleton.getInstance().getConnection();
        statement = connection.createStatement();
    }

    @Override
    public Student find(int id) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            try (ResultSet result = ps.executeQuery()) {
                if (result.next()) {
                    return new Student(
                            result.getInt("id"),
                            result.getString("name"),
                            result.getString("firstname"),
                            result.getString("student_number")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public List<Student> findAll() throws SQLException {
        String query = "SELECT * FROM student";
        try (ResultSet result = statement.executeQuery(query)) {
            List<Student> students = new ArrayList<>();

            while (result.next()) {
                students.add(new Student(
                        result.getInt("id"),
                        result.getString("name"),
                        result.getString("firstname"),
                        result.getString("student_number")
                ));
            }

            return students.isEmpty() ? null : students;
        }
    }

    @Override
    public void create(Student entity) throws SQLException {
        String query = "INSERT INTO student (name, firstname, student_number) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, entity.getName());
            ps.setString(2, entity.getFirstname());
            ps.setString(3, entity.getStudentNumber());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    @Override
    public void updateStudent(Student student){

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = DataSourceSingleton.getInstance().getConnection();
            String query = "UPDATE student SET name = ?, firstname = ?, student_number = ? WHERE id = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, student.getName());
            ps.setString(2, student.getFirstname());
            ps.setString(3, student.getStudentNumber());
            ps.setInt(4, student.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}