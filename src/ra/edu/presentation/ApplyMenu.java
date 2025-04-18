package ra.edu.presentation;

import java.util.Scanner;

public class ApplyMenu {private static void applyMenu() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.println("\n→ Xem và Nộp đơn ứng tuyển");
        System.out.println("1. Xem danh sách vị trí đang hoạt động");
        System.out.println("2. Xem chi tiết và apply (cung cấp CV URL)");
        System.out.println("3. Quay về menu chính");
        System.out.print("Nhập lựa chọn: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println(">> Danh sách vị trí đang hoạt động (đang phát triển)");
                break;
            case 2:
                System.out.println(">> Apply với CV URL (đang phát triển)");
                break;
            case 3:
                return;
            default:
                System.out.println(">> Lựa chọn không hợp lệ!");
        }
    }
}


}
