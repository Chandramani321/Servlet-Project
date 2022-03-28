
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginData")
public class LoginData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name, pass1;
	String user = "", conPass = "";
	Connection con = null;
	DataBase db = null;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = "", pass = "";

		PrintWriter out = response.getWriter();

		userName = request.getParameter("e_mail");
		pass = request.getParameter("p_pass");
		HttpSession session = request.getSession();
		session.setAttribute("uname", name);

		db = new DataBase();

		// System.out.println("Database " + name + " Welcome is " + pass1);
		servletCong();
		int flag = db.loginVerify(userName, pass, user, conPass);

		if (flag == 0) {
			RequestDispatcher rd = request.getRequestDispatcher("LoginDesign.html");
			// RequestDispatcher rd1 = request.getRequestDispatcher("LogoutDesign.html");
			out.print("<b>UserName not Found</b>");

			rd.include(request, response);
		}
		if (flag == 1) {

			RequestDispatcher rd = request.getRequestDispatcher("LoginDesign.html");
			out.print("<b>Password Incorrect</b>");
			rd.include(request, response);
		}
		if (flag == 2) {

			ResultSet rs = null;
			try {
				String query = "select name from studentInfo where Email=?";
				Connection c = db.dbConnection(user, conPass);
				PreparedStatement pst = c.prepareStatement(query);
				pst.setString(1, userName);
				rs = pst.executeQuery();
				if (rs.next()) {

					String name = rs.getString("Name");
					System.out.println("Name is " + name);
					// uccefull succ = new Succefull();

					request.setAttribute("Name", name);
					RequestDispatcher rd = request.getRequestDispatcher("Succefull");

					rd.forward(request, response);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			// db.RetriveName(userName);

		}

	}

	public void servletCong() {
		ServletConfig config = getServletConfig();
		ServletContext context = getServletContext();
		user = config.getInitParameter("user");
		conPass = context.getInitParameter("password");

	}

}
