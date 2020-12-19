import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

@WebServlet("/Login")

public class Login extends HttpServlet 
{
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");

		HashMap<String, UserHomeHub> hm = new HashMap<String, UserHomeHub>();
		
		try
		{
          hm = MySqlDataStoreUtilitiesHomeHub.selectUser();
		}
		catch(Exception e)
		{

		}
		UserHomeHub user = hm.get(username);
		System.out.println(username);
		
		if(user != null)
		{
			String user_password = user.getPassword();
			String user_type = user.getUsertype();
										   
			
			if (password.equalsIgnoreCase(user_password) && userType.equalsIgnoreCase(user_type))
			{
				HttpSession session = request.getSession(true);
				session.setAttribute("username", username);
				session.setAttribute("userType", userType);
				
				if (userType.equalsIgnoreCase("Customer")) 
				{
					response.sendRedirect("Home");
					return;
				} 
				else if (userType.equalsIgnoreCase("StoreManager")) 
				{
					response.sendRedirect("StoreManagerHome");
					return;
				} 
				else if (userType.equalsIgnoreCase("Salesman")) 
				{
					response.sendRedirect("SalesmanHome");
					return;
				}
			}
		}
		displayLogin(request, response, pw, true);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}

	protected void displayLogin(HttpServletRequest request,
			HttpServletResponse response, PrintWriter pw, boolean error)
			throws ServletException, IOException 
	{

		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div id = 'content'><div class='post' style='float: none; width: 100%;'>");
		pw.print("<h2 class='title meta'><a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		
		if (error)
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("login_msg") != null)
		{
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}
		
		pw.print("<form method='post' action='Login'><fieldset>"
				+ "<table style='width:100%; height:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='userType' class='input'><option value='customer' selected>Customer</option><option value='StoreManager'>Store Manager</option><option value='salesman'>Salesman</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
				+ "</td></tr></table>"
				+ "</form></fieldset>" + "</div></div></div></div><br><br><br>");
		utility.printHtml("Footer.html");
	}

}
