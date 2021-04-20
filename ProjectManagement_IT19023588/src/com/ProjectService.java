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
	public String insertItem(@FormParam("ProjId") int ProjId, 
	 @FormParam("ProjTitle") String ProjTitle, 
	 @FormParam("projDesc") String projDesc) 
	{ 
	 String output = projObj.insertProj(ProjId, ProjTitle, projDesc); 
	return output; 
	}

	
	
	
	

}
