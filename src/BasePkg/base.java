package BasePkg;

import files.ReUsableMethods;
import io.restassured.filter.session.SessionFilter;

public class base {
	
	protected static String CommentID;
	protected static String JIRASessionID ;
	protected static String ProjectID;
	protected static String JIRAName ;
	protected static String IssueKeyID;
	protected static String IssueID;
	protected static SessionFilter session = new SessionFilter();
	protected static String ProjecName = ReUsableMethods.getUniqueRandomText();
	protected static String ProjetKey  = ReUsableMethods.getUniqueRandomKey();
	
	
	/*
	static String CommentID;
	static String JIRASessionID ;
	static String ProjectID;
	static String JIRAName ;
	static String IssueKeyID;
	static String IssueID;
	static SessionFilter session = new SessionFilter();
	static String ProjecName = ReUsableMethods.getUniqueRandomText();
	static String ProjetKey  = ReUsableMethods.getUniqueRandomKey();
	*/
	

}
