// Steps to Create a JDBC-Integrated Servlet for Employee Management
// 1. Set Up Database
// Create a MySQL database (EmployeeDB).
// Create an employees table with columns (id, name, department, salary).
// Insert sample employee data.

// 2. Set Up Your Java Project
// Add MySQL JDBC Driver (via Maven or manually).
// Configure Apache Tomcat in your IDE.

// 3. Create Database Connection Class
// Write a utility class (DBConnection.java) to establish a connection with the MySQL database.

// 4. Develop the Servlet (EmployeeServlet.java)
// - Handle GET requests to fetch all employees or search by Employee ID.
// - Use JDBC to query data and display it in HTML format.
// - Implement a search form for filtering employees by ID.

// 5. Deploy and Run
// Deploy the servlet on Tomcat.
// Access it via http://localhost:8080/YourAppName/EmployeeServlet.
// (The page displays employee records and allows searching by ID.)

// Note : Enhancements (Optional)
// Improve UI with CSS & Bootstrap.


<!DOCTYPE html>
<html>
<head><title>Search Employee</title></head>
<body>
  <h2>Search Employee by ID</h2>
  <form action="EmployeeServlet" method="post">
    Enter ID: <input type="text" name="empId" />
    <input type="submit" value="Search" />
  </form>
</body>
</html>

-------------------------




Servlet Code (EmployeeServlet.java)

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String idStr = request.getParameter("empId");

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection con = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/company", "root", "yourpassword");

      Statement stmt = con.createStatement();
      ResultSet rs;

      if (idStr != null && !idStr.isEmpty()) {
        rs = stmt.executeQuery("SELECT * FROM employee WHERE id=" + idStr);
        if (rs.next()) {
          out.println("<h3>Employee Found:</h3>");
          out.println("ID: " + rs.getInt("id") + "<br>");
          out.println("Name: " + rs.getString("name") + "<br>");
          out.println("Email: " + rs.getString("email"));
        } else {
          out.println("<h3>No employee found with ID: " + idStr + "</h3>");
        }
      } else {
        rs = stmt.executeQuery("SELECT * FROM employee");
        out.println("<h3>All Employees:</h3>");
        while (rs.next()) {
          out.println("ID: " + rs.getInt("id") + ", ");
          out.println("Name: " + rs.getString("name") + ", ");
          out.println("Email: " + rs.getString("email") + "<br><br>");
        }
      }
      con.close();
    } catch (Exception e) {
      out.println("Error: " + e.getMessage());
    }
  }
}
-------------------------------------------------

web.xml Configuration

<web-app>
  <servlet>
    <servlet-name>EmployeeServlet</servlet-name>
    <servlet-class>EmployeeServlet</servlet-class>
  </servlet>

  <servlet-mapping>
    <servlet-name>EmployeeServlet</servlet-name>
    <url-pattern>/EmployeeServlet</url-pattern>
  </servlet-mapping>
</web-app> 
