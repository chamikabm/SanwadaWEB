package controllers;

import classification.testInstance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Classification extends Controller {
	
	
	/**
	 * This method is used to classify the given JSON object string in to 
	 * relevant tag.
	 * @return return is also be a JSON with relevant tag type for the input string.
	 * @throws Exception
	 */
	 public static Result getClassify() throws Exception{	
		 
		 System.out.println("getClassify() executing!");
		 JsonNode json = request().body().asJson();
		 ObjectNode result = Json.newObject(); 
		 
		 if(json == null) {
			    return badRequest("Expecting Json data");
		 } else {
				  
				  String tagString = json.findPath("inputText").asText();	
				  
				  System.out.println(tagString);
				  
				  classification.testInstance instance = new testInstance();
				  String tagType = instance.getClassifiedDA(tagString);
				  
				  result.put("tagType",tagType); 
				  
				  return ok(result);
				  
		 }
		 
	 }
	 

}
