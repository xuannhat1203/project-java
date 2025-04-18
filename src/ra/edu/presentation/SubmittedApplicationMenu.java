package ra.edu.presentation;

import java.util.Scanner;

public class SubmittedApplicationMenu {
    private static void submittedApplicationMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n→ Xem đơn đã ứng tuyển");
            System.out.println("1. Xem danh sách đơn đã nộp");
            System.out.println("2. Xem chi tiết đơn (xác nhận tham gia phỏng vấn nếu có)");
            System.out.println("3. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(">> Danh sách đơn đã nộp (đang phát triển)");
                    break;
                case 2:
                    System.out.println(">> Chi tiết đơn và xác nhận phỏng vấn (đang phát triển)");
                    break;
                case 3:
                    return;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }


}
