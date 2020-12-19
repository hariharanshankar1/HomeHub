import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		String action = request.getParameter("button");
		System.out.print(action);
		
		String msg = "good";
		String producttype= "",productId="",productName="",productImage="",productDiscount = "0.0";
		double productPrice=0.0;
		String address="",city="",state="",zipcode="",phone="";

		
		
		if (action.equals("add") || action.equals("update"))
		{
			producttype = request.getParameter("producttype");
			productId   = request.getParameter("productId");
			productName = request.getParameter("productName");
			productPrice = Double.parseDouble(request.getParameter("productPrice"));
			productImage = "images/"+request.getParameter("productImage");
			productDiscount = (request.getParameter("productDiscount"));
			address=request.getParameter("address");
			city=request.getParameter("city");
			state=request.getParameter("state");
			zipcode=request.getParameter("zipcode");
			phone=request.getParameter("phone");
			
			
			

		}
		else
		{
			productId   = request.getParameter("productId");
		}
		
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");

		if(action.equals("add"))
		{
			if(MySqlDataStoreUtilitiesHomeHub.insertservice(producttype,productId,productName,productImage,productDiscount,productPrice,address,city,state,zipcode,phone)){
				msg="Successfully Inserted Service";
			}
			else{
				msg="error plese enter proper values";
			}
		}
		else if(action.equals("update"))
		{
			if(MySqlDataStoreUtilitiesHomeHub.updateservice(producttype,productId,productName,productImage,productDiscount,productPrice,address,city,state,zipcode,phone)){
				msg="Successfully updated Service";
			}
			else{
				msg="error plese enter proper values";
			}
		
		}
		else if(action.equals("delete"))
		{
			if(MySqlDataStoreUtilitiesHomeHub.deleteorder(productId)){
				msg="Successfully deleted Service";
			}
			else{
				msg="error plese enter proper values";
			}
		}

		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Service Updates</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<h4 style='color:blue'>"+msg+"</h4>");
		pw.print("</div></div></div>");
		utility.printHtml("Footer.html");

	}
}
