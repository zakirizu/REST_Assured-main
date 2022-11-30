package files;

public class JIRAPayLoad {

	public static String getJIRALogin()
	{
		String LoginDetails = 
				"{ \r\n"
				+ "  \"username\": \"zakir.13.2011\",\r\n"
				+ "  \"password\": \"Amyrah*1626\"\r\n"
				+ "}";		
		return LoginDetails;		
	}
	
	
	public static String getCreateProject(String ProjectName , String ProjectKey){
		String ProjecDetails = "{\r\n"
				+ "    \"key\": \""+ProjectKey+"\",\r\n"
				+ "    \"name\": \""+ProjectName+"\",\r\n"
				+ "    \"projectTypeKey\": \"business\",\r\n"
				+ "    \"projectTemplateKey\": \"com.atlassian.jira-core-project-templates:jira-core-project-management\",\r\n"
				+ "    \"description\": \"Example Project description\",\r\n"
				+ "    \"lead\": \"zakir.13.2011\",\r\n"
				+ "    \"url\": \"http://atlassian.com\",\r\n"
				+ "    \"assigneeType\": \"PROJECT_LEAD\"\r\n"
				+ "    \r\n"
				+ "}";
		return ProjecDetails;	
	}

	public static String getCreateIssue(String ProjectKey){
		
		String CreateIssue = "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "       \"project\"    : { \"key\": \""+ProjectKey+"\"      },\r\n"
				+ "       \"summary\"    : \"Bismillah - This is Automated Role\",\r\n"
				+ "       \"description\": \"Test Issue\",\r\n"
				+ "       \"issuetype\"  : {        \"name\": \"Task\"       }\r\n"
				+ "   }\r\n"
				+ "}";
		return CreateIssue;	
	}
	
	public static String getAddComment(String Comment){
		String addComment = "{\r\n"
				+ "    \"body\": \""+Comment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
		return addComment;	
	}
	
	
public static String getUpdateComment(String UpdtComment){
		String UpdateComment ="{\r\n"
				+ "    \"body\": \""+UpdtComment+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}";
		return UpdateComment;	
	}






















}
