
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		HttpSession session = request.getSession();
//		session.invalidate();
//		out.print("You are successfully logged out!");
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute("uname");
			session.invalidate();
			response.sendRedirect(request.getContextPath() + "/LoginDesign.html");
		}

	}

}
