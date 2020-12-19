import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class MySqlDataStoreUtilitiesHomeHub
{
    static Connection conn = null;

    public static void getConnection() 
	{

        try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homehub?useUnicode=true&characterEncoding=utf8", "root", "12345");
        } 
		catch (Exception e) 
		{
            System.out.println(e.getMessage());
        }
    }
	
	public static HashMap<String, Services> getAllServices(String servicetype)
	{
		HashMap<String, Services> hm=new HashMap<String, Services>();
		try
		{
			getConnection();

			String selectServices = "SELECT * FROM services WHERE servicetype = '" + servicetype + "';";
			PreparedStatement pst = conn.prepareStatement(selectServices);
			// pst.setString(1, servicetype);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Services services = new Services(rs.getString("servicetype"), rs.getString("id"), rs.getString("servicename"), rs.getDouble("serviceprice"), rs.getString("serviceimage"), rs.getString("url"), rs.getString("rating"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("address"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getString("phonenumber"), rs.getString("discount"));
				hm.put(rs.getString("id"), services);
				services.setId(rs.getString("id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	
	public static HashMap<String, Services> getAllServicesDeals()
	{
		HashMap<String, Services> hm=new HashMap<String, Services>();
		try
		{
			getConnection();

			String selectServices = "select * from services where id in ('_abPVeUNDglp35Z6fR5Xag160', '4IPkuN1TXTL-fZw4vQpGxw');";
			PreparedStatement pst = conn.prepareStatement(selectServices);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Services services = new Services(rs.getString("servicetype"), rs.getString("id"), rs.getString("servicename"), rs.getDouble("serviceprice"), rs.getString("serviceimage"), rs.getString("url"), rs.getString("rating"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("address"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getString("phonenumber"), rs.getString("discount"));
				hm.put(rs.getString("id"), services);
				services.setId(rs.getString("id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	
	public static HashMap<String, String> getService(String serviceid)
	{
		HashMap<String, String> hm=new HashMap<String, String>();
		try
		{
			getConnection();

			String selectOneService = "SELECT * FROM services WHERE id = '" + serviceid + "';";
			PreparedStatement pst = conn.prepareStatement(selectOneService);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				hm.put("servicetype", rs.getString("servicetype"));
				hm.put("id", rs.getString("id"));
				hm.put("servicename", rs.getString("servicename"));
				hm.put("serviceprice", String.valueOf(rs.getDouble("serviceprice")));
				hm.put("serviceimage", rs.getString("serviceimage"));
				hm.put("rating", rs.getString("rating"));
				hm.put("latitude", rs.getString("latitude"));
				hm.put("longitude", rs.getString("longitude"));
				hm.put("address", rs.getString("address"));
				hm.put("city", rs.getString("city"));
				hm.put("zipcode", rs.getString("zipcode"));
				hm.put("state", rs.getString("state"));
				hm.put("phonenumber", rs.getString("phonenumber"));
				hm.put("discount", rs.getString("discount"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	
	public static HashMap<String, String> getServiceUsingServicename(String servicename)
	{
		HashMap<String, String> hm=new HashMap<String, String>();
		try
		{
			getConnection();

			String selectOneService = "SELECT * FROM services WHERE servicename = '" + servicename + "';";
			PreparedStatement pst = conn.prepareStatement(selectOneService);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				hm.put("servicetype", rs.getString("servicetype"));
				hm.put("id", rs.getString("id"));
				hm.put("servicename", rs.getString("servicename"));
				hm.put("serviceprice", String.valueOf(rs.getDouble("serviceprice")));
				hm.put("serviceimage", rs.getString("serviceimage"));
				hm.put("rating", rs.getString("rating"));
				hm.put("latitude", rs.getString("latitude"));
				hm.put("longitude", rs.getString("longitude"));
				hm.put("address", rs.getString("address"));
				hm.put("city", rs.getString("city"));
				hm.put("zipcode", rs.getString("zipcode"));
				hm.put("state", rs.getString("state"));
				hm.put("phonenumber", rs.getString("phonenumber"));
				hm.put("discount", rs.getString("discount"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	
	public static HashMap<String, Services> getAllServicesSorted(String servicetype)
	{
		HashMap<String, Services> hm = new HashMap<String, Services>();
		try
		{
			getConnection();
			
			String selectServices = "SELECT * FROM services WHERE servicetype = '" + servicetype + "';";
			PreparedStatement pst = conn.prepareStatement(selectServices);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				Services services = new Services(rs.getString("servicetype"), rs.getString("id"), rs.getString("servicename"), rs.getDouble("serviceprice"), rs.getString("serviceimage"), rs.getString("url"), rs.getString("rating"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("address"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getString("phonenumber"), rs.getString("discount"));
				hm.put(rs.getString("servicename"), services);
				services.setServicename(rs.getString("servicename"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	
	public static HashMap<String, UserHomeHub> selectUser() 
	{
        HashMap<String, UserHomeHub> hm = new HashMap<String, UserHomeHub>();
        try
		{
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "SELECT * FROM customer";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);

			while (rs.next())
			{
                UserHomeHub user = new UserHomeHub(rs.getString("username"), rs.getString("password"), "Customer");
                hm.put(rs.getString("username"), user);
            }
			
			String selectCustomerQuery1 = "SELECT * FROM staff";
            ResultSet rs1 = stmt.executeQuery(selectCustomerQuery1);
            
			while (rs1.next())
			{
                UserHomeHub user1 = new UserHomeHub(rs1.getString("staff_id"), rs1.getString("username"), rs1.getString("password"), rs1.getString("job_title"));
                hm.put(rs1.getString("username"), user1);
            }
        } 
		catch (Exception e) 
		{
            System.out.println(e.getMessage());
        }
        return hm;
    }
	public static boolean insertUser(String name, String username, String password, String userType) 
	{
        try 
		{
            getConnection();
			
			if(userType.equalsIgnoreCase("Customer"))
			{
				int customer_id = 0;
				Statement stmt = conn.createStatement();
				String selectCustomerQuery = "SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1;";
				ResultSet rs = stmt.executeQuery(selectCustomerQuery);
				while(rs.next())
				{
					customer_id = Integer.parseInt(rs.getString("customer_id")) + 1;
				}
				
				String insertIntoQuery = "INSERT INTO customer(customer_id, name, username, password) VALUES (?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoQuery);
				pst.setString(1, String.valueOf(customer_id));
				pst.setString(2, name);
				pst.setString(3, username);
				pst.setString(4, password);
				pst.execute();
			}
			else
			{
				int staff_id = 0;
				
				Random rand = new Random();
			
				String[] job_title = {"Cashier", "Manager", "Storekeeper"};
				int randomValue = rand.nextInt(3);
			
				Statement stmt = conn.createStatement();
				String selectCustomerQuery = "SELECT staff_id FROM staff ORDER BY staff_id DESC LIMIT 1;";
				ResultSet rs = stmt.executeQuery(selectCustomerQuery);
				
				while(rs.next())
				{
					staff_id = Integer.parseInt(rs.getString("staff_id")) + 1;
				}
				
				String insertIntoQuery = "INSERT INTO staff(staff_id, name, username, password, job_title) VALUES (?,?,?,?,?);";
				PreparedStatement pst = conn.prepareStatement(insertIntoQuery);
				pst.setString(1, String.valueOf(staff_id));
				pst.setString(2, name);
				pst.setString(3, username);
				pst.setString(4, password);
				pst.setString(5, userType);
				pst.execute();
			}
        } 
		catch (Exception e)
		{
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
	
	
	
		public static HashMap<String, String> getuserdetails(String uname)
	{
		HashMap<String, String> userval = new HashMap<String, String>();
		
        try
		{
            getConnection();
            Statement stmt = conn.createStatement();
			
            String selectCustomerQuery = "SELECT * FROM customer where username='"+uname+"'";
			
            
			
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			
			//System.out.println(rs);
			while (rs.next())
			{
                
                userval.put("name",rs.getString("name"));
				userval.put("password",rs.getString("password"));
				userval.put("age",String.valueOf(rs.getDouble("age")));
				userval.put("outstanding_balance",String.valueOf(rs.getDouble("outstanding_balance")));
				userval.put("customer_id",rs.getString("customer_id"));			
				userval.put("address_id",rs.getString("address_id"));
            }
			
			
			if(userval.get("address_id")!=null){
            selectCustomerQuery = "SELECT * FROM address where customer_id='"+userval.get("customer_id")+"'";
			
            ResultSet rs1 = stmt.executeQuery(selectCustomerQuery);
			int count=0;
			
				while(rs1.next())
				{
					if(count==0)
					
					{
						//System.out.println("Inside");
						userval.put("address1",rs1.getString("address_id")+","+String.valueOf(rs1.getInt("apt_no"))+","+String.valueOf(rs1.getInt("street_number"))+","+rs1.getString("street_name")+","+rs1.getString("city")+","+rs1.getString("state")+","+rs1.getString("zip"));
						userval.put("state",rs1.getString("state"));
						count=count+1;
					}
					
				}
				
				
				count=0;
			
				Statement stmt2 = conn.createStatement();
                selectCustomerQuery = "select * from credit_card where address_id = '"+userval.get("address_id")+"';";
				ResultSet rs2 = stmt2.executeQuery(selectCustomerQuery);
				while(rs2.next())
				{
					if(count==0)
					
					{
						
						userval.put("card_number1",String.valueOf((int)rs2.getDouble("card_number")));
						count=count+1;
					}
					
				
			}	
			}
			
			
		


        } 
		catch (Exception e) 
		{
            System.out.println(e.getMessage());
        }
        return userval;
			
		
	}
	
	
	
	public static boolean updateUser(String name, String username, String password, double age) 
	{
		try 
		{
			getConnection();
			
			
			
			Statement stmt = conn.createStatement();
			
			String updateProductQurey = "Update customer set name=?,password=?,age= ? where username=?";
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,name);
			pst.setString(2,password);
		
			pst.setDouble(3,age);
			pst.setString(4,username);
			pst.executeUpdate();
			
			
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	
	
	public static boolean updateaddress(String apt_no,String street_number,String street_name,String city,String state,String zip,String address_id,String customer_id,String card_number) 
	{
        try 
		{
            getConnection();
			String last_address_id="1";
			
			
			Statement stmt = conn.createStatement();
			
			if (address_id==""){
			String selectCustomerQuery = "select address_id from address order by address_id DESC limit 1;";
            ResultSet rs1 = stmt.executeQuery(selectCustomerQuery);
			
			while(rs1.next())
			{
				last_address_id=rs1.getString("address_id");
				
			}
				
			String address_id_new=String.valueOf(Integer.parseInt(last_address_id)+1);
			
			String insertaddressquery = "insert into address(address_id,customer_id,apt_no,street_number,street_name,city,state,zip,address_type) values('"+address_id_new+"','"+customer_id+"',"+apt_no+","+street_number+",'"+street_name+"','"+city+"','"+state+"','"+zip+"','Delivery');";
			//System.out.println(insertaddressquery);
			PreparedStatement pst = conn.prepareStatement(insertaddressquery);
			
			pst.execute();
			
			insertaddressquery = "update customer set address_id='"+address_id_new+"'where customer_id='"+customer_id+"';";
			pst = conn.prepareStatement(insertaddressquery);
			pst.execute();
			
			insertaddressquery = "INSERT INTO credit_card(customer_ID, card_number, address_ID)VALUES('"+customer_id+"',"+card_number+",'"+address_id_new+"');";
			pst = conn.prepareStatement(insertaddressquery);
			pst.execute();
		
			
			}
			else if (address_id=="" & apt_no=="" & street_name=="" & street_number=="" & city=="" & state=="" & zip=="")
			{
				int s=0;
			}
			else if(address_id!="")
			{
				String updateProductQurey = "Update address set apt_no=?,street_number=?,street_name= ?,city=?,state=?,zip=? where address_id=?";
				PreparedStatement pst = conn.prepareStatement(updateProductQurey);
				
						
				pst.setInt(1,Integer.parseInt(apt_no));
				pst.setInt(2,Integer.parseInt(street_number));
				pst.setString(3,street_name);
				
				pst.setString(4,city);
				pst.setString(5,state);
				pst.setString(6,zip);
				pst.setString(7,address_id);

				pst.executeUpdate();
				
				updateProductQurey = "Update credit_card set card_number=? where address_id=? and customer_id=?";
				pst = conn.prepareStatement(updateProductQurey);
				pst.setDouble(1,Double.parseDouble(card_number));
				
				pst.setString(2,address_id);
				pst.setString(3,customer_id);
				pst.executeUpdate();
				
				
				
			}
			
			
			
			
        } 
		catch (Exception e)
		{
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
	
	
	
	
	
	
		public static boolean updatecart(String username, String product_id, String product_name,String price_per_unit,String Type) 
	{
		HashMap<String, String> getuserdetails=getuserdetails(username);
		String customer_id=getuserdetails.get("customer_id");
		
        if (Type.trim().equals("services")){
		try 
		{
            getConnection();
			Statement stmt = conn.createStatement();
			
			String  selectCustomerQuery = "SELECT * FROM shopping_cart_services where customer_id ='"+customer_id+"';";
			ResultSet rs2 = stmt.executeQuery(selectCustomerQuery);
			
			if (!rs2.next()) 
			{
				
			String updateProductQurey = "insert into shopping_cart_services values (?,?,?,?,?)";
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,customer_id);
			pst.setString(2,product_id);
		
			
			pst.setString(3,product_name);
			pst.setDouble(4,1);
			pst.setDouble(5,Double.parseDouble(price_per_unit));
			pst.executeUpdate();
				
				
			}
			else{
				int val=0;
			do{
					
					
					
				
					if(product_id.trim().equals(rs2.getString("id").trim()))
					{
						
						String updateProductQurey = "Update shopping_cart_services set quantity=? where customer_id=? and id=? and servicename=?";
						PreparedStatement pst = conn.prepareStatement(updateProductQurey);
						double d=rs2.getDouble("quantity")+1;
						
						
						
						
						pst.setDouble(1,d);
						
						pst.setString(2,customer_id);
						pst.setString(3,product_id);
					
						
						pst.setString(4,product_name);
						
						pst.executeUpdate();						
						
						val=1;
					}
					
			}while (rs2.next());
					if(val==0){
						
						
						String updateProductQurey = "insert into shopping_cart_services values (?,?,?,?,?)";
						PreparedStatement pst = conn.prepareStatement(updateProductQurey);
						
						pst.setString(1,customer_id);
						pst.setString(2,product_id);

						
						pst.setString(3,product_name);
						pst.setDouble(4,1);
						pst.setDouble(5,Double.parseDouble(price_per_unit));
					pst.executeUpdate();
					}
			}
			
				
			
			
        } 
		catch (Exception e)
		{
            System.out.println(e.getMessage());
            return false;
		}	
	
	
	}
        return true;
    }
		
	public static List<CartProd> getAllCartItems(String uname)
	{
		List<CartProd> cart = new ArrayList<CartProd>();  
		HashMap<String, String> userval = new HashMap<String, String>();
			
			
		userval=getuserdetails(uname);
		String customer_id=userval.get("customer_id");
		//String state=userval.get("state");
		
        
        try
		{  
            getConnection();  
			Statement stmt = conn.createStatement();
            
			String  selectCustomerQuery = "SELECT * FROM shopping_cart_services where customer_id ='"+customer_id+"';";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);  
            
			while(rs.next())
			{  	
				
                CartProd cartprod = new CartProd(rs.getString("customer_id"),rs.getString("servicename"),rs.getString("id"),rs.getDouble("quantity"),rs.getDouble("price_per_unit"));				
				
				cart.add(cartprod); 
            }  
			
			
			selectCustomerQuery = "SELECT * FROM shopping_cart_products where customer_id ='"+customer_id+"';";
			rs = stmt.executeQuery(selectCustomerQuery);  
            
			while(rs.next())
			{  	
				
                CartProd cartprod = new CartProd(rs.getString("customer_id"),rs.getString("product_name"),rs.getString("product_id"),rs.getDouble("quantity"),rs.getDouble("price_per_unit"));				
				
				cart.add(cartprod); 
            }

            conn.close();  
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}         
        return cart;
	}
	
	
	public static boolean removefromcart(String customer_id, String product_id) 
	{
        try 
		{
            getConnection();
			Statement stmt = conn.createStatement();

			String updateProductQurey = "delete from shopping_cart_services where customer_id=? and id=?";
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,customer_id);
			pst.setString(2,product_id);

			pst.executeUpdate();		
			
			updateProductQurey = "delete from shopping_cart_products where customer_id=? and product_id=?";
			pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,customer_id);
			pst.setString(2,product_id);

			pst.executeUpdate();
			
			
        } 
		catch (Exception e)
		{
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
	
	public static int countcart(String uname)
	{
		
		HashMap<String, String> userval = new HashMap<String, String>();
			
			
		userval=getuserdetails(uname);
		String customer_id=userval.get("customer_id");
		//String state=userval.get("state");
		
        int count=0;
        try
		{  
            getConnection();  
			Statement stmt = conn.createStatement();
            
			String  selectCustomerQuery = "select count(*) as count from shopping_cart_services where customer_id ='"+customer_id+"';";
			
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);  
            
			while(rs.next())
			{  	
				count=count+rs.getInt("count");
				
            }  
			
			selectCustomerQuery = "select count(*) as count from shopping_cart_products where customer_id ='"+customer_id+"';";
			
			rs = stmt.executeQuery(selectCustomerQuery);  
            
			while(rs.next())
			{  	
				count=count+rs.getInt("count");
				
            }
            conn.close();  
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}         
        return count;
	}
	

	public static List<CartProd> getcartservices(String uname)
	{
		List<CartProd> cart = new ArrayList<CartProd>();  
		HashMap<String, String> userval = new HashMap<String, String>();
			
			
		userval=getuserdetails(uname);
		String customer_id=userval.get("customer_id");
		//String state=userval.get("state");
		
        
        try
		{  
            getConnection();  
			Statement stmt = conn.createStatement();
            
			String  selectCustomerQuery = "SELECT * FROM shopping_cart_services where customer_id ='"+customer_id+"';";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);  
            
			while(rs.next())
			{  	
				
                CartProd cartprod = new CartProd(rs.getString("customer_id"),rs.getString("servicename"),rs.getString("id"),rs.getDouble("quantity"),rs.getDouble("price_per_unit"));				
				
				cart.add(cartprod); 
            }  
			

            conn.close();  
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}         
        return cart;
	}
	
	public static List<CartProd> getcartprods(String uname)
	{
		List<CartProd> cart = new ArrayList<CartProd>();  
		HashMap<String, String> userval = new HashMap<String, String>();
			
			
		userval=getuserdetails(uname);
		String customer_id=userval.get("customer_id");
		//String state=userval.get("state");
		
        
        try
		{  
            getConnection();  
			Statement stmt = conn.createStatement();
            		
			String selectCustomerQuery = "SELECT * FROM shopping_cart_products where customer_id ='"+customer_id+"';";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);  
            
			while(rs.next())
			{  	
				
                CartProd cartprod = new CartProd(rs.getString("customer_id"),rs.getString("product_name"),rs.getString("product_id"),rs.getDouble("quantity"),rs.getDouble("price_per_unit"));				
				
				cart.add(cartprod); 
            }

            conn.close();  
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}         
        return cart;
	}
	
	
	
	
	
	
	public static int placeOrder(String outstanding_balance,String username, String useraddress,  String deliveryDate, String deliverytype, String creditCardNo) 
	{
		int order_id=0;
        try 
		{
            getConnection();
			Statement stmt = conn.createStatement();
			
			
			int count=0;
			
			Random rand = new Random();
			SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			
			Date current_date = new Date();
			long Expected_Date = ThreadLocalRandom.current().nextLong(current_date.getTime(), current_date.getTime() + 900000000);
			Date Expected_Delivery_Date = new Date(Expected_Date);
			
			String[] delornot={"Disputed","Approved"};
			int Order_Returned = rand.nextInt(2);
			int Order_Delivered_on_Time = rand.nextInt(2);
			
			
			String selectCustomerQuery = "SELECT order_id FROM orders ORDER BY CAST(order_id AS DECIMAL) DESC limit 1;";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			
			HashMap<String, String> userval = new HashMap<String, String>();
			
			
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(username);
			String customer_id=userval.get("customer_id");
			
			while(rs.next())
			{
				
				order_id = Integer.parseInt(rs.getString("order_ID")) + 1;
				
				
			}
			
			if (Double.parseDouble(outstanding_balance)>0){
				
			
			String updateProductQurey = "Update customer set outstanding_balance=? where username=?";
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			
		
			pst.setDouble(1,Double.parseDouble(outstanding_balance));
			pst.setString(2,username);
			pst.executeUpdate();
			}
			
			
			
			selectCustomerQuery = "SELECT * FROM shopping_cart_products where customer_id ='"+userval.get("customer_id")+"';";
			ResultSet rs2 = stmt.executeQuery(selectCustomerQuery);
			while(rs2.next())
			{  	
				if(count==0){
				String updateProductQurey = "INSERT INTO orders(order_id,customer_id,card_number,status,userAddress,deliveryDate,deliveryType,orderreturned,outstanding_balance) VALUES(?,?,?,?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(updateProductQurey);

				pst.setString(1,String.valueOf(order_id));
				pst.setString(2,customer_id);
				pst.setDouble(3,Double.parseDouble(creditCardNo));
				pst.setString(4,delornot[Order_Delivered_on_Time]);
				pst.setString(5,useraddress);
				pst.setString(6,SimpleDateFormat.format(Expected_Delivery_Date));
				pst.setString(7,deliverytype);
				pst.setString(8, String.valueOf(Order_Returned));
				pst.setDouble(9,Double.parseDouble(outstanding_balance));
				pst.executeUpdate();
				
				count=count+1;
				}
				
				String updateProductQurey = "INSERT INTO orders_products(order_ID, product_ID, review_rating, quantity, price_per_unit,product_name)VALUES(?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(updateProductQurey);
				
				pst.setString(1,String.valueOf(order_id));
				pst.setString(2,rs2.getString("product_id"));
				pst.setString(3,getrating(rs2.getString("product_id"),"Product"));
				pst.setDouble(4,rs2.getDouble("quantity"));
				pst.setDouble(5,rs2.getDouble("price_per_unit"));
				pst.setString(6,rs2.getString("product_name"));
				pst.executeUpdate();

			
			}
			
			String updateProductQurey = "delete from shopping_cart_products where customer_id='"+customer_id+"';";
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.executeUpdate();
			
			selectCustomerQuery = "SELECT * FROM shopping_cart_services where customer_id ='"+userval.get("customer_id")+"';";
			rs2 = stmt.executeQuery(selectCustomerQuery);
			while(rs2.next())
			{  	
				if(count==0){
				updateProductQurey = "INSERT INTO orders(order_id,customer_id,card_number,status,userAddress,deliveryDate,deliveryType,orderreturned,outstanding_balance) VALUES(?,?,?,?,?,?,?,?,?)";
				pst = conn.prepareStatement(updateProductQurey);
				
				pst.setString(1,String.valueOf(order_id));
				pst.setString(2,customer_id);
				pst.setDouble(3,Double.parseDouble(creditCardNo));
				pst.setString(4,delornot[Order_Delivered_on_Time]);
				pst.setString(5,useraddress);
				pst.setString(6,SimpleDateFormat.format(Expected_Delivery_Date));
				pst.setString(7,deliverytype);
				pst.setString(8, String.valueOf(Order_Returned));
				pst.setDouble(9,Double.parseDouble(outstanding_balance));
				pst.executeUpdate();
				
				count=count+1;
				}
				
				updateProductQurey = "INSERT INTO orders_services(order_ID, service_id, review_rating, price,servicename)VALUES(?,?,?,?,?)";
				pst = conn.prepareStatement(updateProductQurey);
								
				pst.setString(1,String.valueOf(order_id));
				pst.setString(2,rs2.getString("id"));
				pst.setString(3,getrating(rs2.getString("id"),"services"));
				pst.setDouble(4,rs2.getDouble("price_per_unit"));
				pst.setString(5,rs2.getString("servicename"));
				pst.executeUpdate();

			
			}
			updateProductQurey = "delete from shopping_cart_services where customer_id='"+customer_id+"';";
			pst = conn.prepareStatement(updateProductQurey);
			
			pst.executeUpdate();
			
			
			
			
        } 
		catch (Exception e)
		{
            System.out.println(e.getMessage());
            return 0;
        }
        return order_id;
    }
	
	
	public static String getrating(String serviceid,String Type)
	{
		String rating="5";
		try
		{
			getConnection();
			
			String selectOneService="";
			if(Type=="services"){
			selectOneService = "SELECT * FROM services WHERE id = '" + serviceid + "';";
			
			}
			else{
				selectOneService = "SELECT * FROM product WHERE product_id= '" + serviceid + "';";
				
			}
			PreparedStatement pst = conn.prepareStatement(selectOneService);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				rating=rs.getString("rating");
			}
		}
		catch(Exception e)
		{
		}
		return rating;
	}
	
	
		public static ResultSet getproductOrders(String userId)
	{
		ResultSet rs = null;
		try
		{
			getConnection();
			Statement stmt = conn.createStatement();
			String selectWarehouseQuery = "SELECT order_id FROM orders where customer_id = '" + userId + "';";
			rs = stmt.executeQuery(selectWarehouseQuery);
			
			int count=0;
			String condition="";
			
			while (rs.next())
				
			{
				if(count==0){
					condition=condition+"('"+rs.getString("order_id")+"'";
					count=count+1;
				}
				else{
					condition=condition+",'"+rs.getString("order_id")+"'";
					break;
				}
            }
			
			condition=condition+")";
			
			selectWarehouseQuery = "SELECT * FROM orders_products where order_id in " + condition + ";";
			rs = stmt.executeQuery(selectWarehouseQuery);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
public static ResultSet getserviceOrders(String userId)
	{
		ResultSet rs = null;
		try
		{
			getConnection();
			Statement stmt = conn.createStatement();
			String selectWarehouseQuery = "SELECT order_id FROM orders where customer_id = '" + userId + "';";
			rs = stmt.executeQuery(selectWarehouseQuery);
			
			int count=0;
			String condition=null;
			
			while (rs.next())
				
			{
				if(count==0){
					condition="'"+rs.getString("order_id")+"'";
					count=count+1;
				}
				else{
					condition=condition+",'"+rs.getString("order_id")+"'";
					
				}
            }
			
			selectWarehouseQuery = "SELECT * FROM orders_services where order_id in (" + condition + ");";
			rs = stmt.executeQuery(selectWarehouseQuery);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
	
	
		public static void insertTransactions(String login_ID, String Customer_Name, String Customer_Age, String Customer_Occupation, String Credit_Card_Number, int Order_ID, String Delivery_Type, String Delivery_Zip_Code)
	{
        try 
		{
			

			SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date current_date = new Date();
			long Expected_Date = ThreadLocalRandom.current().nextLong(current_date.getTime(), current_date.getTime() + 900000000);
			Date Expected_Delivery_Date = new Date(Expected_Date);
			
			long Actual_Date = ThreadLocalRandom.current().nextLong(current_date.getTime(), current_date.getTime() + 500000000);
			Date Actual_Delivery_Date = new Date(Actual_Date);
			
			// Calendar c = Calendar.getInstance();
			// c.setTime(new Date());
			// c.add(Calendar.DATE, 14);
			// String output = SimpleDateFormat.format(c.getTime());
			
			Random rand = new Random();
			
			
			String[] delornot={"Disputed","Approved"};
			int Order_Returned = rand.nextInt(2);
			int Order_Delivered_on_Time = rand.nextInt(2);
			
            getConnection();
			Statement stmt = conn.createStatement();
			String selectCustomerQuery = "SELECT * FROM orders_services where order_id ='"+String.valueOf(Order_ID)+"';";
			ResultSet rs2 = stmt.executeQuery(selectCustomerQuery);
			while(rs2.next())
			{ 
            String insertIntoCustomerOrderQuery = "insert into transactions (login_ID, Customer_Name, Customer_Age, Customer_Occupation, Credit_Card_Number, Order_ID, Order_Date, Expected_Delivery_Date, Actual_Delivery_Date, Product_ID, Product_Name, Review_Rating, Delivery_Tracking_ID, Delivery_Type, Delivery_Zip_Code, Transaction_Status, Order_Returned, Order_Delivered_on_Time) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);

            pst.setString(1, login_ID);
            pst.setString(2, Customer_Name);
            pst.setDouble(3, Double.parseDouble(Customer_Age));
            pst.setString(4, Customer_Occupation);
            pst.setString(5, Credit_Card_Number);
            pst.setInt(6, Order_ID);
			pst.setString(7, SimpleDateFormat.format(current_date.getTime()));
			pst.setString(8, SimpleDateFormat.format(Expected_Delivery_Date));
            pst.setString(9, SimpleDateFormat.format(Actual_Delivery_Date));
			pst.setString(10, rs2.getString("service_id"));
            pst.setString(11, rs2.getString("servicename"));
			pst.setString(12,rs2.getString("review_rating"));
            pst.setString(13, Integer.toString(rand.nextInt(10000)));
			pst.setString(14, Delivery_Type);
            pst.setString(15, Delivery_Zip_Code);
			pst.setString(16, delornot[Order_Delivered_on_Time]);
            pst.setInt(17, Order_Returned);
			pst.setInt(18, Order_Delivered_on_Time);
            pst.execute();
			
			}
        } 
		catch (Exception e) 
		{
            System.out.println(e.getMessage());
        }
    }
	///adding after integration
	
	public static boolean deleteorder(int id)
	{
		ResultSet rs = null;
		try
		{
			getConnection();
			Statement stmt = conn.createStatement();
			String selectWarehouseQuery = "Delete from orders_services where id='" + id + "';";
			PreparedStatement pst = conn.prepareStatement(selectWarehouseQuery);
			pst.executeUpdate();
			
					
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	public static boolean insertservice(String producttype,String productId,String productName,String productImage,String productDiscount,double productPrice,String address,String city,String state,String zipcode,String phone)
	{
        try 	
		{
			
            getConnection();
			Statement stmt = conn.createStatement();
			
			Random rand = new Random();
			int rating = rand.nextInt(5);
			
            String insertIntoCustomerOrderQuery = "insert into services( servicetype ,id ,servicename ,serviceprice ,serviceimage ,url ,rating ,address ,city ,zipcode ,state ,phonenumber ,discount) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";


            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);

            pst.setString(1, producttype);
            pst.setString(2, productId);
            pst.setString(3, productName);
            pst.setDouble(4, productPrice);
            pst.setString(5,productImage);
            pst.setString(6, "");
			pst.setString(7,String.valueOf(rating));
			pst.setString(8,address);
            pst.setString(9,city);
			pst.setString(10, zipcode);
			pst.setString(11, state);
            pst.setString(12, phone);
			pst.setString(13,productDiscount);
            
			
            pst.execute();
			
			
        } 
		catch (Exception e) 
		{
            System.out.println(e.getMessage());
			return false;
        }
		return true;
    }
	
	
	public static boolean updateservice(String producttype,String productId,String productName,String productImage,String productDiscount,double productPrice,String address,String city,String state,String zipcode,String phone)
	{
        try 	
		{
			
            getConnection();
			Statement stmt = conn.createStatement();
			
			Random rand = new Random();
			int rating = rand.nextInt(5);
			
            String insertIntoCustomerOrderQuery = "update services set servicetype=?,servicename=? , serviceprice=? , serviceimage=? , url=? , rating=? , address=? , city=? , zipcode=? , state=? , phonenumber=? , discount=? where id=?;";


            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);

            pst.setString(1, producttype);
            
            pst.setString(2, productName);
            pst.setDouble(3, productPrice);
            pst.setString(4,productImage);
            pst.setString(5, "");
			pst.setString(6,String.valueOf(rating));
			pst.setString(7,address);
            pst.setString(8,city);
			pst.setString(9, zipcode);
			pst.setString(10, state);
            pst.setString(11, phone);
			pst.setString(12,productDiscount);
			pst.setString(13, productId);
            
			
            pst.execute();
			
			
        } 
		catch (Exception e) 
		{
            System.out.println(e.getMessage());
			return false;
        }
		return true;
    }
	
		public static boolean deleteorder(String id)
	{
		ResultSet rs = null;
		try
		{
			getConnection();
			Statement stmt = conn.createStatement();
			String selectWarehouseQuery = "Delete from services where id='" + id + "';";
			PreparedStatement pst = conn.prepareStatement(selectWarehouseQuery);
			pst.executeUpdate();
			
					
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	
		public static List<String> getServiceCity()
	{
		
		List<String> llist = new LinkedList<String> ();
		try
		{
			getConnection();

			String selectOneService = "SELECT Distinct(city) FROM services;";
			PreparedStatement pst = conn.prepareStatement(selectOneService);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				
				llist.add(rs.getString("city"));
				
			}
		}
		catch(Exception e)
		{
		}
		return llist;
	}
	
	public static HashMap<String, Services> getAllCityServices(String city)
	{
		HashMap<String, Services> hm=new HashMap<String, Services>();
		try
		{
			getConnection();

			String selectServices = "SELECT * FROM services WHERE city = '" + city + "';";
			PreparedStatement pst = conn.prepareStatement(selectServices);
			// pst.setString(1, servicetype);
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				Services services = new Services(rs.getString("servicetype"), rs.getString("id"), rs.getString("servicename"), rs.getDouble("serviceprice"), rs.getString("serviceimage"), rs.getString("url"), rs.getString("rating"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("address"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getString("phonenumber"), rs.getString("discount"));
				hm.put(rs.getString("id"), services);
				services.setId(rs.getString("id"));
			}
		}
		catch(Exception e)
		{
		}
		return hm;
	}
	

	
	public static ResultSet getallserviceOrders()
	{
		ResultSet rs = null;
		try
		{
			getConnection();
			Statement stmt = conn.createStatement();
			String selectWarehouseQuery = "SELECT order_id FROM orders;";
			rs = stmt.executeQuery(selectWarehouseQuery);
			
			int count=0;
			String condition="";
			
			while (rs.next())
				
			{
				if(count==0){
					condition=condition+"('"+rs.getString("order_id")+"'";
					count=count+1;
				}
				else{
					condition=condition+",'"+rs.getString("order_id")+"'";
					
				}
            }
			
			condition=condition+")";
			
			selectWarehouseQuery = "SELECT * FROM orders_services where order_id in " + condition + ";";
			rs = stmt.executeQuery(selectWarehouseQuery);
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	
	public static HashMap<String, String> selectDailyTransaction() 
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		try 
		{
			getConnection();
			String selectAcc = "select count(issued_date) as count,issued_date from orders order by issued_date; ";
			PreparedStatement pst = conn.prepareStatement(selectAcc);
			ResultSet rs = pst.executeQuery();

			int i = 0;
			while (rs.next()) 
			{
				
				i++;
				hm.put(rs.getString("issued_date"), rs.getString("count"));
				
			}
		} 
		catch (Exception e) 
		{
		}
		return hm;
    }
	
	

}