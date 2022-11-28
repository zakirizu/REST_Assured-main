package files;

public class payload {

	public static String AddPlace() {
		return "\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Rahul Shetty Academy\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + "";
	}

	/*1. Print No of courses returned by API

	2.Print Purchase Amount

	3. Print Title of the first course

	4. Print All course titles and their respective Prices

	5. Print no of copies sold by RPA Course

	6. Verify if Sum of all Course prices matches with Purchase Amount;
		}
	*/
	
	public static String CoursePrice() {
		return "{\n"
				+ "  \"dashboard\": {\n"
				+ "    \"purchaseAmount\": 910,\n"
				+ "    \"website\": \"rahulshettyacademy.com\"\n"
				+ "  },\n"
				+ "  \"courses\": [\n"
				+ "    {\n"
				+ "      \"title\": \"Selenium Python\",\n"
				+ "      \"price\": 50,\n"
				+ "      \"copies\": 6\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"title\": \"Cypress\",\n"
				+ "      \"price\": 40,\n"
				+ "      \"copies\": 4\n"
				+ "    },\n"
				+ "    {\n"
				+ "      \"title\": \"RPA\",\n"
				+ "      \"price\": 45,\n"
				+ "      \"copies\": 10\n"
				+ "    }\n"
				+ "  ]\n"
				+ "}";


	}}


