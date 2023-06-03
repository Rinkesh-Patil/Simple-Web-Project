

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Servlet implementation class RegisterServ
 */
public class RegisterServ extends HttpServlet {
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
//	private Connection con;
//	@Resource(lookup="java:/comp/env/mypool")
//	private DataSource ds;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			//name,address,email,login and password.

			response.setContentType("text/html");
			PrintWriter pw=response.getWriter();
			String name=request.getParameter("name");
			String address=request.getParameter("address");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			PreparedStatement pst=con.prepareStatement("insert into register values(?,?,?,?,?)");
			pst.setString(1,name);
			pst.setString(2, address);
			pst.setString(3, email);
			pst.setString(4,login);
			pst.setString(5, password);
			int k=pst.executeUpdate();
			if(k>0)
			{
				pw.println("Record has been added");
			}
			else
			{
				pw.println("cannot add");
			}
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}	

}
