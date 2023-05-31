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

@WebServlet("/hiredservlet")
public class hiredservlet extends HttpServlet{

protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

   try
   {
     res.setContentType("text/html");
     String id=req.getParameter("id");
   
     Class.forName("com.mysql.cj.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql:///homeowner", "root", "vaishnavi09");
     PreparedStatement ps = con.prepareStatement("select workid,name,contact from worker where workid in(select wkid from hiring where cuid=?);");
     ps.setString(1, id);
     
     ResultSet rs= ps.executeQuery();
     PrintWriter pw = res.getWriter();
     pw.println("<h1><center><b><u>DETAILS OF WORKERS WORKING UNDER YOU</u></b></center></h1>");
     pw.println("<html><body><center><table border='1'><tr><td>ID</td><td>WORKID</td><td>NAME</td><td>CONTACT</td></tr>");
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