import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/RemoveFromCart")

public class RemoveFromCart extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
		String customer_id = request.getParameter("customer_id");
		String product_id = request.getParameter("id");
		
		
		
			//PrintWriter pw = response.getWriter();
		
		if (MySqlDataStoreUtilitiesHomeHub.removefromcart(customer_id,product_id)){
		
		response.sendRedirect("CartPage");
		
		}
		
		
	}
	
	
}
