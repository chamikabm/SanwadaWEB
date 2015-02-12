package controllers;

import classification.testInstance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.mvc.Controller;
import play.mvc.Result;
import play.libs.Json;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;

public class Summarization extends Controller{
	
	/**
	 * This method is used to summarize a upload file to the Sanwada server.
	 * @return - Indication for the successfull completion of the summarization [200 OK]
	 * @throws Exception
	 */
	public static Result summarize() throws Exception{
		System.out.println("AAAAAAAA");
		JsonNode json = request().body().asJson();
		ObjectNode result = Json.newObject(); 
		
		  if(json == null) {
		    return badRequest("Expecting Json data");
		  } else {
			  
			  String tagString = json.findPath("tagset").asText();			   
			  String[] tagset = tagString.replaceAll("'","").split(",");
			  
			  String path = json.findPath("uploadFileURI").asText();			  
			  System.out.println("Path : "+path);
			  
			  String fileName = json.findPath("fileName").asText();
			  System.out.println("File name :"+fileName);
			  
			  ArrayList<String> tagList =new ArrayList<String>();
			  
			  tagList.addAll(Arrays.asList(tagset));
			  
			  classification.testInstance instance = new testInstance();
			  
			  BufferedReader br1 = new BufferedReader(new FileReader(new File(path+""+fileName)));
              String line=" ";              

              Hashtable<String,ArrayList<String>> table = new Hashtable<String,ArrayList<String>>() ;
			  
			  
			  while((line = br1.readLine()) != null){
                  System.out.println(line);

                  String s = instance.getClassifiedDA(line);
                  
                  if(table.containsKey(s)){
                      ArrayList<String> temp = table.get(s);
                      temp.add(line);
                      table.put(s,temp);
                      
                  }else{
                      ArrayList<String> temp = new ArrayList<String>();
                      temp.add(s);
                      temp.add(line);
                      table.put(s,temp);
                  }
                  
			  }  
                  //Read Completed
                  
                  
                  BufferedWriter bw = new BufferedWriter(new FileWriter(new File("public/files/download.txt")));

                  for(Map.Entry<String, ArrayList<String>> entry : table.entrySet()) {

                      String key = entry.getKey();
                      ArrayList<String> list = entry.getValue();

                      if (key.equals("Statement") && !tagList.contains("1")) {
                          continue;
                      }else if (key.equals("Request/Command/Order")&& !tagList.contains("16")) {
                          continue;
                      }else if (key.equals("Abandoned/Uninterpretable/Other") && !tagList.contains("4")) {
                          continue;
                      }else if (key.equals("Open Question")&& !tagList.contains("9")) {
                          continue;
                      }else if (key.equals("Yes-No Question")&& !tagList.contains("5")) {
                          continue;
                      }else if (key.equals("Back-channel/Acknowledge")&& !tagList.contains("2")) {
                          continue;
                      }else if (key.equals("Opinion")&& !tagList.contains("3")) {
                          continue;
                      }else if (key.equals("Thanking")&& !tagList.contains("12")) {
                          continue;
                      }else if (key.equals("No Answer")&& !tagList.contains("15")) {
                          continue;
                      }else if (key.equals("Expressive")&& !tagList.contains("8")) {
                          continue;
                      }else if (key.equals("Yes Answers")&& !tagList.contains("6")) {
                          continue;
                      }else if (key.equals("Conventional Closing")&& !tagList.contains("7")) {
                          continue;
                      }else if (key.equals("Reject")&& !tagList.contains("10")) {
                          continue;
                      }else if (key.equals("Apology")&& !tagList.contains("11")) {
                          continue;
                      }else if (key.equals("Conventional Opening")&& !tagList.contains("13")) {
                          continue;
                      }else if (key.equals("Backchannel Question")&& !tagList.contains("14")) {
                          continue;
                      }

                      bw.write(list.get(0));
                      bw.newLine();
                      bw.newLine();
                      bw.flush();

                      for (int i = 1; i < list.size(); i++) {
                          bw.write(list.get(i));
                          bw.newLine();
                          bw.flush();
                      }

                      bw.newLine();
                      bw.newLine();
                      bw.flush();


                  }

                  bw.close();

			  
			  System.out.println(tagString);
			  
			  System.out.println("tagset : "+tagset[0]);
			  
			  result.put("tag0",path); 
			  
			  return ok(result);
			  
		  }
	
    }

}
