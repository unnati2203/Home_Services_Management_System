package com.servlet.get;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import com.mysql.cj.protocol.Resultset;*/

@WebServlet("/wloginservlet")
public class wloginservlet extends HttpServlet{

protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

   try
   {
     res.setContentType("text/html");
     String name=req.getParameter("name");
     String password=req.getParameter("password");
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql:///homeowner", "root", "vaishnavi09");
     PreparedStatement ps = con.prepareStatement("SELECT * FROM worker where name=? and password=?");
     ps.setString(1, name);
     ps.setString(2, password);
     ResultSet rs= ps.executeQuery();
     PrintWriter pw = res.getWriter();
     pw.println("<h1><center><b><u>WELCOME</u></b></center></h1>");
     pw.println("<html><body><center><table border='1'><tr><td>ID</td><td>FULL_NAME</td><td>CONTACT</td><td>PASSWORD</td></tr>");
     while (rs.next())
     {
    pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td></tr>") ;
     }
     pw.println("</table></center></body></html>");
     
     
   }
   catch (ClassNotFoundException e) {
     e.printStackTrace();
   } catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
   
 }

}