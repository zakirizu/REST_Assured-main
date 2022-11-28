package CRUD_Operaions;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import files.payload;

public class MyFristTestCase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://apigw-pod1.mrel.infaqa.com/t/apim.mrel.com";
		
		//String response = 
		given()
		.log().all()
		.queryParam("in","5105105105105100")
		.header("Content-Type","application/json")
		//.body(payload.AddPlace())
		
	
		.when()
		.get("PrivacyResponse_Block_DoNotDelete")
		
		.then().log().all()
		.assertThat().statusCode(403)
		.header("message", equalTo("Forbidden"));
		//.body("Connection",equalTo("Keep-Alive"))
		//.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		//System.out.println("The string is "+response);
		//System.out.println(response);

		 

	}

}
