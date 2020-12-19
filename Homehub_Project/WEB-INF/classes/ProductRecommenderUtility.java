import java.io.*;
import java.sql.*;
import java.io.IOException;
import java.util.*;

public class ProductRecommenderUtility{

	static Connection conn = null;
    static String message;

	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/homehub","root","12345");
			message="Successfull";
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

	public HashMap<String,String> readOutputFile(){


		String TOMCAT_HOME = System.getProperty("catalina.home");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
		HashMap<String,String> prodRecmMap = new HashMap<String,String>();
		try {

            br = new BufferedReader(new FileReader(new File(TOMCAT_HOME+"\\webapps\\Homehub_Project\\MatrixFactorization.csv")));
            while ((line = br.readLine()) != null) {


                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				prodRecmMap.put(prod_recm[0],prod_recm[1]);
            }

		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
		}

		return prodRecmMap;
	}

	public static Services getProduct(String product){
		Services services = new Services();
		try
		{
			String msg = getConnection();
			String selectProd="select * from  services where id=?";
			PreparedStatement pst = conn.prepareStatement(selectProd);
			pst.setString(1,product);
			ResultSet rs = pst.executeQuery();

			while(rs.next())
			{
				// prodObj = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				services = new Services(rs.getString("servicetype"), rs.getString("id"), rs.getString("servicename"), rs.getDouble("serviceprice"), rs.getString("serviceimage"), rs.getString("url"), rs.getString("rating"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("address"), rs.getString("city"), rs.getString("zipcode"), rs.getString("state"), rs.getString("phonenumber"), rs.getString("discount"));
			}
			rs.close();
			pst.close();
			conn.close();
		}
		catch(Exception e)
		{
		}
		return services;
	}
}
