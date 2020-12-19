import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.io.IOException;
import java.io.*;


public class AjaxUtility 
{
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homehub?useUnicode=true&characterEncoding=utf8", "root", "12345");							
			message="Successful";
			return message;
		}
		catch(SQLException e)
		{
			message="unsuccessful";
			return message;
		}
		catch(Exception e)
		{
			message="unsuccessful";
			return message;
		}
	}

	public  StringBuffer readdata(String searchId)
	{
		HashMap<String, Services> data;
		data=getData();

 	    Iterator it = data.entrySet().iterator();
        while (it.hasNext())
	    {
            Map.Entry pi = (Map.Entry)it.next();
			
			if(pi!=null)
			{
				Services p=(Services)pi.getValue();
                if (p.getServicename().toLowerCase().startsWith(searchId))
                {
					sb.append("<product>");
					sb.append("<id>" + p.getId() + "</id>");
					sb.append("<productName>" + p.getServicename() + "</productName>");
					sb.append("</product>");
                }
			}
       }
	   return sb;
	}

	public static HashMap<String, Services> getData()
	{
		HashMap<String, Services> hm=new HashMap<String, Services>();
		try
		{
			getConnection();

		    String selectproduct="select * from  services;";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();

			while(rs.next())
			{	
				Services services = new Services(rs.getString("servicetype"), rs.getString("id"), rs.getString("servicename"), rs.getDouble("serviceprice"), rs.getString("serviceimage"), rs.getString("url"), rs.getString("rating"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("address"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getString("phonenumber"), rs.getString("discount"));
				hm.put(rs.getString("id"), services);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;
	}

}
