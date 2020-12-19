import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
//import java.time.LocalDate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
// import javax.mail.*;
// import javax.mail.internet.*;
import javax.activation.*;

@WebServlet("/Payment")

public class Payment extends HttpServlet 
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		
		if(!utility.isLoggedin())
		{
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		
		
		HttpSession session=request.getSession();
		UserHomeHub user=utility.getUser();
		String username = user.getName();

		String name = request.getParameter("Name");
		String age = request.getParameter("Age");
		String occupation = request.getParameter("Occupation");
		String totalorder = request.getParameter("orderTotal");
		String street=request.getParameter("Street");
		String city=request.getParameter("City");
		String state=request.getParameter("State");
		String zip=request.getParameter("Zip");
		String creditCardNo=request.getParameter("creditCardNo");
		String deliverytype = request.getParameter("deliverytype");
		String pickupaddress = request.getParameter("pickupaddress");

		String userAddress = street + " " + city + " " + state + " " + zip;
		
		if(!userAddress.isEmpty() && !creditCardNo.isEmpty() )
		{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			Calendar c1 = Calendar.getInstance();
			c.setTime(new Date()); // Now use today date.
			c1.setTime(new Date());
			c.add(Calendar.DATE, 14); // Adding 5 days
			c1.add(Calendar.DATE, 2);
			String output = sdf.format(c.getTime());
			String output1 = sdf.format(c1.getTime());
			String deliverydate=output;
			String temp=output;
			switch (deliverytype)
			{
                   case "Pickup":	   
								   output="The Pickup date is "+output1+" and the pick up location is "+ pickupaddress+ ".";
								   break;
				   case "Delivery":
								   output="The Estimated Arrival date is "+temp+ ".";
								   break;
			}
		
			int Order_id= MySqlDataStoreUtilitiesHomeHub.placeOrder(totalorder,username,userAddress,deliverydate,deliverytype,creditCardNo);
			
			MySqlDataStoreUtilitiesHomeHub.insertTransactions(username,name,age,occupation,creditCardNo,Order_id, deliverytype, zip);


				
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
		
			pw.print("<h2 style='color:#650D88; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 10px;'>Your Order");
			pw.print("&nbsp&nbsp");  
			pw.print("is stored. ");
			pw.print("<br>Thank you for picking Home Hub.<br>");
			pw.print("<br>Order Summary:<br>");

			pw.print("<br>Order Number :"+(Order_id)+ ".");
			pw.print("<br>Customer Name :"+(name)+ ".");
			pw.print("<br>Total Price :"+(totalorder)+ ".");
			pw.print("<br>");
			pw.print(output);
			
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}
		else
		{
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Order</a>");		
			pw.print("</h2><div class='entry'>");	
			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");
		}	
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
	}
}