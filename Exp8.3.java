// Steps to Develop a JSP-Based Student Portal with Attendance Submission
  
// 1. Set Up the Database (MySQL)
// Create a database named StudentDB.
// Create a table attendance with columns: id, student_name, date, status.
// Insert sample records for testing.

// 2. Configure Your Java Project
// Add MySQL JDBC Driver (via Maven or manually).
// Set up Apache Tomcat in your IDE (Eclipse/VScode).

// 3. Create a Database Connection Class (DBConnection.java)
// This utility class connects to MySQL.

// 4. Create the JSP Form (attendance.jsp)
// A form where users enter student name, date, and attendance status (Present/Absent).

// 5. Develop the Servlet (AttendanceServlet.java)
// Handles form submission and saves attendance to the database.
// Uses JDBC to insert data into MySQL.

// 6. Configure web.xml (If Needed)
// Map AttendanceServlet to handle form submissions.

// 7. Deploy & Test
// Run on Tomcat.
// Access via http://localhost:8080/YourAppName/attendance.jsp.
// Submit attendance, check database for saved records.

// Enhancements (Optional)
// - Display submitted attendance records in attendance.jsp.
// - Add CSS/Bootstrap for better UI.
// - Implement session handling for authentication.


<!DOCTYPE html>
<html>
<head>
    <title>Student Attendance</title>
</head>
<body>
    <h2>Enter Attendance Details</h2>
    <form action="AttendanceServlet" method="post">
        Student ID: <input type="text" name="id" /><br/>
        Name: <input type="text" name="name" /><br/>
        Date: <input type="date" name="date" /><br/>
        Status: 
        <select name="status">
            <option value="Present">Present</option>
            <option value="Absent">Absent</option>
        </select><br/><br/>
        <input type="submit" value="Submit Attendance" />
    </form>
</body>
</html>
---------------------------------







import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String date = req.getParameter("date");
        String status = req.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_db", "root", "yourpassword");

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO attendance (id, name, date, status) VALUES (?, ?, ?, ?)");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, status);

            int i = ps.executeUpdate();
            if (i > 0) {
                out.println("<h3>Attendance Recorded Successfully</h3>");
            } else {
                out.println("<h3>Failed to Record Attendance</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
---------------------------------------------

<web-app>
    <servlet>
        <servlet-name>AttendanceServlet</servlet-name>
        <servlet-class>AttendanceServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>AttendanceServlet</servlet-name>
        <url-pattern>/AttendanceServlet</url-pattern>
    </servlet-mapping>
</web-app>
-----------

CREATE DATABASE student_db;
USE student_db;

CREATE TABLE attendance (
    id INT,
    name VARCHAR(100),
    date DATE,
    status VARCHAR(10)
);

