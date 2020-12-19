import java.io.IOException;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DealMatchesUtilities")

public class DealMatchesUtilities extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

			HashMap<String, Services> selectedservices=new HashMap<String, Services>();
		try
			{

		pw.print("<div id='content'>");
		pw.print("<div class='post'>");
		pw.print("<h2 class='title'>");
		pw.print("<a href='#'>Welcome to HOME HUB <br/></a></h2>");
		pw.print("<h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'> <br/><marquee direction='left'> Tired of calling management office for repair service? Try with our providers who work 24*7.</marquee></a>");
		pw.print("</h2>");
		pw.print("<input id='pac-input' class='controls' type='text' placeholder='Search Box' style='width:60px, height:60px'/>");
		pw.print("<div id='map'></div>");
		pw.print("<div class='entry'>");
		pw.print("<img src='images/deal.jpg'style='width: 300px; display: block; margin-left: auto; margin-right: auto' />");
		pw.print("<h1>We beat our competitors in all aspects. Price-Match Guaranteed</h2>");
		

			String line=null;
			String TOMCAT_HOME = System.getProperty("catalina.home");

			HashMap<String,Services> servicemap = MySqlDataStoreUtilitiesHomeHub.getAllServicesDeals();

			for(Map.Entry<String, Services> entry : servicemap.entrySet())
			{
			if(selectedservices.size()<2 && !selectedservices.containsKey(entry.getKey()))
			{


			BufferedReader reader = new BufferedReader(new FileReader (new File(TOMCAT_HOME+"\\webapps\\Homehub_Project\\DealMatches.txt")));
			line=reader.readLine().toLowerCase();

			
			if(line == null)
			{
				pw.print("<h2 align='center'>No Offers Found</h2>");
				break;
			}
			else
			{
			do {

				  if(line.contains(entry.getValue().getServicename()))
				  {

					pw.print("<h2>"+line+"</h2>");
					pw.print("<br>");
					selectedservices.put(entry.getKey(),entry.getValue());
					break;
				  }

			    }while((line = reader.readLine()) != null);

			 }
			 }
			}
			}
			catch(Exception e)
			{
			pw.print("<h2 align='center'>No Offers Found</h2>");
			}
		pw.print("</div>");
		pw.print("</div>");
		pw.print("<div class='post'>");
		pw.print("<div class='entry'>");
		if(selectedservices.size()==0)
		{
		pw.print("<h2 align='center'>No Deals Found</h2>");
		}
		else
		{
		pw.print("<table id='bestseller'>");
		pw.print("<tr>");
		for(Map.Entry<String, Services> entry : selectedservices.entrySet()){
			pw.print("<td><div id='shop_item'><h3><a href = 'PerServicePage?servicename=" + entry.getValue().getServicename() + "' style = 'color:#650D88; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold;'>"+entry.getValue().getServicename()+"</a></h3>");
			pw.print("<strong>"+entry.getValue().getServiceprice()+"$</strong>");
			pw.print("<ul>");
			pw.print("<li id='item'><a href = 'PerServicePage?servicename=" + entry.getValue().getServicename() + "'><img src='" + entry.getValue().getServiceimage() + "' alt='' /></a>");
			pw.print("</li><li>");
			pw.print("<form action='CartPage' method='post'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getValue().getServicename()+"'>");
			pw.print("<input type='hidden' name='type' value='services'>");
			pw.print("<input type='hidden' name='price' value='"+entry.getValue().getServiceprice()+"'>");
			pw.print("<input type='hidden' name='id' value='"+entry.getValue().getId()+"'>");
			pw.print("<input type='hidden' name='access' value=''>");
			pw.print("<input type='submit' class='btnbuy' value='Book Now'>");
			pw.print("</form></li><li>");
			pw.print("<form action='WriteReview' method='post'><input type='submit' class='btnreview' value='WriteReview'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getValue().getServicename()+"'>");
			pw.print("<input type='hidden' name='type' value='services'>");
			pw.print("<input type='hidden' name='maker' value='"+entry.getValue().getServicetype()+"'>");
			pw.print("<input type='hidden' name='price' value='"+entry.getValue().getServiceprice()+"'>");
			pw.print("<input type='hidden' name='zip' value='"+entry.getValue().getZipcode()+"'>");
			pw.print("<input type='hidden' name='city' value='"+entry.getValue().getCity()+"'>");
			pw.print("</form></li>");
			pw.print("<li>");
			pw.print("<form action='ViewReview' method='post'><input type='submit' class='btnreview' value='ViewReview'>");
			pw.print("<input type='hidden' name='name' value='"+entry.getValue().getServicename()+"'>");
			pw.print("<input type='hidden' name='type' value='"+entry.getValue().getServicetype()+"'>");
			pw.print("</form></li></ul></div></td>");
		}
		pw.print("</tr></table>");
		}
		pw.print("</div></div></div>");

	}
}
