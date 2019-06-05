package ATM;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Utils {
	public static String md5(String text) {	         
	    MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
		    md.update(text.getBytes());
		    byte[] digest = md.digest();
		    String hash = DatatypeConverter
		      .printHexBinary(digest).toUpperCase();
		    
		    return hash.toLowerCase();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
}
