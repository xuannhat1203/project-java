package ra.edu.presentation;

import ra.edu.business.model.RecruitmentPosition;
import ra.edu.business.model.Technology;
import ra.edu.business.service.admin.AdminServiceImp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class PositionMenu {
    public static void positionMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n========== QUẢN LÝ VỊ TRÍ TUYỂN DỤNG ==========");
            System.out.println("1. Thêm vị trí tuyển dụng mới");
            System.out.println("2. Cập nhật vị trí tuyển dụng");
            System.out.println("3. Xoá vị trí (đổi tên thành _deleted nếu có FK)");
            System.out.println("4. Xem danh sách vị trí đang hoạt động");
            System.out.println("5. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addPosition();
                    break;
                case 2:
                    updatePosition();
                    break;
                case 3:
                    deletePosition();
                    break;
                case 4:
                    displayRecruitmentPosition();
                    break;
                case 5:
                    System.out.println(">> Quay lại menu quản trị...");
                    return;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }
    public static void addPosition() {
        AdminServiceImp adminService = new AdminServiceImp();
        Scanner sc = new Scanner(System.in);
        RecruitmentPosition newPosition = new RecruitmentPosition();
        System.out.println("Nhập các thông tin vị trí mới");
        newPosition.inputData();
        boolean isCheck = adminService.addNewPosition(newPosition.getName(),newPosition.getDescription(),newPosition.getMinSalary(),newPosition.getMaxSalary(),newPosition.getMinExperience(),newPosition.getExpiredDate(),newPosition.getTechnologyIds());
        if (isCheck) {
            System.out.println("Thêm vị tr thành công");
        }else {
            System.out.println("Lỗi không xác định");
        }
    }
    public static void updatePosition() {
        AdminServiceImp adminService = new AdminServiceImp();
        List<RecruitmentPosition> list = adminService.listRecruitmentPosition();
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập id vị trí muốn cập nhật: ");
        int id = sc.nextInt();
        Optional<RecruitmentPosition> checkId = list.stream().filter(p -> p.getId() == id).findFirst();
        if (checkId.isPresent()) {
            RecruitmentPosition newPosition = new RecruitmentPosition();
            System.out.println("Nhập các thông tin mới của vị trí");
            newPosition.inputData();
            boolean isCheck = adminService.updatePosition(
                    id,
                    newPosition.getName(),
                    newPosition.getDescription(),
                    newPosition.getMinSalary(),
                    newPosition.getMaxSalary(),
                    newPosition.getMinExperience(),
                    newPosition.getExpiredDate()
            );
            if(isCheck) {
                System.out.println("Cập nhật thông tin thành công");
            }else {
                System.out.println("Lỗi không xác định");
            }
        }else {
            System.out.println("Vị trí không tồn tại");
        }
    }
    public static void deletePosition() {
        AdminServiceImp adminService = new AdminServiceImp();
        List<RecruitmentPosition> list = adminService.listRecruitmentPosition();
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập id vị trí muốn xóa: ");
        int id = sc.nextInt();
        Optional<RecruitmentPosition> checkId = list.stream().filter(p -> p.getId() == id).findFirst();
        if (checkId.isPresent()) {
            adminService.deletePosition(id);
            System.out.println("Xóa thành công");
        }else {
            System.out.println("Xóa không thành công");
        }
    }
    public static void displayRecruitmentPosition() {
        Scanner sc = new Scanner(System.in);
        AdminServiceImp adminService = new AdminServiceImp();
        int page = 1;
        int size = 5;
        while (true) {
            List<RecruitmentPosition> recruitmentPositions = adminService.listPosition(page, size);
            if (recruitmentPositions.isEmpty()) {
                System.out.println("Không có công nghệ nào ở trang này.");
            } else {
                System.out.println("\n--- Trang " + page + " ---");
                for (RecruitmentPosition recruitment : recruitmentPositions) {
                    if (recruitment.getName() != null && !recruitment.getName().contains("delete")) {
                        System.out.println("ID: " + recruitment.getId());
                        System.out.println("Name: " + recruitment.getName());
                        System.out.println("------------------");
                    }
                }
            }
            System.out.println("\n[1] Trang sau | [2] Trang trước | [3] Thoát");
            System.out.print("Chọn hành động: ");
            String choice = sc.nextLine().trim().toLowerCase();
            if (choice.equals("1")) {
                page++;
            } else if (choice.equals("2")) {
                if (page > 1) {
                    page--;
                } else System.out.println("Đang ở trang đầu tiên.");
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}
