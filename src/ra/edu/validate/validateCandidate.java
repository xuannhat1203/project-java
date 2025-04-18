package ra.edu.validate;

import java.util.Scanner;

public class validateCandidate {
    public static Scanner scanner = new Scanner(System.in);
    private static Scanner sc = new Scanner(System.in);
    public static String validateName(String message) {
        System.out.println(message);
        while (true) {
            String name = sc.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Không được để trống tên.");
            } else if (name.length() > 100) {
                System.out.println("Tên không được vượt quá 100 ký tự.");
            } else {
                return name;
            }
        }
    }
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
    public static String validatePhone(String message) {
        System.out.println(message);
        while (true) {
            String phone = sc.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println("Số điện thoại không được để trống.");
            } else if (phone.length() > 20) {
                System.out.println("Số điện thoại không được vượt quá 20 ký tự.");
            } else {
                return phone;
            }
        }
    }
    public static int validateExperience(String message) {
        System.out.println(message);
        while (true) {
            try {
                int experience = Integer.parseInt(sc.nextLine().trim());
                if (experience < 0) {
                    System.out.println("Số năm kinh nghiệm không thể là số âm.");
                } else {
                    return experience;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số hợp lệ.");
            }
        }
    }
    public static String validateGender(String message) {
        System.out.println(message);
        while (true) {
            String gender = sc.nextLine().trim().toLowerCase();
            if (gender.equals("nam") || gender.equals("nữ")) {
                return gender;
            } else {
                System.out.println("Giới tính không hợp lệ. Vui lòng nhập 'Nam' hoặc 'Nữ'.");
            }
        }
    }
    public static Boolean validateStatus(String message) {
        System.out.println(message + " (true/false): ");
        while (true) {
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.println("Giá trị trạng thái không hợp lệ. Vui lòng nhập 'true' hoặc 'false'.");
            }
        }
    }
    public static String validateDescription(String message) {
        System.out.println(message);
        while (true) {
            String description = sc.nextLine().trim();
            if (description.isEmpty()) {
                System.out.println("Mô tả không được để trống.");
            } else if (description.length() > 255) {
                System.out.println("Mô tả không được vượt quá 255 ký tự.");
            } else {
                return description;
            }
        }
    }
    public static String validateDob(String message) {
        System.out.println(message);
        while (true) {
            String dob = sc.nextLine().trim();
            try {
                java.sql.Date.valueOf(dob);
                return dob;
            } catch (IllegalArgumentException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại.");
            }
        }
    }
}
