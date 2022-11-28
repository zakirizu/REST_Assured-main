package API_Basics;
import static org.hamcrest.Matchers.*;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class API_Methods {
	public static void main(String[] args) {
		System.out.println("BISMILLAH");
		//Given	-Input Details  
		//When	-Submit the API -- Resource, HTTP method 
		//Then	-Validate the Response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = 
		given()
		.log().all()
		.queryParam("key","qaclick123")
		.header("Content-Type","application/json")
		.body(payload.AddPlace())
		
	
		.when()
		.post("maps/api/place/add/json")
		
		.then()
		.assertThat().statusCode(200)
		.body("Connection",equalTo("Keep-Alive"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("The string is "+response);

	}

}
