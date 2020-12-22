import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSADecrypter {

    public static void main(String[] args) {

            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCqGKukO1De7zhZj6+H0qtjTkVxwTCpvKe4eCZ0FPqri0cb2JZfXJ/DgYSF6vUpwmJG8wVQZKjeGcjDOL5UlsuusFncCzWBQ7RKNUSesmQRMSGkVb1/3j+skZ6UtW+5u09lHNsj6tQ51s1SPrCBkedbNf0Tp0GbMJDyR4e9T04ZZwIDAQAB";

            try {
                KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes(StandardCharsets.UTF_8));
                X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
                PublicKey fileGeneratedPublicKey = keyFactory.generatePublic(spec);
                RSAPublicKey rsaPub  = (RSAPublicKey)(fileGeneratedPublicKey);
                BigInteger publicKeyModulus = rsaPub.getModulus();
                BigInteger publicKeyExponent  = rsaPub.getPublicExponent();
                System.out.println("publicKeyModulus: " + publicKeyModulus);
                System.out.println("publicKeyExponent: " + publicKeyExponent);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
    }
}
