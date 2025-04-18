package ra.edu.validate;

import java.util.Scanner;
import java.util.regex.Pattern;

public class validateLogin {
    public static Scanner sc = new Scanner(System.in);
    public static String validateEmail(String message) {
        System.out.println(message);
        while (true) {
            String email = sc.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("Email không được để trống.");
            } else if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            } else {
                return email;
            }
        }
    }
    public static String validatePassword(String message) {
        System.out.println(message);
        while (true) {
            String password = sc.nextLine().trim();
            if (password.isEmpty()) {
                System.out.println("Mật khẩu không được để trống.");
            } else if (password.length() > 255) {
                System.out.println("Mật khẩu không được vượt quá 255 ký tự.");
            } else {
                return password;
            }
        }
    }
}
