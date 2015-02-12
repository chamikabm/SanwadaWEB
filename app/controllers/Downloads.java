package controllers;

import java.io.File;

import play.mvc.Controller;
import play.mvc.Result;

public class Downloads extends Controller{
	
	/**
	 * This method is used to download any content in the Sanwada application
	 * @param fileName - this is the file name of the file required to download.
	 * @return - this will generate the download file.
	 */
	public static Result download(String fileName)
    {

		 File tmp = new File("public/documents/"+fileName); 
		 response().setHeader("Content-Disposition", "attachment; filename="+fileName);
		 response().setContentType("mime/type");
		 
		 return ok(tmp);
		
    }
	
	public static Result downloadClassifiedFile(String fileName)
    {

		 File tmp = new File("public/files/"+fileName); 
		 response().setHeader("Content-Disposition", "attachment; filename="+fileName);
		 response().setContentType("mime/type");
		 
		 return ok(tmp);
		
    }

}
