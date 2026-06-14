import java.io.IOException;
import java.io.PrintWriter;

// For Tomcat 10+ use: jakarta.servlet.*
// For Tomcat 9 or below use: javax.servlet.*
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// This maps the servlet to the form's action="register"
@WebServlet("/register")
public class registerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 1. Set the content type for response output
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// 2. Fetch data from HTML inputs using the 'name' attributes
		String fullName = request.getParameter("fullName");
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		// 3. Simple Backend Validation
		if (password == null || !password.equals(confirmPassword)) {
			out.println("<script type='text/javascript'>");
			out.println("alert('Passwords do not match! Please try again.');");
			out.println("window.history.back();"); // Sends user back to registration page
			out.println("</script>");
			return;
		}

		// 4. Core Logic (Print to console as a placeholder for Database Saving)
		System.out.println("--- New Registration Received ---");
		System.out.println("Name: " + fullName);
		System.out.println("Username: " + username);
		System.out.println("Email: " + email);
		System.out.println("Phone: " + phone);

		// TODO: Insert your JDBC code here to save to MySQL/PostgreSQL

		// 5. Success Action: Send an alert and redirect to the login page
		out.println("<script type='text/javascript'>");
		out.println("alert('Registration successful for " + username + "!');");
		out.println("window.location.href='main/loginPage.html';"); 
		out.println("</script>");
	}
}