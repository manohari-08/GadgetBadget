package com;

import model.Project; 

//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


@Path("/Project") 
public class ProjectService {
	
	Project projObj = new Project(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readItems() 
	 { 
		return projObj.readProj();
	 } 
	
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("ProjId") String ProjId, 
							 @FormParam("ProjTitle") String ProjTitle, 
							 @FormParam("projDesc") String projDesc,
							 @FormParam("location") String location,
							 @FormParam("sector") String sector,
							 @FormParam("projOwner") String projOwner,
							 @FormParam("projStage") String projStage,
							 @FormParam("projectBudget") String projectBudget
							 ) 
	{ 
		 String output = projObj.insertProj(ProjId, ProjTitle, projDesc,location,sector,projOwner,projStage,projectBudget); 
		 return output; 
	}
	
	
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String Data){ 
		//Convert the input string to a JSON object 
		 JsonObject projectObject = new JsonParser().parse(Data).getAsJsonObject(); 
		 
		//Read the values from the JSON object
		 String ProjId = projectObject.get("ProjId").getAsString(); 
		 String ProjTitle = projectObject.get("ProjTitle").getAsString(); 
		 String projDesc = projectObject.get("projDesc").getAsString(); 
		 String location = projectObject.get("location").getAsString();
		 String sector = projectObject.get("sector").getAsString(); 
		 String projOwner = projectObject.get("projOwner").getAsString();
		 String projStage = projectObject.get("projStage").getAsString();
		 String projectBudget = projectObject.get("projectBudget").getAsString();
		 
		 String output = projObj.updateProject(ProjId, ProjTitle, projDesc,location,sector,projOwner,projStage,projectBudget); 
		 return output; 
	}
	
	
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String Data) 
	{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(Data, "", Parser.xmlParser()); 
		 
		//Read the value from the element <itemID>
		 String ProjId = doc.select("ProjId").text(); 
		 String output = projObj.deleteItem(ProjId); 
		 return output; 
	}

	
	
	
	
	
	
	

	
	
	
	

}
