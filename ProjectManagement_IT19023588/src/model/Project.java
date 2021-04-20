package model;

import java.sql.*; 

public class Project {
	
	
	//A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		
		
		//Insert Project Details
		public String insertProj(int ProjId, String ProjTitle, String projDesc)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into project(`ProjId`,`ProjTitle`,`projDesc`)"
		 + " values (?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 //preparedStmt.setInt(1, 0);
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, ProjTitle);
		 preparedStmt.setString(3, projDesc);
		 //preparedStmt.setString(5, desc);
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		
		
		public String readProj()
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for reading."; }
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Item Code</th><th>Project ID</th>" +
		 "<th>Project Title</th>" +
		 "<th>project Description</th>" +
		 "<th>Update</th><th>Remove</th></tr>";

		 String query = "select * from project";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
		 // iterate through the rows in the result set
		 while (rs.next())
		 {
		 String ProjId = Integer.toString(rs.getInt("ProjId"));
		 String ProjTitle = rs.getString("ProjTitle");
		 String projDesc = rs.getString("projDesc");
//		 String itemPrice = Double.toString(rs.getDouble("itemPrice"));
//		 String itemDesc = rs.getString("itemDesc");
		 // Add into the html table
		 output += "<tr><td>" + ProjId + "</td>";
		 output += "<td>" + ProjTitle + "</td>";
		 output += "<td>" + projDesc + "</td>";
//		 output += "<td>" + itemDesc + "</td>";
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
		 + "<td><form method='post' action='project.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
	  + "</form></td></tr>";
		 }
		 con.close();
		 // Complete the html table
		 output += "</table>";
		 }
		 catch (Exception e)
		 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		
		

	
	

}
