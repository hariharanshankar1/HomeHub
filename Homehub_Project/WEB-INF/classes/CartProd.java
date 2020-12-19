import java.util.*;
import java.util.Map;


public class CartProd
{
    private String customer_id;
    private String product_name;
    private String product_id;
    private double quantity;
    private double price_per_unit;

    public CartProd(String customer_id, String product_name, String product_id, double quantity,double price_per_unit) 
	{
		this.customer_id = customer_id;
        this.product_name = product_name;
        this.product_id = product_id;
        this.quantity = quantity;
		this.price_per_unit=price_per_unit;
       
    }

    public CartProd() 
	{

    }
	
	public String getcustomer_id() 
	{
        return customer_id;
    }

    public void setcustomer_id(String customer_id) 
	{
        this.customer_id = customer_id;
    }
	
    public String getproduct_name() 
	{
        return product_name;
    }

    public void setproduct_name(String product_name) 
	{
        this.product_name = product_name;
    }

    public String getproduct_id()
	{
        return product_id;
    }

    public void setproduct_id(String product_id) 
	{
        this.product_id = product_id;
    }


    public double getquantity() 
	{
        return quantity;
    }

    public void setquantity(double quantity) 
	{
        this.quantity = quantity;
    }
	 public double getprice_per_unit() 
	{
        return price_per_unit;
    }

    public void setprice_per_unit(double quantity) 
	{
        this.price_per_unit = price_per_unit;
    }
}
