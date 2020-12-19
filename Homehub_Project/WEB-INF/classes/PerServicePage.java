import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PerServicePage")

public class PerServicePage extends HttpServlet 
{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		// String serviceId = request.getParameter("serviceid");
		String servicename = request.getParameter("servicename");
		
		HashMap<String, String> oneService = new HashMap<String, String>();

		try
		{
			oneService = MySqlDataStoreUtilitiesHomeHub.getServiceUsingServicename(servicename);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		
		pw.print("<script src='https://maps.googleapis.com/maps/api/js?key=XXXX&callback=initMap&libraries=&v=weekly' defer></script>");
		pw.print("<script>  function initMap() {var lat = parseFloat(document.getElementById('lat').innerHTML);");
		pw.print("var lng = parseFloat(document.getElementById('lon').innerHTML);");
		pw.print("  const points = {");
		pw.print("			lat: lat,");
		pw.print("			lng: lng");
		pw.print("		};");
		  // The map, centered at points
		pw.print("  var map = new google.maps.Map(document.getElementById('map'), {");
		pw.print("		center: points,");
		pw.print("		zoom: 13,");
		pw.print("		mapTypeId: 'roadmap'");
		pw.print("	});");
		  // The marker, positioned at points
		pw.print("  const marker = new google.maps.Marker({");
		pw.print("	position: points,");
		pw.print("	map: map,");
		pw.print("  });}</script>");
		
		pw.print("<div id='content'><div class='post' style = 'margin-top:60px'><h2 class='title meta'>");
		pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:28px'>" + oneService.get("servicename") + "</a>");
		pw.print("</h2><div class='entry'>");
		// pw.print("<div id='shop_item'>");
		pw.print("<h3>Address : " + oneService.get("address") + " " + oneService.get("city") + " " + oneService.get("state") + " " + oneService.get("zipcode") + "</h3>");
		pw.print("<h3>Call us at : " + oneService.get("phonenumber") + "</h3>");
		pw.print("<h3>Price : <strong>" + oneService.get("serviceprice") + "$</strong></h3><ul>");
		pw.print("<table><tr><td><img src='" + oneService.get("serviceimage") + "' alt='' width='500' height='300' /></td>");
		pw.print("<td><a id ='lat' style='color: transparent;'>" + oneService.get("latitude") + "</a><br/>");
		pw.print("<a id ='lon' style='color: transparent;'>" + oneService.get("longitude") + "</a>");
		pw.print("<div id='map'style='width : 100%'></div></td></table>");
		//buttons
		pw.print("<div style='float: right;'><center><form method='post' action='CartPage'>" + 
				"<input type='hidden' name='name' value='"+oneService.get("servicename")+"'>"+
				"<input type='hidden' name='price' value='"+oneService.get("serviceprice")+"'>"+
				"<input type='hidden' name='id' value='"+oneService.get("id")+"'>"+
				"<input type='hidden' name='type' value='services'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' class='btnbuy' value='Book Now'></form>");
		pw.print("<form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+oneService.get("servicename")+"'>"+
				"<input type='hidden' name='type' value='services'>"+
				"<input type='hidden' name='zip' value='"+oneService.get("zipcode")+"'>"+
				"<input type='hidden' name='city' value='"+oneService.get("city")+"'>"+
				"<input type='hidden' name='maker' value='"+oneService.get("servicetype")+"'>"+
				"<input type='hidden' name='price' value='"+oneService.get("serviceprice")+"'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' value='WriteReview' class='btnreview'></form>");
		pw.print("<form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+oneService.get("servicename")+"'>"+
				"<input type='hidden' name='type' value='services'>"+
				"<input type='hidden' name='access' value=''>"+
				"<input type='submit' value='ViewReview' class='btnreview'></form></center>");
		pw.print("</ul></div>");

		
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");
	}
}
