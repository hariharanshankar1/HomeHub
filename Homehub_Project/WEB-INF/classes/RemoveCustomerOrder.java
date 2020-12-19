import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/RemoveCustomerOrder")

public class RemoveCustomerOrder extends HttpServlet 
{
    HttpSession session;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        
		
		
		String orderservce_id=request.getParameter("id");
		String orderId=request.getParameter("orderId");
		String userType=request.getParameter("userType");
		System.out.println(userType);
		if (userType==null){
			if (MySqlDataStoreUtilitiesHomeHub.deleteorder(Integer.parseInt(orderservce_id))){
			response.sendRedirect("DisplayOrders");
		}
		}
		else if (userType.trim().equals("Salesman"))
		{	System.out.println("------------");
			System.out.println(orderservce_id);
			if (MySqlDataStoreUtilitiesHomeHub.deleteorder(Integer.parseInt(orderservce_id)))
			{
				response.sendRedirect("SalesmanHome");
			
			}
		}
		
        
    }
}
