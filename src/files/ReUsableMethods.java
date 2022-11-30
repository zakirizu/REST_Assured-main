package files;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReUsableMethods {
	
	
	public static void main(String args[]) {
		getUniqueRandomText();
		getUniqueRandomKey();
		}
	
	
	public static String getUniqueRandomText() {
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss sss");
		String stringDate = DateFor.format(date);
		String RandomText = ((stringDate.replace(" ", "")).replace(",", "")).replace(":", "");
		return RandomText;}
	
	public static String getUniqueRandomKey() {
		Date date = new Date();		
		SimpleDateFormat DateFor = new SimpleDateFormat("mm:ss sss");
		String stringDate = DateFor.format(date);
		String RandomText = ((stringDate.replace(" ", "")).replace(",", "")).replace(":", "");
		return "ZR"+RandomText;
		}}
	
	
	
	

