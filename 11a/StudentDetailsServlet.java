import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StudentDetailsServlet")
public class StudentDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "StudentDB";
    private static final String USER = "root";   // Replace with your DB user
    private static final String PASS = "password"; // Replace with your DB password

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data from the request
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String program = request.getParameter("program");

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            // Step 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Step 2: Open a connection to the database
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Step A: Create Student Database
            Statement stmt = conn.createStatement();
            String sqlCreateDatabase = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sqlCreateDatabase);
            conn.setCatalog(DB_NAME);

            // Step B: Create Registration Table
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS Registration ("
                    + "id INT NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR(50), "
                    + "address VARCHAR(100), "
                    + "program VARCHAR(50), "
                    + "PRIMARY KEY (id))";
            stmt.executeUpdate(sqlCreateTable);

            // Step C: Insert Records
            String sqlInsert = "INSERT INTO Registration (name, address, program) VALUES (?, ?, ?)";
            PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert);
            pstmtInsert.setString(1, name);
            pstmtInsert.setString(2, address);
            pstmtInsert.setString(3, program);
            pstmtInsert.executeUpdate();

            // Insert additional sample records
            pstmtInsert.setString(1, "Alice");
            pstmtInsert.setString(2, "1234 Elm St.");
            pstmtInsert.setString(3, "Computer Science");
            pstmtInsert.executeUpdate();

            pstmtInsert.setString(1, "Bob");
            pstmtInsert.setString(2, "5678 Oak St.");
            pstmtInsert.setString(3, "Information Technology");
            pstmtInsert.executeUpdate();

            pstmtInsert.setString(1, "Charlie");
            pstmtInsert.setString(2, "91011 Pine St.");
            pstmtInsert.setString(3, "Engineering");
            pstmtInsert.executeUpdate();

            out.println("<h3>Student Details Inserted Successfully</h3>");

            // Step D: Display Records
            String sqlSelect = "SELECT * FROM Registration";
            ResultSet rs = stmt.executeQuery(sqlSelect);

            out.println("<h3>Registered Students:</h3>");
            while (rs.next()) {
                out.println("ID: " + rs.getInt("id") + ", ");
                out.println("Name: " + rs.getString("name") + ", ");
                out.println("Address: " + rs.getString("address") + ", ");
                out.println("Program: " + rs.getString("program") + "<br>");
            }

            // Step E: Update Records
            String sqlUpdate = "UPDATE Registration SET program = 'Data Science' WHERE id = 100 OR id = 101";
            stmt.executeUpdate(sqlUpdate);
            out.println("<h3>Updated Program for Students with IDs 100 and 101</h3>");

            // Step F: Delete Records
            String sqlDelete = "DELETE FROM Registration WHERE id = 101";
            stmt.executeUpdate(sqlDelete);
            out.println("<h3>Deleted Student Record with ID 101</h3>");

            // Clean-up environment
            rs.close();
            stmt.close();
            pstmtInsert.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}