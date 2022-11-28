package CRUD_Operaions;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

import files.payload;
import io.restassured.path.json.JsonPath;

public class Parsgin_Json {
	static JsonPath js = new JsonPath(payload.CoursePrice());

	public static void main(String[] args) {
		 //printNoOfCourses();
		 //PrintPurchaseAmountAndWebsiteName();
		 //PrintTitleofFirstCourse();
		 //PrintAllCoursesAndTitle();
		 //PrintNoOfCopiesSoldForRPACourse();
		  VerifyIfSumOfAllCoursePricesMatchesWithPurchaseAmout();
	
}

	private static void VerifyIfSumOfAllCoursePricesMatchesWithPurchaseAmout() {
		int len = js.getInt("courses.size()");
		int TotalCost = js.getInt("dashboard.purchaseAmount");
		int sum=0;
		System.out.println(TotalCost);
		
	
		
		for(int i=0;i<len;i++)
		{
			int Cost 	= js.getInt("courses["+i+"].price");
			int copies  = js.getInt("courses["+i+"].copies");
			sum 	= sum+Cost*copies;
		}
		if(TotalCost==sum)
		{
			System.out.println("Test PASS");
		}
		else
		{
			System.out.println("Test FAIL");
		}
		
		System.out.println(sum);
	}

	private static void PrintNoOfCopiesSoldForRPACourse() {
		
		Instant start = Instant.now();
		String TargetCourse = "Selenium Python";
		int len = js.getInt("courses.size()");
		
		
		for( int i=0; i<len;i++)
		{
			String Title = js.getString("courses["+i+"].title");
			if(Title.equalsIgnoreCase(TargetCourse))
			{
				int TargetPrice = js.getInt("courses["+i+"].copies");
				System.out.println("Copies of the Course "+TargetCourse +" is "+TargetPrice);
				break;
			}
		}

		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Time taken for execution: "+ timeElapsed.toMillis() +" milliseconds");	
		
		
	}

	private static void PrintAllCoursesAndTitle() {

		//Printing all courses and their respective titles
		int len = js.getInt("courses.size()");
		for( int i=0; i<len; i++)
		{
			String title 	= js.getString("courses["+i+"].title");
			int price 		= js.getInt("courses["+i+"].price");
			System.out.println("Course title = " +title +"		and its price = " +price);
			
		}
		
		
		
	}

	private static void PrintTitleofFirstCourse() {

		////////////////////
		//Printing the purchase first Course Title			
		String FirstCourseName = js.getString("courses[0].title");
		System.out.println("First Course Title Name 		= "+FirstCourseName);
		//String ThirdCourse Title and Price
		String ThirdCourseTile = js.get("courses[2].title");
		System.out.println("Third Course Title Name			= " +ThirdCourseTile);
		
	}

	private static void PrintPurchaseAmountAndWebsiteName() {
		
		////////////////////
		//Printing the purchase amount and also the webSite
		//Now taking the return type as int because the property value is int 
		//Taking the return type as String becaue the property values is String in ""
		int TotalPrice = js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount is			= " +TotalPrice);		
		String WebSiteName = js.getString("dashboard.website");
		System.out.println("WebSite Name				= " +WebSiteName);
				
		
	}

	private static void printNoOfCourses() {
		//////////////////////////////
		//Printing the Count of Objects inside an array (Array has [] )
		
		int count = js.getInt("courses.size()");
		System.out.println("Total Number of Courses			= " +count);
		
	}

}
