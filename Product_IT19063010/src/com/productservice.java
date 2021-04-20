package com;
import model.product;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
//For REST Service
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
}

