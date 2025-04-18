package ra.edu.presentation;

import ra.edu.business.model.Login;
import ra.edu.business.service.admin.AdminServiceImp;

import java.util.Scanner;

public class CandidateMainMenu {
    public static void candidateMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n========== MENU ỨNG VIÊN ==========");
            System.out.println("1. Quản lý Thông tin cá nhân");
            System.out.println("2. Xem và nộp đơn ứng tuyển");
            System.out.println("3. Xem đơn đã ứng tuyển");
            System.out.println("4. Đăng xuất");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
//                    personalInfoMenu();
                    break;
                case 2:
//                    applyMenu();
                    break;
                case 3:
//                    submittedApplicationMenu();
                    break;
                case 4:
                    AdminServiceImp adminServiceImp = new AdminServiceImp();
                    Login accountLogin = adminServiceImp.getIsCheckLogin();
                    System.out.println("Đăng xuất thành công");
                    adminServiceImp.deleteIsLogin(accountLogin.getEmail());
                    System.exit(0);
                    return;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }

}
