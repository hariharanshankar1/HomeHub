import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderItem")

public class OrderItem extends HttpServlet
{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String producttype;

	public OrderItem(String name, double price, String image)
	{
		this.name = name;
		this.price = price;
		this.image = image;
		//this.retailer = retailer;
	}
	
	public OrderItem(String id, String name, double price, String image, String producttype)
	{
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
		this.producttype = producttype;
		//this.retailer = retailer;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}

	public String getImage() 
	{
		return image;
	}

	public void setImage(String image) 
	{
		this.image = image;
	}
	
	public String getProductType() 
	{
		return producttype;
	}

	public void setProductType(String producttype) 
	{
		this.producttype = producttype;
	}

	// public String getRetailer() 
	// {
	// 	return retailer;
	// }
	//
	// public void setRetailer(String retailer) 
	// {
	// 	this.retailer = retailer;
	// }
}
