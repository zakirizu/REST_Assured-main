package CRUD_Operaions;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class ReadFileJson {
	
	

	@Test()
	public static void AddBookData() throws IOException {
		
	RestAssured.baseURI = "http://216.10.245.166";
	JsonPath js =
			
	given()//.log().all()
	.header("Content-Type","application/json")
	.body(new String(Files.readAllBytes(Paths.get("C:\\Work\\RestAssured\\REST_Assured-main\\Resources_JsonFFiles\\AddBook.json"))))
	//.body(new String("C:\\Work\\RestAssured\\REST_Assured-main\\Resources_JsonFFiles\\AddBook.json"))
	
	.when()
	.post("/Library/Addbook.php")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();
	
	
	System.out.println("ID of the Book = " +js.getString("ID"));
	
	}
}