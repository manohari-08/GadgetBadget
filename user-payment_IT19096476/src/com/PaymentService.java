package com;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.Payemnt;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payemnt")
public class PaymentService {
	
	
	Payemnt paymentObj = new Payemnt();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("paymentID") String paymentID,
	@FormParam("buyerID") String buyerID,
	@FormParam("sellerID") String sellerID ,
	@FormParam("productID") String productID,
	@FormParam("Ammount") String Ammount,
	@FormParam("Date") String Date)
	{
	String output = paymentObj.makePayment(paymentID, buyerID, sellerID, productID, Ammount, Date);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String userID = doc.select("paymentID").text();
	String output = paymentObj.canclePayment(userID);
	return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllUser()
	{
	return paymentObj.getAllPayment();
	}



}
