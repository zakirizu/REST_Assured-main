package CRUD_Operaions;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {
	
	

	@Test(dataProvider= "GetBookData")
	public static void AddBookData(String aisle,String phNumber) {
	
	RestAssured.baseURI = "http://216.10.245.166";
	JsonPath js =
			
	given()//.log().all()
	.header("Content-Type","application/json")
	.body(payload.addBook(aisle,phNumber))
	
	.when()
	.post("/Library/Addbook.php")
	
	.then()//.log().all()
	.assertThat().statusCode(200)
	.extract().response().jsonPath();
	
	
	System.out.println("ID of the Book = " +js.getString("ID"));
	}

	@DataProvider(name = "GetBookData")
	public Object[][] TestData(){
	//X = How many sets of Data
	//Y = How many values in each set
	//Object[][] data = new Object[3][2];
	return new Object[][] 
	{
		{"abdc","162616261"},
		{"pqrt","210821081"},
		{"xyza","100810081"}
	};
	
	}
		
}
