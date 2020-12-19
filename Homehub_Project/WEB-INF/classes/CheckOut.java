import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;
import java.util.*;
import java.util.Random;


@WebServlet("/CheckOut")


public class CheckOut extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	    Utilities Utility = new Utilities(request, pw);
		storeOrders(request, response);
	}
	
	protected void storeOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    try
        {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request,pw);
			
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 

			String userName = session.getAttribute("username").toString();
			HashMap<String, String> userval = new HashMap<String, String>();
			
			
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(userName);
			
			String customer_id="";
			String apt_no="";
			String street_number="";
			String street_name="";
			String city="";
			String state="";
			String zip="";
			
			String address_id="";
			String cus_name="";
			String card_number="";
			
			
			
			if(userval.get("address1")!=null)
			{
				cus_name=userval.get("name");
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
			
			String orderTotal = request.getParameter("orderTotal");
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<form name ='CheckOut' action='Payment' method='post'>");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<table  class='gridtable'><tr><td>Customer Name:</td><td>");
			pw.print(userName);
			pw.print("</td></tr>");
			
			List<CartProd> cart = MySqlDataStoreUtilitiesHomeHub.getAllCartItems(userName);
			
			for (CartProd cp:cart) 
			{
				pw.print("<tr><td> Product added for Purchase:</td><td>");
				pw.print(cp.getproduct_name()+"</td></tr><tr><td>");
				pw.print("<input type='hidden' name='orderPrice' value='"+cp.getprice_per_unit()+"'>");
				pw.print("<input type='hidden' name='orderName' value='"+cp.getproduct_name()+"'>");
				pw.print("Product Price:</td><td>"+ cp.getprice_per_unit()*cp.getquantity());
				pw.print("</td></tr>");
			}
			
			pw.print("<tr><td>");
			pw.print("Total Order Cost</td><td>"+orderTotal);
			pw.print("<input type='hidden' name='orderTotal' value='"+orderTotal+"'>");
			pw.print("</td></tr></table><table><tr></tr><tr></tr>");	
			pw.print("<tr><td>");
			pw.print("Credit/accountNo</td>");
			pw.print("<td><input type='text' name='creditCardNo' value='"+card_number+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("Customer Name</td>");
			pw.print("<td><input type='text' name='Name' value='"+cus_name+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("Customer Age</td>");
			pw.print("<td><input type='text' name='Age' value='"+userval.get("age")+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("Customer Occupation</td>");
			pw.print("<td><input type='text' name='Occupation'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("Street</td>");
			pw.print("<td><input type='text' name='Street' value='"+street_number+", "+street_name+", apt "+apt_no+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("City</td>");
			pw.print("<td><input type='text' name='City' value='"+city+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("State</td>");
			pw.print("<td><input type='text' name='State' value='"+state+"'>");
			pw.print("</td></tr>");
			pw.print("<tr><td>");
			pw.print("Zipcode</td>");
			pw.print("<td><input type='text' name='Zip' value='"+zip+"'>");					  
			pw.print("</td></tr>");
			pw.print("<input type='hidden' name='deliverytype' value='Delivery'>");
			pw.print("<tr><td colspan='2'>");
			pw.print("<input type='submit' name='submit' class='btnbuy'>");
			pw.print("</td></tr></table></form>");
			pw.print("</div></div></div>");		
			utility.printHtml("Footer.html");
	    }
        catch(Exception e)
		{
         System.out.println(e.getMessage());
		}  			
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	}
}