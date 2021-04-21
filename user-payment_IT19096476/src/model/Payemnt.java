package model;

import java.sql.*; 

public class Payemnt {
	
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
	
	public String makePayment(String paymentID, String buyerID, String sellerID, String productID, String Ammount, String Date){
		String output = "";
		try{
			Connection con = connect();
			if (con == null){
				return "Error while connecting to the database for inserting."; 
			}
			// create a prepared statement
			String query = " insert into user_ (`paymentID`,`buyerID`,`sellerID`,`productID`,`Ammount`,`Date`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, buyerID);
			preparedStmt.setString(3, sellerID);
			preparedStmt.setString(4, productID);
			preparedStmt.setString(5, Ammount);
			preparedStmt.setString(6, Date);
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
