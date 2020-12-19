import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@WebServlet("/SortServicesDescProducts")

public class SortServicesDescProducts extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String serviceType = request.getParameter("servicetype");
		
		HashMap<String, Services> allServicesSorted = new HashMap<String, Services>();

		try
		{
			allServicesSorted = MySqlDataStoreUtilitiesHomeHub.getAllServicesSorted(serviceType);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		TreeMap<String, Services> hm = new TreeMap<String, Services>(String.CASE_INSENSITIVE_ORDER);
		
		hm.putAll(allServicesSorted);

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post' style = 'margin-top:60px'><table><tr><td><h2 class='title meta'>");
		pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:28px'>" + serviceType.replaceAll("'","") + "</a></td>");
		pw.print("<td style='width:275px' align='right'>");
		
		pw.print("<li class='dropdown'  style = 'list-style-type:none; color:#650D88; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:14px'><a class='dropdown-toggle' type='button' id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' >Click here to sort</span></span></a>");
		pw.print("<ul class='dropdown-menu' aria-labelledby='dropdownMenuLink'>");
		pw.print("<li><a href = 'SortServicesAscProducts?servicetype=" + serviceType.replaceAll("'","") + "'  style = 'color:#650D88; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:14px'>Alphabet: A to Z</a></li>");
		pw.print("<li><a href = 'SortServicesDescProducts?servicetype=" + serviceType.replaceAll("'","") + "'  style = 'color:#650D88; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:14px'>Alphabet: Z to A</a></li>");
		pw.print("</ul></li>");
		
		pw.print("</td></tr></table>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		
		int i = 1;
		int size = hm.size();
		
		for (Map.Entry<String, Services> entry : hm.descendingMap().entrySet()) 
		{
			Services services = entry.getValue();
			
			if (i % 5 == 1)
				pw.print("<tr>");
			System.out.println(entry.getKey());
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3><a href = 'PerServicePage?serviceid=" + services.getId() + "' style = 'color:#650D88; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>" + services.getServicename() + "</a></h3>");
			pw.print("<strong>" + services.getServiceprice() + "$</strong><ul>");
			pw.print("<li id='item'><a href = 'PerServicePage?serviceid=" + services.getId() + "'><img src='" + services.getServiceimage() + "' alt='' /></a></li>");
			pw.print("<li><form method='post' action='CartPage'>" + 
					"<input type='hidden' name='name' value='"+services.getServicename()+"'>"+
					"<input type='hidden' name='type' value='services'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+services.getServicename()+"'>"+
					"<input type='hidden' name='type' value='services'>"+
					"<input type='hidden' name='maker' value='"+serviceType+"'>"+
					"<input type='hidden' name='zip' value='"+services.getZipcode()+"'>"+
					"<input type='hidden' name='city' value='"+services.getCity()+"'>"+
					"<input type='hidden' name='price' value='"+services.getServiceprice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+services.getServicename()+"'>"+
					"<input type='hidden' name='type' value='services'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			
			if (i % 5 == 0 || i == size)
				pw.print("</tr>");
			
			i++;
		}
		pw.print("</table></div></div></div>");
		utility.printHtml("Footer.html");
	}
}
