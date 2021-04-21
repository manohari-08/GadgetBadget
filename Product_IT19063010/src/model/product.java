package model;
import java.sql.*;
public class product
{ //A common method to connect to the DB
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");

 //Provide the correct details: DBServer/DBName,username, password
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/product", "root", "");
 }
 catch (Exception e)
 {e.printStackTrace();}
 return con;
 }





//insert
public String insertproduct(String code, String name, String price, String desc)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for inserting."; }
 
 // create a prepared statement
 String query = " insert into producttable(`code`, `name`, `price`, `desc`)" + " values (?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);

 // binding values
 preparedStmt.setString(1, code);
 preparedStmt.setString(2, name);
 preparedStmt.setDouble(3, Double.parseDouble(price));
 preparedStmt.setString(4, desc);
 
// execute the statement
 preparedStmt.execute();
 con.close();
 output = "Inserted successfully";
 }
 catch (Exception e)
 {
 output = "Error while inserting the product.";
 System.err.println(e.getMessage());
 }
 return output;
 }





//read

public String readproduct()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {return "Error while connecting to the database for reading."; }
 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>product Code</th><th>product Name</th>" +"<th>product Price</th>" +"<th>product Description</th>" +"<th>Update</th><th>Remove</th></tr>";

 String query = "select * from producttable";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 
 
 
 // iterate through the rows in the result set
 while (rs.next())
 {

 String code = rs.getString("code");
 String name = rs.getString("name");
 String price = Double.toString(rs.getDouble("price"));
 String desc = rs.getString("desc");
 
 // Add into the html table
 output += "<tr><td>" + code + "</td>";
 output += "<td>" + name + "</td>";
 output += "<td>" + price + "</td>";
 output += "<td>" + desc + "</td>";
 // buttons
 output += "<td> <input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='product.jsp'>" + "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>" + "</form></td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the products.";
 System.err.println(e.getMessage());
 }
 return output;
 }



//update
public String updateproduct(String code, String name, String price, String desc)
{
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 
	 
	 // create a prepared statement update
	 
	 
	 String query = "UPDATE producttable SET name=?,price=?,desc=? WHERE code=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, code);
	 preparedStmt.setString(2, name);
	 preparedStmt.setDouble(3, Double.parseDouble(price));
	 preparedStmt.setString(4, desc);

	 
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while updating the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String deleteproduct(String code)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 
	 
	 
	 
	 
	 
	 // create a prepared statement for delete
	 
	 String query = "delete from producttable where code=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(code));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the product.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 
