

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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Servlet implementation class AdminServ
 */
public class AdminServ extends HttpServlet {
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
		PrintWriter pw = response.getWriter();
	     try {
	    	 boolean found = false;
			PreparedStatement pst = con.prepareStatement("select * from register ");
			 ResultSet rs = pst.executeQuery();
			 ResultSetMetaData ps = rs.getMetaData();
			 int columnCount = ps.getColumnCount();
			 int columnIndex = 1;

			 while (columnIndex <= columnCount) {
			     String columnName = ps.getColumnName(columnIndex);
			     pw.print(columnName+"\t");
			     columnIndex++;
			 }	
			 pw.println();
      while(rs.next()) {
    pw.print(rs.getString(1)+"\t");
   	pw.print(rs.getString(2)+"\t");
   	pw.print(rs.getString(3)+"\t");
   	pw.print(rs.getString(4)+"\t");
   	pw.print(rs.getString(5)+"\t");
   	pw.println();
      }
      

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
