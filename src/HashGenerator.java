import java.security.MessageDigest;

public class HashGenerator {
    // Take string, encode it in SHA256 and return encoded string
    public static String applySha256(String inputData){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(inputData.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer(); // This string will contain encoded string in hexadecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
