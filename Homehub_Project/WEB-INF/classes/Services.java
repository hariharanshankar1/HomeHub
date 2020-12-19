import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Services")

public class Services extends HttpServlet
{
	private String servicetype;
	private String id;
	private String servicename;
	private double serviceprice;
	private String serviceimage;
	private String url;
	private String rating;
	private String latitude;
	private String longitude;
	private String address;
	private String city;
	private String zipcode;
	private String state;
	private String phonenumber;
	private String discount;

	public Services(String servicetype, String id, String servicename, double serviceprice, String serviceimage, String url, String rating, String latitude, String longitude, String address, String city, String zipcode, String state, String phonenumber, String discount)
	{
		this.servicetype = servicetype;
		this.id = id;
		this.servicename = servicename;
		this.serviceprice = serviceprice;
		this.serviceimage = serviceimage;
		this.url = url;
		this.rating = rating;
		this.latitude = latitude;
		this.longitude = longitude;
		this.address = address;
		this.city = city;
		this.zipcode = zipcode;
		this.state = state;
		this.phonenumber = phonenumber;
		this.discount = discount;
	}

	public Services()
	{

	}

	public String getServicetype() 
	{
		return servicetype;
	}
	public void setServicetype(String servicetype) 
	{
		this.servicetype = servicetype;
	}
	
	public String getId() 
	{
		return id;
	}
	public void setId(String id) 
	{
		this.id = id;
	}

	public String getServicename() 
	{
		return servicename;
	}
	
	public void setServicename(String servicename)
	{
		this.servicename = servicename;
	}
	
	public double getServiceprice() 
	{
		return serviceprice;
	}
	
	public void setServiceprice(double serviceprice) 
	{
		this.serviceprice = serviceprice;
	}
	
	public String getServiceimage()
	{
		return serviceimage;
	}
	
	public void setServiceimage(String serviceimage) 
	{
		this.serviceimage = serviceimage;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getRating() 
	{
		return rating;
	}

	public void setRating(String rating) 
	{
		this.rating = rating;
	}
	
	public String getLatitude() 
	{
		return latitude;
	}

	public void setLatitude(String latitude) 
	{
		this.latitude = latitude;
	}
	
	public String getLongitude() 
	{
		return longitude;
	}

	public void setLongitude(String longitude) 
	{
		this.longitude = longitude;
	}
	
	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getCity() 
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}
	
	public String getZipcode() 
	{
		return zipcode;
	}

	public void setzipcode(String zipcode) 
	{
		this.zipcode = zipcode;
	}
	
	public String getState() 
	{
		return state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}
	
	public String getPhonenumber() 
	{
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) 
	{
		this.phonenumber = phonenumber;
	}

	public String getDiscount() 
	{
		return discount;
	}

	public void setDiscount(String discount) 
	{
		this.discount = discount;
	}
}
