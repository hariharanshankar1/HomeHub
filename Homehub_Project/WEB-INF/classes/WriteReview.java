import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
//once the user clicks writereview button from products page he will be directed
//to write review page where he can provide reqview for item rating reviewtext

public class WriteReview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        review(request, response);
    }

    protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            Utilities utility = new Utilities(request, pw);
            HttpSession session = request.getSession(true);

            if (!utility.isLoggedin()) {
                session.setAttribute("login_msg", "Please Login to Write a Review");
                response.sendRedirect("Login");
                return;
            }
            String productname = request.getParameter("name");
            String producttype = request.getParameter("type");
            String productmaker = request.getParameter("maker");
            String productprice = request.getParameter("price");
            String username = (String) session.getAttribute("username");
			String city_service = request.getParameter("city");
			String zip_service = request.getParameter("zip");
			
			HashMap<String, String> userval = new HashMap<String, String>();
			
			
			userval=MySqlDataStoreUtilitiesHomeHub.getuserdetails(username);
			
			
			
			String customer_id="";
			String apt_no="";
			String street_number="";
			String street_name="";
			String city="";
			String state="";
			String zip="";
			
			String address_id="";
			
			String card_number="";
			
			
			
			if(userval.get("address1")!=null){
			
				String[] address_split = userval.get("address1").split(",");
				address_id=address_split[0];
				apt_no=address_split[1];
				street_number=address_split[2];
				street_name=address_split[3];
				city=address_split[4];
				state=address_split[5];
				zip=address_split[6];
				card_number=userval.get("card_number1");
			
			}
			

            // on filling the form and clicking submit button user will be directed to submit review page
            utility.printHtml("Header.html");
            utility.printHtml("LeftNavigationBar.html");
            pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
            pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
            pw.print("<a style='color:#E78200; font-family: Comic Sans MS, cursive, sans-serif; font-weight:bold; 'font-size: 24px;'>Review</a>");
            pw.print("</h2><div class='entry'>");
            pw.print("<table class='gridtable'>");
            pw.print("<tr><td> Service Name: </td><td>");
            pw.print(productname.replace("_", " "));
            pw.print("<input type='hidden' name='productname' value='" + productname + "'>");
            pw.print("</td></tr>");


            pw.print("<tr><td> Service Category:</td><td>");
            pw.print(producttype);
            pw.print("<input type='hidden' name='producttype' value='" + producttype + "'>");
            pw.print("</td></tr>");

            pw.print("<tr><td> Service Price:</td><td>");
            pw.print(productprice);
            pw.print("<input type='hidden' name='productprice' value='" + productprice + "'>");
            pw.print("</td></tr>");

            pw.print("<tr><td> Retailer Name:</td><td>");
            pw.print("HomeHub");
            pw.print("<input type='hidden' name='retailerName' value='SmartPortables'>");
            pw.print("</td></tr>");

            pw.print("<tr><td> Retailer State:</td><td>");
            pw.print("IL");
            pw.print("<input type='hidden' name='retailerState' value='IL'>");
            pw.print("</td></tr>");

            pw.print("<tr><td> Service On Sale:</td><td>");
            pw.print("Yes");
            pw.print("<input type='hidden' name='productOnSale' value='Yes'>");
            pw.print("</td></tr>");

           
            pw.print("<input type='hidden' name='productmaker' value='Yes'>");
           
			
			
            pw.print("<input type='hidden' name='productrebate' value='Yes'>");
            pw.print("<table>");

            pw.print("<br/>");

            pw.print("<tr>");
            pw.print("<td> UserID: </td>");
            pw.print("<td>" + username + "</td>");
            pw.print("</tr>");


            pw.print("<tr>");
            pw.print("<td> User Age: </td>");
            pw.print("<td>");
            pw.print("<input type='text' name='userAge' value='"+userval.get("age")+"'> ");
            pw.print("</td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td> User Gender: </td>");
            pw.print("<td>");
            pw.print("<select name='userGender'>");
            pw.print("<option value='male' selected>Male</option>");
            pw.print("<option value='female'>Female</option>");
			pw.print("</td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td> User Occupation: </td>");
            pw.print("<td> <input type='text' name='userOccupation'></td>");
            pw.print("</tr>");
			
			pw.print("<tr>");
            pw.print("<td> User Street: </td>");
            pw.print("<td> <input type='text' name='userStreet' value='"+apt_no+" "+street_number+" "+street_name+"'></td>");
            pw.print("</tr>");
			
			pw.print("<tr>");
            pw.print("<td> User City: </td>");
            pw.print("<td> <input type='text' name='userCity' value='"+city+"'></td>");
            pw.print("</tr>");
			
			pw.print("<tr>");
            pw.print("<td> User State: </td>");
            pw.print("<td> <input type='text' name='userState' value='"+state+"'></td>");
            pw.print("</tr>");
			
			pw.print("<tr>");
            pw.print("<td> User Zipcode: </td>");
            pw.print("<td> <input type='text' name='userZipcode'  value='"+zip+"'></td>");
            pw.print("</tr>");
			
						
            pw.print("<tr><td> Store Zip:</td>");
            pw.print("<td> <input type='text' name='retailerpin' value='"+zip_service+"'></td>");
            pw.print("</tr>");

            pw.print("<tr><td> Store City:</td>");
            pw.print("<td> <input type='text' name='retailerCity' value='"+city_service+"'></td>");
            pw.print("</tr></table>");

            pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
            pw.print("<td>");
            pw.print("<select name='reviewrating'>");
            pw.print("<option value='1' selected>1</option>");
            pw.print("<option value='2'>2</option>");
            pw.print("<option value='3'>3</option>");
            pw.print("<option value='4'>4</option>");
            pw.print("<option value='5'>5</option>");
            pw.print("</td></tr>");

            pw.print("<tr>");
            pw.print("<td> Review Date: </td>");
            pw.print("<td> <input type='date' name='reviewdate'></td>");
            pw.print("</tr>");

            pw.print("<tr>");
            pw.print("<td> Review Text: </td>");
            pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
            pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");
//            pw.print("</td>");
//            pw.print("</tr>");

            pw.print("</h2></div></div></div>");
            utility.printHtml("Footer.html");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

    }
}