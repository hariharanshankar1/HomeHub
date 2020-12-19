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

@WebServlet("/SalesReport")
public class SalesReport extends HttpServlet 
{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displaySalesReport(request, response, pw);
    }

	//    @Override
	//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//
	//        try {
	//            ArrayList<OrderPayment> orderPaymentArrayList = MySqlDataStoreUtilities.selectDailyTransactionForChart();
	//
	//            String reviewJson = new Gson().toJson(orderPaymentArrayList);
	//
	//            System.out.println(reviewJson);
	//            response.setContentType("application/JSON");
	//            response.setCharacterEncoding("UTF-8");
	//            response.getWriter().write(reviewJson);
	//
	//        } catch (Exception ex) {
	//
	//        }
	//    }

    private void displaySalesReport(HttpServletRequest request, HttpServletResponse response, PrintWriter pw) throws ServletException, IOException 
	{
        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        //Table of all product sold
        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title' style='font-size:18px'>");
        pw.print("Table of Services Sold");
        pw.print("</h3>");
        pw.print("<div class='entry'>");

        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td>Service Name</td>");	
        pw.print("<td>Sold Price</td>");
        pw.print("</tr>");

		try 
		{
       
		
		int counter=0;
		int services=0;
		ResultSet rs=MySqlDataStoreUtilitiesHomeHub.getallserviceOrders();
		pw.println("<h4>Service Orders</h4>"); 
				
		while(!rs.next()){
			services=1;
			break;
		}
		do
		{	
			pw.print("<tr>");
            pw.print("<td>" + rs.getString("servicename") + "</td>" +
                     "<td>" + rs.getString("price") + "</td>");
            pw.print("</tr>");
			
			
		}while(rs.next());
		
			
		pw.println("</table></div></div></div>"); 
		pw.println("<br><br><br><br>"); 
        rs=MySqlDataStoreUtilitiesHomeHub.getallserviceOrders();


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
        pw.println("data.addColumn('number', 'Sold Amount');");
        pw.println(" data.addRows([");
        // for (OrderPayment orderPayment : orderPaymentHashMap.values())
		// {
            // pw.println(" ['" + orderPayment.getOrderName() + "', " + orderPayment.getSaleAmount() + "],");
        // }
		
		while(rs.next()){
			pw.println(" ['" + rs.getString("servicename") + "', " + rs.getString("price")  + "],");
		}
		
		
        pw.println("]);");
        // Set chart options
        pw.println(" var options = {'title':'Sales Report',");
        pw.println("        'width':800,");
        pw.println("       'height':500};");

        // Instantiate and draw our chart, passing in some options.
        pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");
        pw.println("  chart.draw(data, options);     }");
        pw.println(" </script>");

        //</script>

        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title' style='font-size:18px'>");
        pw.print("Bar Chart of Service Sold");
        pw.print("</h3>");
        pw.print("<div class='entry'>");
        pw.println("<div id='chart_div'></div>");
        pw.print("</div></div></div>");

        //Table of total daily sales transactions
        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title' style='font-size:18px'>");
        pw.print("Table of Daily Sales Transactions");
        pw.print("</h3>");
        pw.print("<div class='entry'>");

        pw.print("<table class='gridtable'>");
        pw.print("<tr>");
        pw.print("<td>Date</td>");
        pw.print("<td>Sold Amount</td>");
        pw.print("</tr>");
		
		HashMap<String, String> daily=new HashMap<String, String>();
		
        try 
		{
            daily = MySqlDataStoreUtilitiesHomeHub.selectDailyTransaction();
        } 
		catch (Exception ignored) 
		{
        }
		

        for (Map.Entry me : daily.entrySet())
		{
            String orderTime = me.getKey().toString().substring(0, 10);
            pw.print("<tr>");
            pw.print("<td>" + orderTime + "</td>" +
                    "<td>" + me.getValue() + "</td>");
            pw.print("</tr>");
        }
        pw.print("</table></div></div></div>");
    }
		catch (Exception ignored) 
		{
	}
	}
}
