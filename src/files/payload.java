package files;

public class payload {
	
	
	public static String addBook(String isbn, String phNumber) {
		String BookDetails = 
				"{\n"
				+ "\"name\"	:\"Testing Book Zakir\",\n"
				+ "\"isbn\"	:\""+isbn+"\",\n"
				+ "\"aisle\"	:\""+phNumber+"\",\n"
				+ "\"author\"	:\"Zakir Husain Shaik\"\n"
				+ "}\n"
				+ "";
		return BookDetails;
	}
	
	public static String GroupCreation() {
		return "{\n"
				+ "	\"name\" : \"RestAssured26\",\n"
				+ "	\"apis\" : [\n"
				+ "        \n"
				+ "		{\n"
				+ "			\"endpoint\" : \"/ZR_Basic/1.0.0\"\n"
				+ "		}\n"
				+ "		\n"
				+ "	]\n"
				+ "}";
	}
	

	public static String VerifyAzureOrgExists() {
		return "{\n"
				+ "  \"orgId\" : {\n"
				+ "	\"infaId\" : \"lgE0vNbguanlwZiEpgueaa\",\n"
				+ "	\"domain\" : \"apim.tlv.com\"\n"
				+ "  },\n"
				+ "  \"orgContact\": {\n"
				+ "    \"orgName\": \"tlv.inner.jenkins\",\n"
				+ "    \"email\": \"tlv.inner.jenkins@informatica.com\"\n"
				+ "  }\n"
				+ "}";
	}
	
	
	public static String MrelLogin() {
		return "{\n"
				+ "	\"@type\" : \"Login\",\n"
				+ "	\"username\": \"apim.mrel.all\",\n"
				+ "	\"password\": \"Inf0rmat1ca\"\n"
				+ "}";
	}
	
	
	
	public static String LoginAzure() {
		return "{\n"
				+ "	\"@type\" : \"Login\",\n"
				+ "	\"username\": \"apim.azure.all\",\n"
				+ "	\"password\": \"Inf0rmat1ca\"\n"
				+ "}";
	}
	
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


