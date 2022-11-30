package CRUD_Operaions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
import files.payload;

public class MyFristTestCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// validate if Add Place API is workimg as expected 
		//Add place-> Update Place with New Address -> Get Place to validate if New address is present in response
		
		//given - all input details 
		//when - Submit the API -resource,http method
		//Then - validate the response
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		JsonPath js =  
		given()
		.log().all()		
		.header("Content-Type","application/json")
		.body(payload.AddPlace())
		
		.when().post("maps/api/place/add/json")
		
		.then()
		.assertThat().statusCode(200)
		.extract().response().jsonPath();
	
		System.out.println(js.getString("place_id"));
		
		String placeId = js.getString("placeId");

	

		
		
		
		
		//Update Place
		String newAddress = "Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").
		when().put("maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		
	String getPlaceResponse=	given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id",placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
	
//	JsonPath js1=ReUsableMethods.rawToJson(getPlaceResponse);
	
	//String actualAddress =js1.getString("address");
//	System.out.println(actualAddress);
	//Assert.assertEquals(actualAddress, "Pacific ocean");
	//Cucumber Junit, Testng
	
	
	
	
	
	
	
	
	

		
		
		
		
		
		
		
		
		
		
		
	}

}
