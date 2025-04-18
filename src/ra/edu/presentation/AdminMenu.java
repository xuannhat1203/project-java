package ra.edu.presentation;

import ra.edu.business.model.Login;
import ra.edu.business.service.admin.AdminServiceImp;

import java.util.Scanner;

public class AdminMenu {
    public static void adminMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("========== MENU QUẢN TRỊ ==========");
            System.out.println("1. Quản lý Công nghệ tuyển dụng");
            System.out.println("2. Quản lý Ứng viên");
            System.out.println("3. Quản lý Vị trí tuyển dụng");
            System.out.println("4. Quản lý Đơn ứng tuyển");
            System.out.println("5. Đăng xuất");
            System.out.println("===================================");
            System.out.print("Nhập lựa chọn: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    TechMenu.techMenu();
                    break;
                case 2:
                    CandidateMenu.candidateMenu();
                    break;
                case 3:
                    PositionMenu.positionMenu();
                    break;
                case 4:
                    ApplicationMenu.applicationMenu();
                    break;
                case 5:
                    AdminServiceImp adminServiceImp = new AdminServiceImp();
                    Login account = adminServiceImp.getIsCheckLogin();
                    System.out.println("Đăng xuất thành công");
                    adminServiceImp.deleteIsLogin(account.getEmail());
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }
}
