package Main;

import java.math.BigDecimal;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

@Service
public class PayleGatewayHelper {
	private static final String HEX_DIGITS = "0123456789abcdef";
    private static final String RESOURCE_KEY = "**********";// given by payle system
    
	public  String encryptAES(String encryptString) throws Exception{   	 
    	byte[] encryptedText=null;
    	IvParameterSpec ivspec=null;
    	SecretKeySpec skeySpec=null;
    	Cipher cipher=null;
    	byte[]  text=null;
    	String s=null;
        	try{
           	 
            	ivspec = new IvParameterSpec(RESOURCE_KEY.getBytes("UTF-8"));
            	skeySpec = new SecretKeySpec(RESOURCE_KEY.getBytes("UTF-8"), "AES");
            	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            	cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivspec);
            	text = encryptString.getBytes("UTF-8");
            	encryptedText = cipher.doFinal(text);
            	s = byteArrayToHexString(encryptedText);
           	 
        	}catch(Exception e){
            	e.printStackTrace();
        	}
        	finally
        	{
            	encryptedText=null;
            	ivspec=null;
            	skeySpec=null;
            	cipher=null;
            	text=null;
        	}
    	return s.toUpperCase();   	 
	}
    
	private static String byteArrayToHexString(byte[] data) {
    	return byteArrayToHexString(data, data.length);
	}
    
	private static String byteArrayToHexString(byte[] data, int length) {
    	StringBuffer buf = new StringBuffer();

    	for (int i = 0; i != length; i++) {
        	int v = data[i] & 0xff;

        	buf.append(HEX_DIGITS.charAt(v >> 4));
        	buf.append(HEX_DIGITS.charAt(v & 0xf));
    	}

    	return buf.toString();
	}
    
	public static String decryptAES(String encryptedString) throws Exception{
    	SecretKeySpec skeySpec=null;
    	IvParameterSpec ivspec=null;
    	Cipher cipher =null;
    	byte[] textDecrypted=null;
    	try{    

    	byte[] b = hexStringToByteArray(encryptedString);
    	skeySpec = new SecretKeySpec(RESOURCE_KEY.getBytes("UTF-8"), "AES");
    	ivspec = new IvParameterSpec(RESOURCE_KEY.getBytes("UTF-8"));
    	cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivspec);
    	textDecrypted = cipher.doFinal(b);
    	}catch(Exception e){
    	e.printStackTrace();
    	}
    	finally
    	{
    	skeySpec=null;
    	ivspec=null;
    	cipher =null;
	}

return(new String(textDecrypted));
}

private static byte[] hexStringToByteArray(String s) {
	int len = s.length();
	byte[] data = new byte[len / 2];
	for (int i = 0; i < len; i += 2) {
	data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
	.digit(s.charAt(i + 1), 16));
	}
	return data;
}
}
