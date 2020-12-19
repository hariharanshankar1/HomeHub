import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@WebServlet("/Inventory")
public class Inventory extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        PrintWriter pw = response.getWriter();
        displayInventory(request, response, pw);
    }

	

    protected void displayInventory(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws ServletException, IOException 
	{
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
		
		List<String> llist = new LinkedList<String> ();
        
		//Table of Inventory
        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title' style='font-size:18px'>");
        pw.print("Select Service City");
        pw.print("</h3>");
        pw.print("<div class='entry'>");



        try 
		{
            llist = MySqlDataStoreUtilitiesHomeHub.getServiceCity();
			
        } 
		catch (Exception ignored) 
		{
            pw.print("WRONG!!!");
        }

		pw.print("<div id='content'><div class='post'>");
			// pw.print("<h2 class='title meta'><a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Select City</a></h2>"
					// + "<div class='entry'>");
			int count=0;
			pw.print("<form method='displayCityInventory' action='InventoryCity'>"
					+ "<table id='bestseller'><tr><td>"	
					+"<h3>Service City: </h3></td><td><select name='city' class='input'>");
					for(String val : llist){
						if (count==0){
						pw.print("<option value='"+val+"' selected>"+val+"</option>");
						count=count+1;
						}
						else{
							pw.print("<option value='"+val+"'>"+val+"</option>");
							count=count+1;
						}
					}
					
			pw.print("</select></td></tr></table>"
					+"<input type='submit' class='btnbuy' value='See Inventory'>"
					+ "</form>" + "</div></div></div>");
 

        //<script>
    }
	
	protected void displayCityInventory(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws ServletException, IOException 
	{
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        
		String city = request.getParameter("city");
		
		System.out.println(city);
		
		
		HashMap<String, Services> allServices = new HashMap<String, Services>();

		try
		{
			allServices = MySqlDataStoreUtilitiesHomeHub.getAllCityServices(city);
		}
		catch(Exception e)
		{
			
		}
		
		HashMap<String, Services> hm = new HashMap<String, Services>();

		hm.putAll(allServices);
		
		
		
        
		//Table of Inventory
        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title' style='font-size:18px'>");
        pw.print("Select Inventory City");
        pw.print("</h3>");
        pw.print("<div class='entry'>");

        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td>Service Name</td>");
		pw.print("<td>Service Id</td>");
        pw.print("<td>Price</td>");
        pw.print("<td>Service City</td>");
        pw.print("</tr>");


		int i = 1;
		int size = hm.size();
		
		for (Map.Entry<String, Services> entry : hm.entrySet()) 
		{
			Services services = entry.getValue();
			

       
            pw.print("<tr>");
            pw.print("<td>" + services.getServicename() + "</td>" +
					"<td>" + services.getId() + "</td>" +
                     "<td>" + services.getServiceprice() + "</td>" +
                     "<td>" + services.getCity() + "</td>");
            pw.print("</tr>");

        }
        pw.print("</table></div></div>");
		utility.printHtml("Footer.html");
        //Bar Chart of Inventory
		
       
    }
}
