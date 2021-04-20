package com;
import model.product;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/product")
public class productservice {
	product productObj = new product();
	@GET
	@Path("/product")
	@Produces(MediaType.TEXT_HTML)
	public String readproducts()
	{
		 return productObj.readproduct();
		 }
	//insert(add,post)
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertproduct(@FormParam("code") String code,
	@FormParam("name") String name,
	@FormParam("price") String price,
	@FormParam("desc") String desc)
	{
	String output = productObj.insertproduct(code, name,price, desc);
	return output;
	}
	
	
	
	//update(get)
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateproduct(String productData)
	{
		
	//Convert the input string to a JSON object
	 JsonObject productObject = new JsonParser().parse(productData).getAsJsonObject();
	 
	//Read the values from the JSON object

	 String code = productObject.get("code").getAsString();
	 String name = productObject.get("name").getAsString();
	 String price = productObject.get("price").getAsString();
	 String desc = productObject.get("desc").getAsString();
	 String output = productObj.updateproduct(code, name, price,desc);
	return output;
	}
	
	
	
	//delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteproduct(String productData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(productData, "", Parser.xmlParser());

	//Read the value from the element <productID>
	 String code = doc.select("code").text();
	 String output = productObj.deleteproduct(code);
	return output;
	}
	
}

