package doranco;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Connection
        String url = "jdbc:mysql://localhost:3306/doranco_merise";
        String user = "root";
        String password = ""; // We must put it even if it's empty (error without)

        Scanner scanner = new Scanner(System.in); // Source :
                                                  // https://koor.fr/Java/Tutorial/java_regular_expression_scanner.wp
        System.out.println("Enter Student ID :");
        int studentId = scanner.nextInt();

        // The query we want : here : all the datas from student depending on the id
        // written in the terminal
        String studentQuery = "SELECT * FROM student WHERE id = ?";

        // Query to find books borrowed by the specified student
        String bookQuery = "SELECT book.title FROM borrow " +
                "JOIN book ON borrow.id_book = book.id " +
                "WHERE borrow.id_student = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmtStudent = conn.prepareStatement(studentQuery);
                PreparedStatement pstmtBook = conn.prepareStatement(bookQuery)) {

            pstmtStudent.setInt(1, studentId);
            ResultSet rsStudent = pstmtStudent.executeQuery();

            if (rsStudent.next()) {
                Student student = new Student(
                        rsStudent.getInt("id"),
                        rsStudent.getString("name"),
                        rsStudent.getString("firstname"),
                        rsStudent.getString("student_number"));

                System.out.println("Student Found: " + student);

                pstmtBook.setInt(1, studentId);
                ResultSet rsBook = pstmtBook.executeQuery();

                System.out.println("Books borrowed by " + student.getName() + ":");
                while (rsBook.next()) {
                    String title = rsBook.getString("title");
                    System.out.println(title);
                }
            } else {
                System.out.println("No student found with this ID: " + studentId);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Error without the try catch error
            //
        }
    }
}
