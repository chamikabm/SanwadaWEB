package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;

public class Application extends Controller{
	
	/***
	 * This method is used to generate the Index view of the Sanwda Application
	 * @return view of the specified render function.
	 */
	 public static Result index() {
	        return ok(index.render());
	    }

}
