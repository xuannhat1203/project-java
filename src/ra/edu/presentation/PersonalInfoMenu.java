package ra.edu.presentation;

import java.util.Scanner;

public class PersonalInfoMenu {
    private static void personalInfoMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n→ Quản lý Thông tin cá nhân");
            System.out.println("1. Cập nhật thông tin cá nhân");
            System.out.println("2. Đổi mật khẩu (có xác thực email/sđt)");
            System.out.println("3. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(">> Cập nhật thông tin cá nhân (đang phát triển)");
                    break;
                case 2:
                    System.out.println(">> Đổi mật khẩu (đang phát triển)");
                    break;
                case 3:
                    return;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }

}
