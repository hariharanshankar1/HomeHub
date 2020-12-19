import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/DisplayOrders")

public class DisplayOrders extends HttpServlet 
{
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		openDisplayOrders(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		

	}


	protected void openDisplayOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		try
         {
			response.setContentType("text/html");
			
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login");
				response.sendRedirect("Login");
				return;
			}
			
			HttpSession session=request.getSession();
			UserHomeHub user=utility.getUser();
			utility.printHtml("Header.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Orders</a>");
			pw.print("</h2><div class='entry'>");
			
			
			HashMap<String, String> userval = new HashMap<String, String>();
			
			
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(user.getName());
			
			
			
			int counter=0;
			int services=0;
			ResultSet rs=MySqlDataStoreUtilitiesHomeHub.getserviceOrders(userval.get("customer_id"));
			pw.println("<h4>Service Orders</h4>"); 
					
			while(!rs.next()){
				break;
			}
			do
			{	
				if (counter==0){
				pw.println("<center><div><table border=1 width=60% height=50%>");  
				pw.println("<tr><th>order id</th><th>Service_Name</th><th>Price</th><th>Cancel</th></tr>");
				counter=counter+1;
				}
				pw.print("<form method='post' action='RemoveCustomerOrder'>");
				pw.println("<tr><td>" + rs.getString("order_id") + "</td><td>" + rs.getString("servicename") + "</td><td>" + rs.getString("price") + "</td>");
				pw.print("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
                pw.print("<input type='hidden' name='orderId' value='" + rs.getString("order_id") + "'>");
				pw.print("<input type='hidden' name='serviceid' value='" + rs.getString("service_id") + "'>");
                pw.print("<td><input type='submit' name='Order' value='Cancel' class='btnbuy'></td>");
                pw.print("</tr>");
                pw.print("</form>");
				
				
			}while(rs.next());
			
			if (counter!=0){
				pw.println("</table></div></center>"); 
				pw.println("<br><br><br><br>"); 
			
			}
			
			// int counter2=0;
			// int products=0;
			// rs=MySqlDataStoreUtilitiesHomeHub.getproductOrders(userval.get("customer_id"));
					
			// while(!rs.next()){
				// products=1;
				// System.out.println("Test1");
				// break;
			// }
			// do
			// {	pw.println("<h4>Product Orders</h4>");  
				// System.out.println("Test2");
				// if (counter2==0){
				// pw.println("<center><div><table border=1 width=60% height=50%>");  
				// pw.println("<tr><th>order id</th><th>Product_Name</th><th>Quantity</th><th>Price</th><th>Cancel</th></tr>");
				// counter2=counter2+1;
				// }
				// pw.print("<form method='post' action='RemoveUpdateOrder'>");
				// pw.println("<tr><td>" + rs.getString("order_id") + "</td><td>" + rs.getString("product_name") + "</td><td>"+ rs.getString("quantity") + "</td><td>" + rs.getString("price_per_unit") + "</td>");
				// pw.print("<input type='hidden' name='id' value='" + rs.getInt("id") + "'>");
                // pw.print("<input type='hidden' name='orderId' value='" + rs.getString("order_id") + "'>");
				// pw.print("<input type='hidden' name='product_id' value='" + rs.getString("product_id") + "'>");
                // pw.print("<td><input type='submit' name='Order' value='Cancel' class='btnbuy'></td>");
                // pw.print("</tr>");
                // pw.print("</form>");
				
				
			// }while(rs.next());
			
			// if (counter2!=0){
				// pw.println("</table></div></center>"); 
				// pw.println("<br><br><br>"); 
			
			// }
			// if(services==1 & products==1){
				// pw.println("<h3>No Orders Placed<h3>");
			// }
			
			
		}
		catch(Exception e)
		{
		}
	}
}
