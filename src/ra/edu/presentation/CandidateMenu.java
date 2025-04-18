package ra.edu.presentation;

import ra.edu.business.model.Candidate;
import ra.edu.business.service.admin.AdminServiceImp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class CandidateMenu {
    public static AdminServiceImp adminServiceImp = new AdminServiceImp();
    private static final Scanner sc = new Scanner(System.in);
    public static void candidateMenu() {
        while (true) {
            System.out.println("\n========== QUẢN LÝ ỨNG VIÊN ==========");
            System.out.println("1. Hiển thị danh sách ứng viên");
            System.out.println("2. Khóa/Mở khóa tài khoản ứng viên");
            System.out.println("3. Reset mật khẩu ứng viên (hiển thị mật khẩu random)");
            System.out.println("4. Tìm kiếm theo tên");
            System.out.println("5. Lọc theo kinh nghiệm, tuổi, giới tính, công nghệ");
            System.out.println("6. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    displayCandidateList();
                    break;
                case 2:
                    changeStatus();
                    break;
                case 3:
                    resetCandidatePassword();
                    break;
                case 4:
                    searchByName();
                    break;
                case 5:
                    filterMenu();
                    break;
                case 6:
                    System.out.println("Quay lại menu quản trị");
                    return;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }
    private static void displayCandidateList() {
        int page = 1;
        int size = 5;
        while (true) {
            List<Candidate> candidates = adminServiceImp.listCandidatePagination(page, size);
            if (candidates.isEmpty()) {
                System.out.println("Không có ứng viên nào ở trang này.");
            } else {
                System.out.println("\n--- Trang " + page + " ---");
                for (Candidate candidate : candidates) {
                    if (candidate != null) {
                        System.out.println(candidate.toString());
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
                } else {
                    System.out.println("Đang ở trang đầu tiên.");
                }
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    private static void changeStatus() {
        System.out.print("Nhập ID ứng viên cần đổi trạng thái: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean result = adminServiceImp.changeStatusOfCandidate(id);
        if (result) {
            System.out.println("Đổi trạng thái thành công");
        }else {
            System.out.println("Đổi trạng thái thất bại");
        }
    }
    private static void resetCandidatePassword() {
        System.out.print("Nhập ID ứng viên cần reset mật khẩu: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean result = adminServiceImp.resetPassword(id);
        if (result) {
            System.out.println("Mật khẩu đã được reset, kiểm tra cơ sở dữ liệu để lấy mật khẩu mới");
        }else{
            System.out.println("Reset mật khẩu thất bại!");
        }
    }
    private static void searchByName() {
        System.out.print("Nhập tên ứng viên cần tìm: ");
        String name = sc.nextLine();
        List<Candidate> result = adminServiceImp.findCandidate(name);
        if (result.isEmpty()) {
            System.out.println("Không tìm thấy ứng viên nào");
            return;
        }

        int page = 1;
        int size = 5;
        int totalPage = (int) Math.ceil((double) result.size() / size);

        while (true) {
            int start = (page - 1) * size;
            int end = Math.min(start + size, result.size());
            System.out.println("\n--- Kết quả tìm kiếm - Trang " + page + "/" + totalPage + " ---");
            for (int i = start; i < end; i++) {
                System.out.println(result.get(i));
            }

            System.out.println("\n[1] Trang sau | [2] Trang trước | [3] Thoát");
            System.out.print("Chọn hành động: ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                if (page < totalPage) page++;
                else System.out.println("Đang ở trang cuối.");
            } else if (choice.equals("2")) {
                if (page > 1) page--;
                else System.out.println("Đang ở trang đầu tiên.");
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }


    private static void filterMenu() {
        System.out.println("***** Bộ lọc *****");
        System.out.println("1. Kinh nghiệm");
        System.out.println("2. Tuổi");
        System.out.println("3. Giới tính");
        System.out.println("4. Công nghệ");
        System.out.println("5. Thoát");
        System.out.print("Chọn loại bộ lọc: ");
        int filterChoice = Integer.parseInt(sc.nextLine());
        List<Candidate> filtered = null;
        switch (filterChoice) {
            case 1:
                System.out.print("Nhập kinh nghiệm min: ");
                int expMin = Integer.parseInt(sc.nextLine());
                System.out.print("Nhập kinh nghiệm max: ");
                int expMax = Integer.parseInt(sc.nextLine());
                filtered = adminServiceImp.filterExperience(expMin, expMax);
                break;
            case 2:
                System.out.print("Nhập tuổi min: ");
                int ageMin = Integer.parseInt(sc.nextLine());
                System.out.print("Nhập tuổi max: ");
                int ageMax = Integer.parseInt(sc.nextLine());
                filtered = adminServiceImp.filterAge(ageMin, ageMax);
                break;
            case 3:
                System.out.print("Nhập giới tính (Nam/Nữ): ");
                String gender = sc.nextLine();
                filtered = adminServiceImp.filterGender(gender);
                break;
            case 4:
                System.out.print("Nhập tên công nghệ: ");
                String tech = sc.nextLine();
                filtered = adminServiceImp.filterTechnology(tech);
                break;
            case 5:
                System.out.println("Thoát");
                return;
            default:
                System.out.println(">> Lựa chọn không hợp lệ!");
                return;
        }

        if (filtered == null || filtered.isEmpty()) {
            System.out.println("Không có kết quả phù hợp.");
            return;
        }
        int page = 1;
        int size = 5;
        int totalPage = (int) Math.ceil((double) filtered.size() / size);
        while (true) {
            int start = (page - 1) * size;
            int end = Math.min(start + size, filtered.size());
            System.out.println("\n--- Kết quả lọc - Trang " + page + "/" + totalPage + " ---");
            for (int i = start; i < end; i++) {
                System.out.println(filtered.get(i));
            }
            System.out.println("\n[1] Trang sau | [2] Trang trước | [3] Thoát");
            System.out.print("Chọn hành động: ");
            String choice = sc.nextLine().trim();
            if (choice.equals("1")) {
                if (page < totalPage) page++;
                else System.out.println("Đang ở trang cuối.");
            } else if (choice.equals("2")) {
                if (page > 1) page--;
                else System.out.println("Đang ở trang đầu tiên.");
            } else if (choice.equals("3")) {
                break;
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

}
