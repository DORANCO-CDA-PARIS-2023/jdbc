package doranco.model;

import doranco.entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;

public class StudentDao extends Dao implements IStudentDao {

    @Override
    public int add(Student student) throws Exception {
        Connection connection = null;
        String request = "INSERT INTO student (name, firstname, student_number) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setString(2, student.getFirstname());
            ps.setString(3, student.getStudentNumber());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return id;
    }

    @Override
    public Set<Student> get() throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM students";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Set<Student> students = new HashSet<>();

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    Student student = new Student();
                    student.setId(rs.getInt("id"));
                    student.setName(rs.getString("name"));
                    student.setFirstname(rs.getString("firstname"));
                    student.setStudentNumber(rs.getString("student_number"));
                    students.add(student);
                }
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return students;
    }

    @Override
    public Student getById(int id) throws Exception {
        Connection connection = null;
        String request = "SELECT * FROM students WHERE id=?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student student = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs != null && rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setFirstname(rs.getString("firstname"));
                student.setStudentNumber(rs.getString("student_number"));
            }
        } finally {
            closeDataFlow(connection, ps, rs);
        }

        return student;
    }

    @Override
    public void update(Student student) throws Exception {
        Connection connection = null;
        String request = "UPDATE students SET name=?, firstname=?, student_number=? WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setString(1, student.getName());
            ps.setString(2, student.getFirstname());
            ps.setString(3, student.getStudentNumber());
            ps.setInt(4, student.getId());
            ps.executeUpdate();

        } finally {
            closeDataFlow(connection, ps);
        }

    }

    @Override
    public void delete(Student student) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM student WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setInt(1, student.getId());
            ps.executeUpdate();
        } finally {
            closeDataFlow(connection, ps);
        }

    }

    @Override
    public void deleteById(int id) throws Exception {
        Connection connection = null;
        String request = "DELETE FROM student WHERE id=?";
        PreparedStatement ps = null;

        try {
            connection = DorancoMeriseDB.getINSTANCE().getConnection();
            ps = connection.prepareStatement(request);
            ps.setInt(1, id);
            ps.executeUpdate();
        } finally {
            closeDataFlow(connection, ps);
        }
    }
}
