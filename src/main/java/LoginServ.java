

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServ
 */
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;
	public void init()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/mydb";
			con=DriverManager.getConnection(url,"root","Rudra@9765");
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String log = request.getParameter("login");
	     String pass = request.getParameter("password");
	     try {
	    	 boolean found = false;
			PreparedStatement pst = con.prepareStatement("select * from register where login = ? and password = ?");
			pst.setString(1, log);
			pst.setString(2, pass);
       ResultSet rs = pst.executeQuery();
       while(rs.next()) {
    	   found = true;
       }
       if (found) {
           response.sendRedirect("success.html");
       } else {
           response.sendRedirect("Fail.html");
       }	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}

}
