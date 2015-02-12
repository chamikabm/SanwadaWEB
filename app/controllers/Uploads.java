package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;

public class Uploads extends Controller{
	
	/**
	 * This method is used to upload .txt files to the server for summarization. 
	 * @return - Indication of the process [HTTP Codes]
	 */
	public static Result upload()
    {
		  MultipartFormData body = request().body().asMultipartFormData();
		  FilePart file = body.getFile("txtFile");
		  if (file != null) {
		    String fileName = file.getFilename();
		    String contentType = file.getContentType(); 
		    File fileRecieved = file.getFile();
		    
		    final String filePath="public/files/";
		
		    try {
		    	
		    	
		    	File f = new File(filePath+""+fileName);
		    	if(f.exists()){
		    		System.out.println("File is already available!");
		    		f.delete();
		    		System.out.println("Existing file deleted!");
		    		FileUtils.moveFile(fileRecieved, new File(filePath, fileName));
		    		System.out.println("New File is created..........");		    		
		    	}else{
		    		FileUtils.moveFile(fileRecieved, new File(filePath, fileName));
		    		System.out.println("New File is created..........");
		    	}
	           
	        } catch (IOException ioe) {
	            System.out.println("Problem operating on filesystem");
	        }
		  
		    return ok("File uploaded! + File Name: "+fileName);
		    
		  } else {
		    flash("error", "Missing file");
		    return redirect(routes.Application.index());    
		  }
   
    }

}
