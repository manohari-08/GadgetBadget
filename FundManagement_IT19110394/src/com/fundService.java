package com;


import model.fund;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/fund")
public class fundService
	{
	fund fundObj = new fund();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	
return fundObj.readfund();
	}

//@Path("/fund")
//public class fundService
//{
//fund fundObj = new fund();
//@GET
//@Path("/")
//@Produces(MediaType.TEXT_HTML)
//public String updatefund()
//{
//	
//return fundObj.();
//}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updatefund(String userData)
	{
	//Convert the input string to a JSON object
	JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
	//Read the values from the JSON object
	String ID = userObject.get("ID").getAsString();
	String Funders_name = userObject.get("Funders_name").getAsString();
	String Project_name = userObject.get("Project_name").getAsString();
	String Amount = userObject.get("Amount").getAsString();

	String output = fundObj.updatefund(ID, Funders_name, Project_name,Amount );
	return output;
	}


@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deletefund(String userData)
{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(userData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String ID = doc.select("ID").text();
	String output = fundObj.deletefund(ID);
	return output;
	}


//
//@PUT
//@Path("/")
//@Consumes(MediaType.APPLICATION_JSON)
//@Produces(MediaType.TEXT_PLAIN)
//public String updatefund(String Data)
//{​​​​​
////Convert the input string to a JSON object
//JsonObject fundObject = new JsonParser().parse(Data).getAsJsonObject();
////Read the values from the JSON object
//String ID= fundObject.get("ID").getAsString();
//String Funders_name = fundObject.get("Funders_name").getAsString();
//String Project_name = fundObject.get("Project_name").getAsString();
//String Amount = fundObject.get("Amount").getAsString();
//
//String output = fundObj.updatefund(ID, Funders_name, Project_name,Amount );
//return output;
//}​​​​​




@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)

public String insertfund(@FormParam("ID") String ID,
		@FormParam("Funders_name") String Funders_name,
		@FormParam("Project_name") String Project_name,
		@FormParam("Amount") String Amount)
	{
	String output = fundObj.insertfund(ID, Funders_name, Project_name, Amount);
	return output;
}



}











//@path("/fund")
//public class fundService{
//
//	fund fundObj = new fund();
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN)
//
//	public String insertfund(@FormParam("ID") String ID,
//	@FormParam("Funders_name") String Funders_name,
//	@FormParam("Project_name") String Project_name,
//	@FormParam("Amount") String Amount)
//	{
//	String output = fundObj.insertfund(ID, Funders_name, Project_name, Amount);
//	return output;
//	}
//
//	 }
