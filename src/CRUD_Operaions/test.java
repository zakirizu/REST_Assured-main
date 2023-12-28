package CRUD_Operaions;

public class test {

	public static void main(String[] args) {
        String str = "32400121200";

        // Count the number of zeros at the end of the string
        int numZeros = str.length() - str.replaceAll("0*$", "").length();

        // Create the output string with zeros at the beginning
        String strOutput = "0".repeat(numZeros) + str.replaceAll("0*$", "");

        System.out.println(strOutput);

	}

}
