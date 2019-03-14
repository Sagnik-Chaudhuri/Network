import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


/**
 * 
 * This class consists of data members that create
 * a random string of random lengths and a checksum 
 * field, to be multicasted. 
 * 
 * @author Sagnik
 * @version 1.0
 */
public class ObjectCustom implements Serializable {

	int length;
	String content;
	byte hash[];
	
	/**
	 * Sets the length of the string 
	 * to be random
	 * 
	 */
	
	public void setLength() {
		// Set max length of string to be < 1000
		Random random = new Random();
		length = random.nextInt(1000);
		
	}
	/**
	 * Sets the content of the String of random length
	 * to be random
	 * Also generates the checksum of the String
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public void setContent() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"; 

		// Create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			// Generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int)(AlphaNumericString.length() * Math.random());
			
			// Add character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		
		// Converting to String
		content = sb.toString();
		
		// Initialising MessageDigest object for Md5 Checksum generation
		// and storing generated checksum in hash[] array
		MessageDigest md = MessageDigest.getInstance("MD5");
		hash = md.digest(content.getBytes("UTF-8"));
		
	}
	
	/**
	 * Displays details (datamembers of the class)
	 */
	public void display() {
		// Displaying details of an object
		System.out.println("Displaying details");
		System.out.println("Length = " + length);
		System.out.println("Content = " + content);
		System.out.println("Byte Checksum = ");
		for (byte b : hash) {
			System.out.print(b);
		}
		System.out.println();
	}
	
}
