import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class loginPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		// 1. Retrieve the form data using the "name" attributes from your HTML
		String role = request.getParameter("role");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Clean up inputs to avoid accidental whitespace issues
		if (username != null) username = username.trim();
		if (password != null) password = password.trim();

		boolean isAuthenticated = false;

		// 2. Validate credentials based on your Meowy Groom system roles
		if ("customer".equals(role) && "aliahmad".equals(username) && "ali123".equals(password)) {
			isAuthenticated = true;
		} else if ("staff".equals(role) && "mikejohnson".equals(username) && "mike123".equals(password)) {
			isAuthenticated = true;
		} else if ("owner".equals(role) && "mohdhanafi".equals(username) && "hanafi123".equals(password)) {
			isAuthenticated = true;
		}

		// 3. Decide where to send the user based on authentication status
		if (isAuthenticated) {
			// Create a server-side session to keep the user logged in
			HttpSession session = request.getSession();
			session.setAttribute("userRole", role);
			session.setAttribute("username", username);

			// Redirect them to your main application dashboard/sidebar
			response.sendRedirect("sidebar.html");
		} else {
			// Authentication failed: Send an alert and kick them back to the login page
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("<script type='text/javascript'>");
			out.println("alert('Invalid username, password, or role selection. Please try again.');");
			out.println("window.history.back();"); // Sends user back to the login form
			out.println("</script>");
		}
	}
}
