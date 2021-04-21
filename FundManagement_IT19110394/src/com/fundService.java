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
//@GET
//@Path("/")
//@Produces(MediaType.TEXT_HTML)
//public String readItems()
//{
//return "Hello";
//}
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

public String readfund(@FormParam("ID") String ID,
@FormParam("Funders_name") String Funders_name,
@FormParam("Project_name") String Project_name,
@FormParam("Amount") String Amount)
{
String output = fundObj.readfund(ID, Funders_name, Project_name, Amount);
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
