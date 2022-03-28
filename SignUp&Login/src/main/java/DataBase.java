import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;

public class DataBase extends HttpServlet {
	private static final String String = null;

	String user = "", conPass = "";
	Connection con;
	Connection c;

	public Connection dbConnection(String user, String conPass) {

		try {

			String url = "jdbc:sqlserver://localhost:1433;databaseName=student;" + "user=" + user + ";password="
					+ conPass;

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			con = DriverManager.getConnection(url);

		} catch (Exception e) {
			System.out.println(e);
		}

		return con;

	}

	public String SignUpVerify(String name, String email, String pass, String rePass, String gender, String user,
			String conPass) {

		String error_message = "";

		if (name.isEmpty()) {
			error_message = "Full Name";
			return error_message;
		}

		if (email.equals("")) {
			error_message = "email";
			return error_message;
		}
		if (pass.equals("")) {
			error_message = "password";
			return error_message;
		}
		if (rePass.equals("")) {
			error_message = "ReturnPass";
			return error_message;
		}
		if (gender == null) {
			error_message = "GenderA";
			return error_message;
		}

		c = dbConnection(user, conPass);

		try {
			String query = "select * from studentInfo where email=?";

			PreparedStatement pst = c.prepareStatement(query);

			pst.setString(1, email);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				error_message = "Existing User";

				return error_message;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(pass.equals(rePass))) {
			error_message = "PassNoMatch";
			return error_message;
		}

		try {
			c = dbConnection(user, conPass);

			PreparedStatement pst = c.prepareStatement("insert into studentInfo values(?,?,?,?,?)");

			pst.setString(1, name);

			pst.setString(2, email);

			pst.setString(3, pass);

			pst.setString(4, rePass);

			pst.setString(5, gender);

			ResultSet rs = null;

			int row = pst.executeUpdate();
			System.out.println("Row affected" + row);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return error_message;

	}

	public int loginVerify(String username, String password, String user, String conPass) {

		int flag = 0;
		ResultSet rs = null;
		try {

			c = dbConnection(user, conPass);

			String query = "select * from studentInfo where Email=?";

			PreparedStatement pst = c.prepareStatement(query);

			pst.setString(1, username);

			rs = pst.executeQuery();

			if (rs.next()) {
				flag = 1;

				pst = c.prepareStatement("select * from studentInfo where Email=? and Password=?");
				pst.setString(1, username);

				pst.setString(2, password);

				rs = pst.executeQuery();

				if (rs.next()) {

					flag = 2;
					// RetriveName(username);
				}

			}

		} catch (Exception e) {

		}
		return flag;
	}
//		public int RetriveName(String username) {
//			
//			//retrive name from dataBase
//			int flag=0;
//			ResultSet rs=null;
//			try {
//				String query="select name from studentInfo where Email=?";
//				PreparedStatement pst=c.prepareStatement(query);
//				pst.setString(1, username);
//				rs=pst.executeQuery();
//				if(rs.next()) {
//					flag=1;
//					String name=rs.getString("Name");
//					System.out.println("Name is "+name);
//				
//				}
//				
//				
//			}
//			catch(Exception e) {
//				
//			}
//			return flag;
//		}

}
