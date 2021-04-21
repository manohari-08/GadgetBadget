package model;
import java.sql.*; 

public class User {
	
	private Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "");
		}catch (Exception e){
			e.printStackTrace();
		}
		return con;
	 }
	
	public String createUser(String userID, String LastName, String FirstName, String Email, String Password, String Country, String ContactNumber){
		String output = "";
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for inserting."; 
			}
			// create a prepared statement
			String query = " insert into user_ (`userID`,`LastName`,`FirstName`,`Email`,`Password`,`Country`,`ContactNumber`)" + " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, LastName);
			preparedStmt.setString(3, FirstName);
			preparedStmt.setString(4, Email);
			preparedStmt.setString(5, Password);
			preparedStmt.setString(6, Country);
			preparedStmt.setString(7, ContactNumber);
			// execute the statement
	 
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e){
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	 }
	
	public String updateUser(String userID, String LastName, String FirstName, String Email, String Password, String Country, String ContactNumber){
		 
		String output = "";
		 
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for updating."; 
			}
			// create a prepared statement
			String query = "UPDATE user_ SET LastName=?,FirstName=?,Email=?,Password=?,Country=?,ContactNumber=? WHERE userID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
			preparedStmt.setString(1, LastName);
			preparedStmt.setString(2, FirstName);
			preparedStmt.setString(3, Email);
			preparedStmt.setString(4, Password);
			preparedStmt.setString(5, Country);
			preparedStmt.setString(6, ContactNumber);
			preparedStmt.setInt(7, Integer.parseInt(userID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		 	}catch (Exception e){
		 		output = "Error while updating the item.";
		 		System.err.println(e.getMessage());
		 	}
		 	return output;
		 }

	public String deleteUser(String itemID){
	 
		String output = "";
		try{
	 
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for deleting."; 
			}
			// create a prepared statement
			String query = "delete from items where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}catch (Exception e){
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	 }
	
	//to read all user
	public String readAllUser(){
		
		String output = "";
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for reading."; 
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>userID</th><th>LastName</th>" +
					"<th>FirstName</th>" +
					"<th>Email</th>" +
					"<th>Password</th>" +
					"<th>Country</th>" +
					"<th>ContactNumber</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from user_";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()){
				String userID = Integer.toString(rs.getInt("userID"));
				String LastName = rs.getString("LastName");
				String FirstName = rs.getString("FirstName");
				String Email = rs.getString("Email");
				String Password = rs.getString("Password");
				String Country = rs.getString("Country");
				String ContactNumber = rs.getString("ContactNumber");
				// Add into the html table
				output += "<tr><td>" + userID + "</td>";
				output += "<td>" + LastName + "</td>";
				output += "<td>" + FirstName + "</td>";
				output += "<td>" + Email + "</td>";
				output += "<td>" + Password + "</td>";
				output += "<td>" + Country + "</td>";
				output += "<td>" + ContactNumber + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + 
						"<td><form method='post' action='items.jsp'>" + 
						"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + 
						"<input name='itemID' type='hidden' value='" + userID + "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}catch (Exception e){
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	 }
	
}
