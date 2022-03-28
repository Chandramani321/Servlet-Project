
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FirstProgram")
public class FirstProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = request.getParameter("userName");
		String Password = request.getParameter("Password");

		Connector c = new Connector();

		// c.connection();
		c.CheckValidate(name, Password);
		RequestDispatcher rd = request.getRequestDispatcher("Succefull");
		rd.forward(request, response);

	}
}
