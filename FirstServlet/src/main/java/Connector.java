import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connector {

	public Connection connection() {
		Connection con = null;
		// String url =
		// "jdbc:sqlserver://localhost\\sqlexpress;AptechData;user=sa;password=sql321";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=AptechData;user=sa;password=sql321";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
			System.out.println("Connection is Succefull");

		} catch (Exception e) {
			System.out.println(e);
		}

		return con;
	}

	public void CheckValidate(String username, String password) {
		if (username.isEmpty() || password.isEmpty()) {
			System.out.println("UserName or Password is Empty");
		}

		try {
			connection();
			Connection con = this.connection();

			String sql = "insert into userData values(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			String Email = username;

			pst.setString(1, Email);
			pst.setString(2, password);
			System.out.println("Insert into values on DataBase");
			int count = pst.executeUpdate();
			if (count > 0) {
				System.out.println("Data Inserted!!!");
			} else {
				System.out.println("Data is not Inserted!!!");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
