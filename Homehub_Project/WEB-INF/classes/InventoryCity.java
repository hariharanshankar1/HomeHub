import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;


import com.google.gson.Gson;
//import com.sun.tools.corba.se.idl.constExpr.Or;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
import java.io.*;
import java.sql.*;

@WebServlet("/InventoryCity")
public class InventoryCity extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        PrintWriter pw = response.getWriter();
        displayCityInventory(request, response, pw);
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
        pw.print("Services list City:"+city);
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
		pw.println("<br><br><br><br>"); 
		
        //Bar Chart of Inventory
		
		
		 //<script>
        pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");
        pw.println("<script type='text/javascript'>");

        // Load the Visualization API and the corechart package.
        pw.println("google.charts.load('current', {'packages':['corechart']});");

        // Set a callback to run when the Google Visualization API is loaded.
        pw.println("google.charts.setOnLoadCallback(drawChart);");

        // Callback that creates and populates a data table,
        // instantiates the pie chart, passes in the data and
        // draws it.
        pw.println("function drawChart() {");

        // Create the data table.
        pw.println("var data = new google.visualization.DataTable();");
        pw.println("data.addColumn('string', 'Service Name');");
        pw.println("data.addColumn('number', 'Price');");
        pw.println(" data.addRows([");
		int count=0;
        for (Map.Entry<String, Services> entry : hm.entrySet()) 
		{
			Services services = entry.getValue();
            
			pw.println(" ['" + services.getServicename() + "', " + services.getServiceprice() + "],");
			count=count+1;
			System.out.println(services.getServicename());
			
        }
         pw.println("]);");
        // Set chart options
        pw.println(" var options = {'title':'Sales Report',");
        pw.println("        'width':800,");
        pw.println("       'height':1300};");

        // Instantiate and draw our chart, passing in some options.
        pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
        pw.println("  chart.draw(data, options);     }");
        pw.println(" </script>");

        //</script>

        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title' style='font-size:18px'>");
        //pw.print("Bar Chart of Services");
        pw.print("</h3>");
        pw.print("<div class='entry'>");
        pw.println("<div id='chart_div'></div>");
        pw.print("</div></div></div>");

		
       
    }
}
