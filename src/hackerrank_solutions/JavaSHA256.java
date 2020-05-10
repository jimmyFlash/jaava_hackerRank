package hackerrank_solutions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class JavaSHA256 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String pass = sc.nextLine();
        MessageDigest sha2;
        try {
            sha2 = MessageDigest.getInstance("SHA-256");
            sha2.update(pass.getBytes());
            byte[] digest = sha2.digest();
            String myHash = bytesToHex(digest).toLowerCase();
            System.out.println(myHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
