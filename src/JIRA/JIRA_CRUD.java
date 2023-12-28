package JIRA;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import BasePkg.base;
import static io.restassured.RestAssured.given;import static org.hamcrest.Matchers.equalTo;
import java.io.File;
import java.util.Calendar;
import java.util.Stack;
import static org.hamcrest.Matchers.*;import files.JIRAPayLoad;
import files.ReUsableMethods;
import files.payload;import io.restassured.RestAssured;
import io.restassured.filter.Filter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;import static io.restassured.RestAssured.*;import files.payload;
import io.restassured.RestAssured;

public class JIRA_CRUD extends base{
	static long startTime = Calendar.getInstance().getTimeInMillis();
	static Filter session = null;

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
public static void addComment01() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	 given()//.log().all()
	.headers("Content-Type", "application/json")
	.body(JIRAPayLoad.getAddComment("01 First Automation Test Comments "))
	
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
 * ********************THIS IS TO PUT METHOD--NO NEED OF HEADER/BODY*************
 * ********************THIS WILL PRINT ALL FIELDS IN RESPONSE******************** 
  * ******************************************************************************
 */
@Test(priority = 6)
public static void getAll_IssueDetails() {
	
	 RestAssured.baseURI = "http://localhost:8080";
	
	 given()
		
	.filter(session)
	.when()
	.get("/rest/api/2/issue/"+IssueID+"")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();
}
/*
 * ******************************************************************************
 * ********************LIMIT THE RESPONSE AND DEFINE WHAT WE WANT TO SEE*********
 * ********************THIS IS TO PUT METHOD--NO NEED OF HEADER/BODY*************
 * ********************THIS WILL PRINT ONLY COMMENTES FIELDS IN RESPONSE********* 
  * *****************************************************************************
 */
@Test(priority = 7)
public static void getComments_Details_Only_Of_An_Issue() {
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	given()
	.queryParam("fields", "comment")
	
	.filter(session)
	.when()
	.get("/rest/api/2/issue/"+IssueID+"")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();
	
	String commentAdded = js.getString("fields.comment.comments[0].body");
	System.out.println("Reading the Comment from JSON = "+commentAdded);
}



/*
 * ******************************************************************************
 * ********************THIS IS TO VERIFY THE FILE UPLOAD ************************
 * ******* Header ismultipart/form-data and X-atlassion with no check************
 * ******************************************************************************
 */
@Test(priority = 8)
public static void BypassHTTPSCertification() {
	//Create a File Class Object--This is bascially used to create a Object and sent file
	//In order to java know we are sending file we will be using file class 
	File fs = new File("C:\\Work\\RestAssured\\REST_Assured-main\\JIRA_Attachment.txt");
	
	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js= 
	given()//.log().all()
	.header("Content-Type","multipart/form-data")
	.header("X-Atlassian-Token","no-check")
	.multiPart("file", fs)
	
	.filter(session)
	.when()
	.post("/rest/api/2/issue/"+IssueID+"/attachments")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().jsonPath();
}


/*
 * ******************************************************************************
 * ********************THIS IS TO BY PASS THE HTTPS CERTIFICATE******************
 * ******************************************************************************
 */
@Test(priority = 9, enabled = false)
public static void AddAttachment() {

	RestAssured.baseURI = "http://localhost:8080";
	JsonPath js = 
	 given()//.log().all()
	 
	 //******************* BY PASS HTTPS VALIDATION - RELAXED HTTPS VALIDATION****
	 .relaxedHTTPSValidation()
	 
	.headers("Content-Type", "application/json")
	.body(JIRAPayLoad.getUpdateComment("Updated Automation Comments"))
	
	.filter(session)
	.when()
	.put("/rest/api/2/issue/"+IssueID+"/comment/"+CommentID+"")
	
	.then()
	.assertThat().statusCode(200)
	.extract().jsonPath();
}


	//iF YOU ENABLE THIS THEN YOU WILL NOT SEE ANYTHING IN THE UI 
@Test(priority = 10, enabled = true)
public static void DeleteProject() throws InterruptedException {
	
	Thread.sleep(30000);
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
	
	
	long endtime = Calendar.getInstance().getTimeInMillis();	
	long totalTime = ((endtime-startTime)/1000) ;
	System.out.println("Total Time Taken to Execute in Seconds = " +totalTime);
	
	
}
	


	
	
}


//System.out.println
	
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
	
	
	
	
	
	

