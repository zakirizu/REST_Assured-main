package CRUD_Operaions;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import files.payload;

public class Get_Informatica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ValidateWithCorrectData();
	//	ValidateWithInCorrectCorrectData();
		
		
		

	}

	private static void ValidateWithInCorrectCorrectData() {
RestAssured.baseURI = "https://apigw-pod1.mrel.infaqa.com/t/apim.mrel.com";
		
		ValidatableResponse response = 
		given()
		.log().all()
		.queryParam("in","510510510510515500")
		.header("Content-Type","application/json")
		//.body(payload.AddPlace())
		
	
		.when()
		.get("PrivacyResponse_Block_DoNotDelete")
		
		.then().log().all()
		.assertThat().statusCode(200);
		//.body("Connection",equalTo("Keep-Alive"))
		//.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("The string is "+response);
		//System.out.println(response);

		 
		
	}

	private static void ValidateWithCorrectData() {
RestAssured.baseURI = "https://apigw-pod1.mrel.infaqa.com/t/apim.mrel.com";
		
		ValidatableResponse response = 
		given()
		.log().all()
		.queryParam("in","5105105105105100")
		.header("Content-Type","application/json")
		//.body(payload.AddPlace())
		
	
		.when()
		.get("PrivacyResponse_Block_DoNotDelete")
		
		.then().log().all()
		.assertThat().statusCode(403);
		//.body("Connection",equalTo("Keep-Alive"))
		//.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		System.out.println("The string is "+response);
		//System.out.println(response);

		 
		
	}

}
