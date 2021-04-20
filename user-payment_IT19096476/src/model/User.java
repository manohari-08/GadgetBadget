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
}
