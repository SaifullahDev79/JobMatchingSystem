package sanitytest;
import jobmatchingsystem.security.PasswordUtil;

public class HashTest {
    public static void main(String[] args) {
        String rawPassword = "Houston2024^"; // example password
        String hash = PasswordUtil.sha256(rawPassword);

        System.out.println("Raw: " + rawPassword);
        System.out.println("SHA-256 Hash: " + hash);
        System.out.println("Hash length: " + hash.length());
    }
}