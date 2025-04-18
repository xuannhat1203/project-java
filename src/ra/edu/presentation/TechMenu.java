package ra.edu.presentation;

import ra.edu.business.model.Candidate;
import ra.edu.business.model.Technology;
import ra.edu.business.service.admin.AdminServiceImp;
import ra.edu.validate.validateTechnology;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class TechMenu {
    public static Scanner sc = new Scanner(System.in);

    public static void techMenu() {
        while (true) {
            System.out.println("********** Quản lí công nghệ **********");
            System.out.println("1. Xem danh sách công nghệ");
            System.out.println("2. Thêm công nghệ mới");
            System.out.println("3. Sửa công nghệ");
            System.out.println("4. Xoá công nghệ (đổi tên thành _deleted nếu liên kết FK)");
            System.out.println("5. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    displayTechnology();
                    break;
                case 2:
                    addTechnology();
                    break;
                case 3:
                    updateTechnology();
                    break;
                case 4:
                    deleteTechnology();
                    break;
                case 5:
                    System.out.println("Quay lại menu chính");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ");
                    break;
            }
        }
    }
    public static void displayTechnology() {
        AdminServiceImp adminService = new AdminServiceImp();
        int page = 1;
        int size = 5;
        while (true) {
            List<Technology> technologies = adminService.listTechnologyPagination(page, size);
            if (technologies.isEmpty()) {
                System.out.println("Không có công nghệ nào ở trang này.");
            } else {
                System.out.println("\n--- Trang " + page + " ---");
                for (Technology technology : technologies) {
                    if (technology.getName() != null && !technology.getName().contains("delete")) {
                        System.out.println("ID: " + technology.getId());
                        System.out.println("Name: " + technology.getName());
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
    public static void addTechnology() {
        AdminServiceImp adminService = new AdminServiceImp();
        String name = validateTechnology.validateTechnology("Nhập tên công nghệ mới: ");
        boolean isAdded = adminService.addTechnology(name);
        if (isAdded) {
            System.out.println("Thêm công nghệ thành công!");
        } else {
            System.out.println("Thêm công nghệ thất bại.");
        }
    }

    public static void updateTechnology() {
        AdminServiceImp adminService = new AdminServiceImp();
        System.out.print("Nhập ID công nghệ cần sửa: ");
        int id = sc.nextInt();
        sc.nextLine();
        List<Technology> listTech = adminService.listTechnology();
        Optional<Technology> isCheck = listTech.stream().filter(tech -> tech.getId() == id).findFirst();
        if (isCheck.isPresent()) {
            String name = validateTechnology.validateTechnology("Nhập tên công nghệ mới: ");
            boolean isUpdated = adminService.updateTechnology(id, name);
            if (isUpdated) {
                System.out.println("Cập nhật công nghệ thành công!");
            } else {
                System.out.println("Cập nhật công nghệ thất bại.");
            }
        }else {
            System.out.println("ID công nghệ không tồn tại");
        }

    }
    public static void deleteTechnology() {
        AdminServiceImp adminService = new AdminServiceImp();
        System.out.print("Nhập ID công nghệ cần xoá: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean isDeleted = adminService.deleteTechnology(id);
        if (isDeleted) {
            System.out.println("Xoá công nghệ thành công!");
        } else {
            System.out.println("Xoá công nghệ thất bại. Công nghệ này có thể có liên kết với các bảng khác.");
        }
    }
}
