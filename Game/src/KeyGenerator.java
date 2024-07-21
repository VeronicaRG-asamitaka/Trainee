import java.security.SecureRandom;

public class KeyGenerator {

    public String generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256 bits es 32 bytes
        secureRandom.nextBytes(key);
        
        return bytesToHex(key);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }
}

