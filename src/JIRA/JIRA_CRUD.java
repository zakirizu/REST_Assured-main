package JIRA;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;import static io.restassured.RestAssured.given;import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.Stack;

import static org.hamcrest.Matchers.*;import files.JIRAPayLoad;
import files.ReUsableMethods;
import files.payload;import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;import static io.restassured.RestAssured.*;import files.payload;
import io.restassured.RestAssured;

public class JIRA_CRUD {
static String CommentID;
static String JIRASessionID ;
static String ProjectID;
static String JIRAName ;
static String IssueKeyID;
static String IssueID;
static SessionFilter session = new SessionFilter();
static String ProjecName = ReUsableMethods.getUniqueRandomText();
static String ProjetKey  = ReUsableMethods.getUniqueRandomKey();

@Test(priority = 1)
public static void LoginToJIRA() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	
	 given()//.log().all()
	.headers("Content-Type","application/json")
	.body(JIRAPayLoad.getJIRALogin())
	
	.filter(session)
	.when()
	.post("/rest/auth/1/session")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();
	
	/* This is taken care by filter(session)
	JIRAName 	  = js.getString("session.name");
	JIRASessionID = js.getString("session.value");
	System.out.println("JIRA Session ID		= "+JIRASessionID);
	System.out.println("JIRA Session Name	= "+JIRAName);
	*/
}

@Test(priority = 2)
public static void createProject() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	 given()//.log().all()
	.headers("Content-Type","application/json")
	.body(JIRAPayLoad.getCreateProject(ProjecName,ProjetKey))
	
	.filter(session)
	.when()
	.post("/rest/api/2/project")
	
	.then()//.log().all()
	.assertThat().statusCode(201)
	.extract().response().jsonPath();
	
	ProjectID = js.getString("id");
	System.out.println("Project ID			= " +ProjectID);
	System.out.println("Project Name			= " +ProjecName);
}



@Test(priority = 3)
public static void createIssue() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	 given()
	.header("Content-Type","application/json")
	.body(JIRAPayLoad.getCreateIssue(ProjetKey))
	
	.filter(session)
	.when()
	.post("/rest/api/2/issue")
	
	.then()//.log().all()
	.assertThat().statusCode(201)
	.extract().response().jsonPath();
	
	IssueID    = js.getString("id");
	IssueKeyID = js.getString("key");
	System.out.println("Issue ID			= "+IssueID);
	System.out.println("Issue Key			= "+IssueKeyID);
	
}

@Test(priority = 4)
public static void addComment() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	 given()//.log().all()
	.headers("Content-Type", "application/json")
	.body(JIRAPayLoad.getAddComment("Automation Test Comments "))
	
	.filter(session)
	.when()
	.post("/rest/api/2/issue/"+IssueID+"/comment")
	
	.then()//.log().all()
	.assertThat().statusCode(201)
	.extract().jsonPath();
	
	CommentID = js.getString("id");
	System.out.println("Comment ID = "+CommentID);
	
}


@Test(priority = 5)
public static void UpdateComment() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	 given()//.log().all()
	.headers("Content-Type", "application/json")
	.body(JIRAPayLoad.getUpdateComment("Updated Automation Comments"))
	
	.filter(session)
	.when()
	.put("/rest/api/2/issue/"+IssueID+"/comment/"+CommentID+"")
	
	.then()
	.assertThat().statusCode(200)
	.extract().jsonPath();
}


/*
 * ******************************************************************************
 * ********************THIS IS TO VERIFY THE FILE UPLOAD ************************
 * ******* Header ismultipart/form-data and X-atlassion with no check************
 * ******************************************************************************
 */
@Test(priority = 6)
public static void AddAttachment() {
	//Create a File Class Object--This is bascially used to create a Object and sent file
	//In order to java know we are sending file we will be using file class 
	File fs = new File("C:\\Work\\RestAssured\\REST_Assured-main\\JIRA_Attachment.txt");
	
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js= 
	given().log().all()
	.header("Content-Type","multipart/form-data")
	.header("X-Atlassian-Token","no-check")
	.multiPart("file", fs)
	
	.filter(session)
	.when()
	.post("/rest/api/2/issue/"+IssueID+"/attachments")
	
	.then().log().all()
	.assertThat().statusCode(200)
	.extract().jsonPath();
		
}


	//iF YOU ENABLE THIS THEN YOU WILL NOT SEE ANYTHING IN THE UI 
@Test(priority = 10, enabled = true)
public static void DeleteProject() throws InterruptedException {
	
	Thread.sleep(40000);
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	
	given()
	.headers("Content-Type", "application/json")

	.filter(session)
	.when()
	.delete("/rest/api/2/project/"+ProjectID+"")
	
	.then()//.log().all()
	.assertThat().statusCode(204)
	.extract().response().jsonPath();
}
	
	
	
	/*
	 * ******************************************************************************
	 * ****** THIS IS TO DELTE THE PROJECTS THAT WERE CREATED BY MISTAKE ************
	 * ******************************************************************************
	 *
@Test(dataProvider = "getdata")
public static void DeletesProject(String ID) {
	
	SessionFilter localSession = new SessionFilter();
	RestAssured.baseURI = "http://localhost:8080";
	
	
	 given()//.log().all()
	.headers("Content-Type","application/json")
	.body(JIRAPayLoad.getJIRALogin())
	
	.filter(localSession)
	.when()
	.post("/rest/auth/1/session")
	
	.then()//.log().all()
	.assertThat().statusCode(200);
		
	
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	
	given().log().all()
	.headers("Content-Type", "application/json")

	.filter(localSession)
	.when()
	.delete("/rest/api/2/project/"+ID+"")
	
	.then().log().all()
	.assertThat().statusCode(204)
	.extract().response().jsonPath();
}

@DataProvider(name = "getdata")
public static Object[] data(){
	return new Object[] { "ZR5641041","ZR5713013","ZR5840040","ZR5908008","ZR5935035",
			"ZR0008008",
			"ZR0406006",
			"ZR0556056",
			"ZR0906006",
			"ZR0946046",
			"ZR1757057",
			"ZR1820020",
			"DLT",
			"DP",
			"DPA"
			
		};
	
}

	
	*/
	
	
	
	
	
	
	
	
}
