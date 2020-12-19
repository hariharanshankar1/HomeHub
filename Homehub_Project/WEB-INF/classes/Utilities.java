import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/Utilities")


public class Utilities extends HttpServlet
{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session;
	
	public Utilities(HttpServletRequest req, PrintWriter pw)
	{
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}


	public void printHtml(String file)
	{
		String result = HtmlToString(file);
		int count=0;
		
		if (file.equals("Header.html")) 
		{
			result = result + "<div id='menu' style='float: right;'><ul>";
			
			if (session.getAttribute("username") != null) 
			{
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				count=MySqlDataStoreUtilitiesHomeHub.countcart(username);
				System.out.println(count);
				String userType = session.getAttribute("userType").toString();
				
				
				switch (userType) 
				{
					case "customer":
									result = result + "<li><a><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#650D88'>Hello, " + username + "</span></a></li>"
													+ "<li><a href='Account'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Account</span></a></li>"
													+ "<li><a href='DisplayOrders'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Orders</span></a></li>"
													+ "<li><a href='Logout'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Logout</span></a></li>";
									break;
									
					case "StoreManager":
									result = result +"<li><a href='DataVisualization'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>DataVisualization</span></a></li>"
													+"<li><a href='DataAnalytics'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>DataAnalytics</span></a></li>"
													+"<li><a href='Inventory'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Inventory</span></a></li>"
													+"<li><a href='SalesReport'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>SalesReport</span></a></li>"
													+ "<li><a href='StoreManagerHome'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>EditService</span></a></li>"
													+ "<li><a><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#650D88'>Hello, " + username + "</span></a></li>"
													+ "<li><a href='Logout'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Logout</span></a></li>";
									break;
				
					case "salesman":
									result = result + "<li><a href='SalesmanHome'><span class='glyphicon'style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>ViewOrder</span></a></li>"
													+ "<li><a><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#650D88'>Hello, " + username + "</span></a></li>"
													+ "<li><a href='Logout'><span class='glyphicon' style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Logout</span></a></li>";
									break;
				}
			} 
			else
				result = result + "<li><a href='ViewOrder'><span style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>ViewOrder</span></a></li>" + "<li><a href='Login'><span style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Login</span></a></li>";
			
			result = result + "<li><a href='CartPage'><span style = 'font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; font-size:15px; color:#E78200'>Cart(" + count + ")</span></a></li></ul></div></div><div id='page'>";
			pw.print(result);
		} 
		else
			pw.print(result);
	}


	public String getFullURL()
	{
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) 
		{
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}


	public String HtmlToString(String file) 
	{
		String result = null;
		
		try 
		{
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) 
			{
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		}
		catch (Exception e) 
		{
		}
		return result;
	}


	public void logout()
	{
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}


	public boolean isLoggedin()
	{
		if (session.getAttribute("username") == null)
			return false;
		return true;
	}


	public String username()
	{
		if (session.getAttribute("username") != null)
			return session.getAttribute("username").toString();
		return null;
	}


	public String usertype()
	{
		if (session.getAttribute("usertype") != null)
			return session.getAttribute("usertype").toString();
		return null;
	}


	public UserHomeHub getUser()
	{
		String usertype = usertype();
		HashMap<String, UserHomeHub> hm=new HashMap<String, UserHomeHub>();
		
		try
		{
			hm = MySqlDataStoreUtilitiesHomeHub.selectUser();
		}
		catch(Exception e)
		{
		}
		return hm.get(username());
	}
	
	public String storeReview(String productname, String producttype, String reviewrating, String reviewdate, String reviewtext, String reatilerpin, String price, String city, String userAge, String userGender, String userOccupation, String  userStreet, String userCity, String userState, String userZipcode, String productrebate) 
	{
		String message = MongoDBDataStoreUtilities.insertReview(productname, username(), producttype, reviewrating, reviewdate, reviewtext, reatilerpin, price, city, userAge, userGender, userOccupation, userStreet, userCity, userState, userZipcode, productrebate);
	    
		if (!message.equals("Successful")) 
		{
			return "UnSuccessful";
	    } 
		else 
		{
			HashMap<String, ArrayList<Review>> reviews = new HashMap<String, ArrayList<Review>>();
			try 
			{
				reviews = MongoDBDataStoreUtilities.selectReview();
			} 
			catch (Exception e) 
			{

			}
			
			if (reviews == null) 
			{
				reviews = new HashMap<String, ArrayList<Review>>();
			}
			
			if (!reviews.containsKey(productname)) 
			{
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(productname, arr);
			}
			
			ArrayList<Review> listReview = reviews.get(productname);
			Review review = new Review(productname, username(), producttype, reviewrating, reviewdate, reviewtext, reatilerpin, price, city, userAge, userGender, userOccupation, userStreet, userCity, userState, userZipcode, productrebate);
			listReview.add(review);

			return "Successful";
	    }
	}

	public boolean isContainsStr(String string) 
	{
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(string);
        return m.matches();
    }
}
