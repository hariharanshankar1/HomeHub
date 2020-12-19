import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/CartPage")

public class CartPage extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		Utilities utility = new Utilities(request, pw);
		String name = request.getParameter("name");
		String id = request.getParameter("id");
		String price = request.getParameter("price");
		String type = request.getParameter("type");
		String access = request.getParameter("access");
		if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login");
				response.sendRedirect("Login");
				return;
			}
			
		HttpSession session=request.getSession();
		UserHomeHub user=utility.getUser();
		String username = user.getName();
		
		
		System.out.println(id);
		System.out.println(username);
		System.out.println(type);
		System.out.println(name);
		System.out.println(price);
		
		
			
		if(MySqlDataStoreUtilitiesHomeHub.updatecart(username,id,name,price,type)){
		//response.sendRedirect("ProductListCustomer");
		System.out.println("Updated Cart");
		displayCart(request, response);
			
			
		}
		
	
	}


	protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");							   
		PrintWriter out = response.getWriter();  
        
		
		Utilities utility = new Utilities(request, out);
		Carousel carousel = new Carousel();
		
		if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("login_msg", "Please Login");
				response.sendRedirect("Login");
			return;
			}
		
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		
		
		
		try
		{	
			UserHomeHub user=utility.getUser();
			String username = user.getName();
			HashMap<String, String> userval = new HashMap<String, String>();
			
			
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(username);
			String customer_id=userval.get("customer_id");

			
			List<CartProd> cart = MySqlDataStoreUtilitiesHomeHub.getAllCartItems(username);
			out.print("<div id='content'><div class='post'><h2 class='title meta'>");
			out.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Cart("+cart.size()+")</a>");
			out.print("</h2><div class='entry'>");

		if(cart.size() > 0)
			
		{
			out.println("<table border=1 width=60% height=50%>");  
			out.println("<tr><th>Number_of_itmes</th><th>Product_Name</th><th>Quantity</th><th>price_per_unit</th><th>Remove</th><tr>");
			int i = 1;
			double total = 0;
			
			for (CartProd cp:cart)
			{
				out.print("<form name ='CartForm' action='RemoveFromCart' method='get'>");
				out.println("<input type='hidden' id='custId' name='customer_id' value='"+customer_id+"'>");
				out.print("<tr>");
				out.print("<td>"+i+"</td><td>"+cp.getproduct_name()+"</td><td> "+cp.getquantity()+"</td><td> "+cp.getprice_per_unit()+"</td>");
				out.print("<td><input type='submit' name='RemoveCart' value='Remove' class='btnbuy' /></td>");
				out.print("<input type='hidden' name='id' value='"+cp.getproduct_id()+"'>");
				out.print("<input type='hidden' name='orderPrice' value='"+cp.getprice_per_unit()+"'>");
				out.print("</tr>");
				out.print("</form>");
				total = total +(cp.getprice_per_unit()*cp.getquantity());
				i++;
			}
			out.print("<form action='CheckOut' method='post'>");    
			out.print("<input type='hidden' name='orderTotal' value='"+total+"'>");
			out.print("<tr><th></th><th>Total</th><th></th><th>"+total+"</th>");
			out.print("<tr><td></td><td></td><td><input type='submit' name='CheckOut' value='CheckOut' class='btnbuy' /></td>");
			out.print("</table></form>");
			
			out.print(carousel.carouselfeature(utility));
		}
		else
		{
			out.print("<h4 style='color:red'>Your Cart is empty</h4>");
		}
		
		utility.printHtml("Footer.html");}
		catch (Exception e) 
		{  
			out.println("error");  
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		displayCart(request, response);
	}




}
