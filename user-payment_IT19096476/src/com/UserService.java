package com;
import model.User;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService {
	
	User userObj = new User();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createUser(@FormParam("userID") String userID,
	@FormParam("LastName") String LastName,
	@FormParam("FirstName") String FirstName,
	@FormParam("Email") String Email,
	@FormParam("Password") String Password,
	@FormParam("Country") String Country,
	@FormParam("ContactNumber") String ContactNumber)
	{
	String output = userObj.createUser(userID, LastName, FirstName, Email, Password, Country, ContactNumber);
	return output;
	}

	
}

