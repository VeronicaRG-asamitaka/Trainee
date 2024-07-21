import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class HMACCalculator {

    private String secretKey;
    private String message;
    private String algorithm;

    public HMACCalculator(String secretKey, String message, String algorithm) {
        this.secretKey = secretKey;
        this.message = message;
        this.algorithm = algorithm;
    }

    public byte[] generateHmac() throws NoSuchAlgorithmException, Exception {
    	//Create the secret key
        Key key = new SecretKeySpec(secretKey.getBytes(), algorithm);

        //Create and initialize the Mac object
        Mac mac = Mac.getInstance(algorithm);
        mac.init(key);

        //Generate the HMAC
        return mac.doFinal(message.getBytes());
    }

    public String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
