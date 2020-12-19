import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

@WebServlet("/SalesmanHome")


public class SalesmanHome extends HttpServlet
{
    private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displaySalesmanHome(request, response, pw, "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
		String name = request.getParameter("fullname");

        String customerName = request.getParameter("customerName");
        String itemName = request.getParameter("itemName");
        String itemCatalog = request.getParameter("itemCatalog");
        String creditCardNo = request.getParameter("creditCardNo");
        String customerAddress = request.getParameter("customerAddress");


        HashMap<String, UserHomeHub> hm=new HashMap<String, UserHomeHub>();
		

        try 
		{
            hm = MySqlDataStoreUtilitiesHomeHub.selectUser();
        } 
		catch (Exception e) 
		{

        }

        if (request.getParameter("customer") != null && request.getParameter("customer").equals("Create Customer"))
		{
            if (!password.equals(repassword)) 
			{
                error_msg = "Passwords doesn't match!";
                displaySalesmanHome(request, response, pw, "customer");
            } 
			else 
			{

                if (hm.containsKey(username))
				{
                    error_msg = "Username already exist.";
                    displaySalesmanHome(request, response, pw, "customer");
                }
				else
				{	MySqlDataStoreUtilitiesHomeHub.insertUser(name,username, password, "customer");
                    
                    HttpSession session = request.getSession(true);
                    session.setAttribute("login_msg", "The customer account created successfully.");

                    error_msg = "The customer account created successfully.";
                    displaySalesmanHome(request, response, pw, "customer");
                }

            }
        } 
    }

    protected void displaySalesmanHome(HttpServletRequest request, HttpServletResponse response, PrintWriter pw, String flag) throws ServletException, IOException 
	{
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title'>");
        pw.print("Create New Customer");
        pw.print("</h3>");
        pw.print("<div class='entry'>");

        if (flag.equals("customer"))
            pw.print("<h4 style='color:red'>" + error_msg + "</h4>");

        pw.print("<form action='SalesmanHome' method='post'>");
        pw.print("<table style='width:100%'><tr><td>");
		pw.print("<h4>FullName</h4></td><td><input type='text' name='fullname' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Username</h4></td><td><input type='text' name='username' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Password</h4></td><td><input type='password' name='password' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<h4>Re-Password</h4></td><td><input type='password' name='repassword' value='' class='input' required></input>");
        pw.print("</td></tr><tr><td>");
        pw.print("<input type='submit' class='btnbuy' value='Create Customer' name='customer' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>");
        pw.print("</td></tr><tr><td></td><td>");
        pw.print("</td></tr></table>");
        pw.print("</form></div></div>");

        pw.print("<div class='post'>");
        pw.print("<h3 class='title'>");
		
		HashMap<String, UserHomeHub> hm=new HashMap<String, UserHomeHub>();
		

        try 
		{
            hm = MySqlDataStoreUtilitiesHomeHub.selectUser();
        } 
		catch (Exception e) 
		{

        }
		pw.print("<div class='post'>");
        pw.print("<h2 class='title meta'>");
        pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>View Orders</a>");
        pw.print("</h2><div class='entry'>");

        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td>Order Id:</td>");
        pw.print("<td>Username:</td>");
        pw.print("<td>Product Name:</td>");
        pw.print("<td>Price:</td></td>");
        pw.print("</tr>");
		try 
		{
		for (Map.Entry me : hm.entrySet())
		{
			HashMap<String, String> userval = new HashMap<String, String>();
			System.out.println("customerid: "+me.getKey().toString());
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(me.getKey().toString());
			int counter=0;
			int services=0;
			ResultSet rs=MySqlDataStoreUtilitiesHomeHub.getserviceOrders(userval.get("customer_id"));
			while(rs.next())
			{
				String apt_no="";
				String street_number="";
				String street_name="";
				String city="";
				String state="";
				String zip="";
				String address_id="";	
				String card_number="";
			
				if(userval.get("address1")!=null)
				{
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
				
				pw.print("<form method='post' action='RemoveCustomerOrder'>");
                pw.print("<tr>");
                pw.print("<td>" + rs.getString("order_id") + "</td>" +
                        "<td>" + me.getKey() + "</td>" +
                        "<td>" + rs.getString("servicename") + "</td>" +
                        "<td>" + rs.getString("price") + "</td>" +
                        "<td>" + apt_no + ", " + street_number + ", " + street_name + ", " + state + ", " + city + ", " + zip + "</td>" +
                        "<td>" + userval.get("card_number1") + "</td>");

                pw.print("<input type='hidden' name='orderName' value='" + rs.getString("servicename") + "'>");
				 pw.print("<input type='hidden' name='id' value='" + rs.getString("id") + "'>");
                pw.print("<input type='hidden' name='orderId' value='" + rs.getString("order_id") + "'>");
                pw.print("<input type='hidden' name='username' value='" + me.getKey() + "'>");
                pw.print("<input type='hidden' name='productName' value='" + rs.getString("servicename") + "'>");
                pw.print("<input type='hidden' name='price' value='" + rs.getString("price") + "'>");
                pw.print("<input type='hidden' name='address' value='" + apt_no + ", " + street_number + ", " + street_name + ", " + state + ", " + city + ", " + zip + "'>");
                pw.print("<input type='hidden' name='creditCard' value='" + userval.get("card_number1") + "'>");
                pw.print("<input type='hidden' name='userType' value='Salesman'>");
                pw.print("<td><input type='submit' name='Order' value='Cancel' class='btnbuy'></td>");
                pw.print("</tr>");
                pw.print("</form>");
				
			}
			
			
			
		}
		pw.print("</table>");
        pw.print("</h2></div></div></div>");
		} 
		catch (Exception e)
		{
			System.out.println(e);
        }
    }
}
