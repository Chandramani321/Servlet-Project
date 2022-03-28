
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String user = "", conPass = "";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("header.html").include(request, response);
		String fullName = "", email = "", password = "", RePassword = "", Gender = "";
		fullName = request.getParameter("f_name");
		email = request.getParameter("e_mail");
		password = request.getParameter("pass");
		RePassword = request.getParameter("retypePass");

		Gender = request.getParameter("A");
		DataBase db = new DataBase();
		servletCong();
		String error_message = db.SignUpVerify(fullName, email, password, RePassword, Gender, user, conPass);
		if (error_message.equals("Full Name")) {

			out.print("<b>Please Enter Full Name</b>");

			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		} else if (error_message.equals("email")) {

			out.print("<b>Please Enter Email</b>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		}

		else if (error_message.equals("password")) {

			out.print("<b>Please Enter password</b>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		} else if (error_message.equals("ReturnPass")) {

			out.print("<b>Please Enter Returnpassword</b>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		} else if (error_message.equals("passwordNoMatch")) {

			out.print("<b>passwordword is not match</b>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		} else if (error_message.equals("GenderA")) {

			out.print("<b>Please Select Gender</b>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		} else if (error_message.equals("Existing User")) {

			out.print("<b>UserName alredy Exists</b>");
			RequestDispatcher rd = request.getRequestDispatcher("SignUpDesign.html");
			rd.include(request, response);

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/WebPage.html");
			rd.include(request, response);

		}

		// request.removeAttribute(fullName + "" + email);
	}

	public void servletCong() {
		ServletConfig config = getServletConfig();
		ServletContext context = getServletContext();
		user = config.getInitParameter("user");
		conPass = context.getInitParameter("password");
		System.out.println(user + " ya " + conPass);

	}

}
