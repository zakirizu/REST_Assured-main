package CRUD_Operaions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import files.payload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import files.payload;
import io.restassured.RestAssured;

public class QaClick {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//This is a Post Request So we are storing Data as Response
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
		//.body("scope",equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("The string is "+response);

	}

}
