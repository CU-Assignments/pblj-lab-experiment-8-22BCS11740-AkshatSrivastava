// Steps to Implement
// 1. Set Up Your Environment
// - Install Java Development Kit (JDK)
// - Install Apache Tomcat (Servlet Container)
// - Set up an IDE (Eclipse, IntelliJ, or VScode)

// 2. Create an HTML Login Form (login.html)
// This form collects the username and password from the user.

// 3. Create the Java Servlet to Process Login (LoginServlet.java)
// This servlet reads username and password from the request.
// It checks the credentials.
// - If valid, it displays a welcome message.
// - If invalid, it redirects back to the login page.

// 4.  Configure web.xml
// 5. Deploy and Run
// - Place the login.html file inside the WebContent (for VScode) or webapp (for Maven projects).
// - Compile and deploy the servlet in Tomcat.
// Access the form in your browser:
// http://localhost:8080/login.html


<!DOCTYPE html>
<html>
<head><title>Login Page</title></head>
<body>
  <h2>Login Form</h2>
  <form action="LoginServlet" method="post">
    Username: <input type="text" name="username" /><br><br>
    Password: <input type="password" name="password" /><br><br>
    <input type="submit" value="Login" />
  </form>
</body>
</html>



----------------------------------------





Servlet Code (LoginServlet.java)

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    String username = request.getParameter("username");
    String password = request.getParameter("password");

    if ("admin".equals(username) && "admin123".equals(password)) {
      out.println("<h2>Welcome, " + username + "!</h2>");
    } else {
      out.println("<h2>Login Failed. Invalid credentials.</h2>");
    }
  }
}

-------------------------------------------

web.xml Configuration

<web-app>
  <servlet>
    <servlet-name>LoginServlet</servlet-name>
    <servlet-class>LoginServlet</servlet-class>
  </servlet>
  <servlet-mapping>
    <servlet-name>LoginServlet</servlet-name>
    <url-pattern>/LoginServlet</url-pattern>
  </servlet-mapping>
</web-app> 
