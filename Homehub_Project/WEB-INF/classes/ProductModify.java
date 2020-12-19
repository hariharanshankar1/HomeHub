import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
							
		if(action.equals("AddService"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Add Product</a></h2>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"	
					+"<h3>Service Type</h3></td><td><select name='producttype' class='input'><option value='Home Cleaning' selected>Home Cleaning</option><option value='Solar Panel Cleaning'>Solar Panel Cleaning</option><option value='Air Duct Cleaning'>Air Duct Cleaning</option><option value='Carpet Cleaning'>Carpet Cleaning</option><option value='Pressure Washers'>Pressure Washers</option><option value='pool & Hot Tub Services'>Pool & Hot Tub Services</option><option value='Swimming Pools'>Swimming Pools</option><option value='Pool Cleaners'>Pool Cleaners</option><option value='Cabinetry'>Cabinetry</option><option value='Carpenters'>Carpenters</option><option value='Carpeting'>Carpeting</option><option value='Fences & Gates'>Fences & Gates</option><option value='Flooring'>Flooring</option><option value='Foundation Repair'>Foundation Repair</option><option value='Furniture Assembly'>Furniture Assembly</option><option value='Garbage Door Services'>Garbage Door Services</option><option value='Refinishing Services'>Refinishing Services</option><option value='Heating & Air Conditioning/HVAC'>Heating & Air Conditioning/HVAC</option><option value='Home Network Installation'>Home Network Installation</option><option value='Home Theatre Installation'>Home Theatre Installation</option><option value='Television Service Providers'>Television Service Providers</option><option value='Internet Service Providers'>Internet Service Providers</option><option value='Insulation Installation'>Insulation Installation</option><option value='Security Systems'>Security Systems</option><option value='Electricity Suppliers'>Electricity Suppliers</option><option value='Natural Gas Suppliers'>Natural Gas Suppliers</option><option value='Water Suppliers'>Water Suppliers</option><option value='Packing Services'>Packing Services</option><option value='Movers'>Movers</option><option value='Home Window Tinting'>Home Window Tinting</option><option value='Glass & Mirrors'>Glass & Mirrors</option><option value='Shades & Blinds'>Shades & Blinds</option><option value='Roof Inspectors'>Roof Inspectors</option><option value='Roofing'>Roofing</option><option value='Gardeners'>Gardeners</option><option value='Plumbing'>Plumbing</option><option value='Painters'>Painters</option><option value='Mobile Home Repair'>Mobile Home Repair</option><option value='Holiday Decorating Services'>Holiday Decorating Services</option><option value='Keys & Locksmiths'>Keys & Locksmiths</option><option value='Handyman'>Handyman</option><option value='Interior Design'>Interior Design</option></select>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service address</h3></td><td><input type='text' step='any' name='address' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service city</h3></td><td><input type='text' step='any' name='city' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service state</h3></td><td><input type='text' step='any' name='state' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service zipcode</h3></td><td><input type='text' step='any' name='zipcode' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service phone</h3></td><td><input type='text' step='any' name='phone' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
					
					
					
					
					
		}
		else if (action.equals("UpdateService"))
		{
		    pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Update Service</a></h2>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+"<h3>Service Type</h3></td><td><select name='producttype' class='input'><option value='Home Cleaning' selected>Home Cleaning</option><option value='Solar Panel Cleaning'>Solar Panel Cleaning</option><option value='Air Duct Cleaning'>Air Duct Cleaning</option><option value='Carpet Cleaning'>Carpet Cleaning</option><option value='Pressure Washers'>Pressure Washers</option><option value='pool & Hot Tub Services'>Pool & Hot Tub Services</option><option value='Swimming Pools'>Swimming Pools</option><option value='Pool Cleaners'>Pool Cleaners</option><option value='Cabinetry'>Cabinetry</option><option value='Carpenters'>Carpenters</option><option value='Carpeting'>Carpeting</option><option value='Fences & Gates'>Fences & Gates</option><option value='Flooring'>Flooring</option><option value='Foundation Repair'>Foundation Repair</option><option value='Furniture Assembly'>Furniture Assembly</option><option value='Garbage Door Services'>Garbage Door Services</option><option value='Refinishing Services'>Refinishing Services</option><option value='Heating & Air Conditioning/HVAC'>Heating & Air Conditioning/HVAC</option><option value='Home Network Installation'>Home Network Installation</option><option value='Home Theatre Installation'>Home Theatre Installation</option><option value='Television Service Providers'>Television Service Providers</option><option value='Internet Service Providers'>Internet Service Providers</option><option value='Insulation Installation'>Insulation Installation</option><option value='Security Systems'>Security Systems</option><option value='Electricity Suppliers'>Electricity Suppliers</option><option value='Natural Gas Suppliers'>Natural Gas Suppliers</option><option value='Water Suppliers'>Water Suppliers</option><option value='Packing Services'>Packing Services</option><option value='Movers'>Movers</option><option value='Home Window Tinting'>Home Window Tinting</option><option value='Glass & Mirrors'>Glass & Mirrors</option><option value='Shades & Blinds'>Shades & Blinds</option><option value='Roof Inspectors'>Roof Inspectors</option><option value='Roofing'>Roofing</option><option value='Gardeners'>Gardeners</option><option value='Plumbing'>Plumbing</option><option value='Painters'>Painters</option><option value='Mobile Home Repair'>Mobile Home Repair</option><option value='Holiday Decorating Services'>Holiday Decorating Services</option><option value='Keys & Locksmiths'>Keys & Locksmiths</option><option value='Handyman'>Handyman</option><option value='Interior Design'>Interior Design</option></select>"
					+ "</td></tr><tr><td>"
					+ "<h3>Serviec Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Price</h3></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service Discount</h3></td><td><input type='number' step='any' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service address</h3></td><td><input type='text' step='any' name='address' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service city</h3></td><td><input type='text' step='any'  name='city' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service state</h3></td><td><input type='text' step='any'  name='state' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service zipcode</h3></td><td><input type='text' step='any'  name='zipcode' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Service phone</h3></td><td><input type='text' step='any'  name='phone' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"

					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else if (action.equals("DeleteService"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Delete Service</a></h2>"
					+ "<div class='entry'>");

			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>Service Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		else
		{
			
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
	}
}
