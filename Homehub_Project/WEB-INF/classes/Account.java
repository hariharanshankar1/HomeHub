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

@WebServlet("/Account")

public class Account extends HttpServlet 
{
	private String error_msg;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String apt_no=request.getParameter("apt_no");
		String street_number=request.getParameter("street_number");
		String street_name=request.getParameter("street_name");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zip=request.getParameter("zip");
		
		String address_id=request.getParameter("address_id");
		
		String card_number=request.getParameter("card_number");
		
		
		String customer_id=request.getParameter("customer_id");
		
		System.out.println("Inside post");
		String usertype = "customer";
		double age= Double.parseDouble(request.getParameter("age"));
		System.out.println("after parse");
		
		
		
		
		if (MySqlDataStoreUtilitiesHomeHub.updateUser(name, username, password, age)) 
				{
					if (MySqlDataStoreUtilitiesHomeHub.updateaddress(apt_no,street_number,street_name,city,state,zip,address_id,customer_id,card_number))
					{
						response.sendRedirect("Account");
                        return;
					}
					
                       
                }
				response.sendRedirect("Account");
				
	}


	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
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
			pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			
			pw.print("<table class='gridtable'>");
			// pw.print("<tr>");
			// pw.print("<td> UserName: </td>");
			// pw.print("<td>" +user.getName()+ "</td>");
			// pw.print("</tr>");
			// pw.print("<tr>");
			// pw.print("<td> User Type: </td>");
			// pw.print("<td>" +user.getUsertype()+ "</td>");
			// pw.print("</tr>");
			HashMap<String, String> userval = new HashMap<String, String>();
			
			
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(user.getName());
			
			
			
			String customer_id="";
			String apt_no="";
			String street_number="";
			String street_name="";
			String city="";
			String state="";
			String zip="";
			
			String address_id="";
			
			String card_number="";
			
			
			
			if(userval.get("address1")!=null){
			
				String[] address_split = userval.get("address1").split(",");
				address_id=address_split[0];
				apt_no=address_split[1];
				street_number=address_split[2];
				street_name=address_split[3];
				city=address_split[4];
				state=address_split[5];
				zip=address_split[6];
				card_number=userval.get("card_number1");
			
			}
			
			
			
			
			
			
			pw.print("<form method='post' action='Account'><fieldset>"
				+ "<table style='width:100%; height:100%'><tr class='border_bottom'><td>"
				+ "<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Username</h3></td><td><input type='text' name='username' value='"+user.getName()+"'class='input' readonly='readonly'></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>UserType</h3></td><td><input type='text' name='usertype' value='"+user.getUsertype()+"'class='input' readonly='readonly'></input>"
				+ "</td></tr><tr><td>"
				+"<input type='hidden' id='custId' name='customer_id' value='"+userval.get("customer_id")+"'>"
				+ "</td></tr><tr><td>"
				+ "<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Name</h3></td><td><input type='text' name='name' value='"+userval.get("name")+"' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Password</h3></td><td><input type='text' name='password' value='"+userval.get("password")+"' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Age</h3></td><td><input type='double' name='age' value='"+userval.get("age")+"' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Balance</h3></td><td><input type='double' name='balance' value='"+userval.get("outstanding_balance")+"' class='input' readonly='readonly'></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Home Address</h3></td><td>"
				+ "</td></tr><tr><td>"
				+"<input type='hidden' id='custId' name='address_id' value='"+address_id+"'>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Apartment No</h3></td><td><input type='text' name='apt_no' value='"+apt_no+"'class='input' ></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Street Number</h3></td><td><input type='text' name='street_number' value='"+street_number+"'class='input' ></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Street Name</h3></td><td><input type='text' name='street_name' value='"+street_name+"'class='input' ></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>City</h3></td><td><input type='text' name='city' value='"+city+"'class='input' ></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>State</h3></td><td><input type='text' name='state' value='"+state+"'class='input' ></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Zip</h3></td><td><input type='text' name='zip' value='"+zip+"'class='input' ></input>"
				+ "</td></tr><tr><td>"
				+"<h3 style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>Credit Card Number</h3></td><td><input type='text' name='card_number' value='"+card_number+"'class='input' ></input>"
				+ "</td></tr><tr><td><br>"
				+ "<input type='submit' class='btnbuy' value='Update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td>"
				+ "</tr></table>"
				+ "</form>" + "</div></div></div></div></center><br><br><br>");
			pw.print("</table>");
			pw.print("</h2></div></div></div>");
			
			
			
			
			// int counter=0;
			// ResultSet rs=PostgresqlDataUtilities.getOrders(userval.get("customer_id"));
					
			// while(!rs.next()){
				// pw.println("<p>No Orders Placed</p>");
			// }
			// do
			// {
				// if (counter==0){
				// pw.println("<center><div><table border=1 width=60% height=50%>");  
				// pw.println("<tr><th>order id</th><th>product_id</th><th>quantity</th><th>price_per_unit</th><tr>");
				// counter=counter+1;
				// }
				// pw.println("<tr><td>" + rs.getString("order_id") + "</td><td>" + rs.getString("product_id") + "</td><td>" + rs.getString("quantity") + "</td><td>" + rs.getString("price_per_unit") + "</td></tr>");
			// }while(rs.next());
			
			// if (counter!=0){
				// pw.println("</table></div></center>"); 
				// pw.println("<br><br>"); 
			
			// }
			
		}
		catch(Exception e)
		{
		}
	}
}
