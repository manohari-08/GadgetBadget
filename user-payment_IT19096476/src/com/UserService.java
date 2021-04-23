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
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String userData)
	{
	//Convert the input string to a JSON object
	JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	String userID = userObject.get("userID").getAsString();
	String LastName = userObject.get("LastName").getAsString();
	String FirstName = userObject.get("FirstName").getAsString();
	String Email = userObject.get("Email").getAsString();
	String Password = userObject.get("Password").getAsString();
	String Country = userObject.get("Country").getAsString();
	String ContactNumber = userObject.get("ContactNumber").getAsString();
	String output = userObj.updateUser(userID, LastName, FirstName, Email, Password, Country, ContactNumber);
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
	String userID = doc.select("itemID").text();
	String output = userObj.deleteUser(userID);
	return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAllUser()
	{
	return userObj.readAllUser();
	}

	
}

