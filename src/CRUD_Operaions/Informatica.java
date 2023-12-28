package CRUD_Operaions;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.apache.http.impl.conn.DefaultHttpRoutePlanner;
import org.testng.Assert;
import files.ReUsableMethods;
import files.payload;
import io.restassured.RestAssured;

public class Informatica {
	static String SessionID;
	static String orgID;
	//static String Baseurl = "https://dm1-ap.informaticacloud.com";
	static String Baseurl = "https://qa-ma.mrel.infaqa.com";	
	//static String HostUrl = "https://apne1-apim.dm1-ap.informaticacloud.com";
	static String HostUrl = "https://apim-pod1.mrel.infaqa.com";


@Test(priority = 1)
public static void GettingSessionID() {
	
	
	RestAssured.baseURI = Baseurl;	
	JsonPath js =
	
	given()
	.header("Content-Type", "application/json")
	.body(payload.MrelLogin())
	
	.when()
	.post("identity-service/api/v1/Login")
	
	.then()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();	

	SessionID = js.getString("sessionId");
	orgID 	= js.getString("orgId");
	String Deployer = js.getString("effectiveRoles.Deployer");
	System.out.println("Session ID = "+SessionID);
	System.out.println("Org ID = "+orgID);
	System.out.println("Deployer ID = "+Deployer);
}



@Test(priority = 2)
public static void VerifyWhetherOrgExists() {
	
	RestAssured.baseURI = HostUrl;
	JsonPath js =
	given()//.log().all()
	.headers("Content-Type", "application/json","IDS-SESSION-ID",SessionID)
	.body(payload.VerifyAzureOrgExists())
	
	.when()
	.get("apimui/api/v0.4/org/"+orgID+"/provisioning")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();	
}


@Test(priority = 3)
public static void CreateGroup() {
	RestAssured.baseURI = HostUrl;
	JsonPath js = 
			
	given().log().all()
	.headers("Content-Type", "application/json", "IDS-SESSION-ID",SessionID)
	//.header(null)
	.body(payload.GroupCreation())
	
	.when()
	.post("/apimui/api/v0.4/org/"+orgID+"/group")
	
	.then().log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();
}



















}
