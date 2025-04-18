package ra.edu.presentation;

import ra.edu.business.model.Application;
import ra.edu.business.model.Technology;
import ra.edu.business.service.admin.AdminServiceImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ApplicationMenu {
    public static Scanner scanner = new Scanner(System.in);

    public static void applicationMenu() {
        while (true) {
            System.out.println("\n========== QUẢN LÝ ĐƠN ỨNG TUYỂN ==========");
            System.out.println("1. Xem danh sách đơn ứng tuyển");
            System.out.println("2. Lọc đơn theo trạng thái (progress, result)");
            System.out.println("3. Hủy đơn (ghi lý do, cập nhật ngày hủy)");
            System.out.println("4. Xem chi tiết đơn (tự chuyển từ pending -> handling)");
            System.out.println("5. Gửi thông tin phỏng vấn (chuyển sang interviewing)");
            System.out.println("6. Cập nhật kết quả phỏng vấn (done, ghi chú, đậu/rớt)");
            System.out.println("7. Quay về menu chính");
            System.out.print("Nhập lựa chọn: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    displayApplication();
                    break;
                case 2:
                    filterByOption();
                    break;
                case 3:
                    destroyInterview();
                    break;
                case 4:
                    System.out.println(">> Chi tiết đơn (tự động chuyển pending -> handling) (đang phát triển)");
                    break;
                case 5:
                    System.out.println(">> Gửi thông tin phỏng vấn (đang phát triển)");
                    break;
                case 6:
                    System.out.println(">> Cập nhật kết quả phỏng vấn (đang phát triển)");
                    break;
                case 7:
                    System.out.println(">> Quay lại menu quản trị...");
                    return;
                default:
                    System.out.println(">> Lựa chọn không hợp lệ!");
            }
        }
    }

    public static void displayApplication() {
        AdminServiceImp adminService = new AdminServiceImp();
        Scanner sc = new Scanner(System.in);
        int page = 1;
        int size = 5;

        while (true) {
            List<Application> applications = adminService.listApplication(page, size);
            System.out.println("\n===== DANH SÁCH ĐƠN ỨNG TUYỂN ĐÃ HỦY - TRANG " + page + " =====");
            String format = "| %-4s | %-12s | %-18s | %-7s | %-19s | %-20s |\n";
            System.out.format("+------+--------------+--------------------+---------+---------------------+----------------------+\n");
            System.out.format("| ID   | Candidate ID | Recruitment Pos ID | Tiến độ | Thời gian hủy       | Lý do hủy            |\n");
            System.out.format("+------+--------------+--------------------+---------+---------------------+----------------------+\n");
            boolean hasData = false;
            for (Application app : applications) {
                if (app.getDestroyAt() != null) {
                    hasData = true;
                    System.out.format(format,
                            app.getId(),
                            app.getCandidateId(),
                            app.getRecruitmentPositionId(),
                            app.getProgress(),
                            app.getDestroyAt(),
                            truncateString(app.getDestroyReason(), 20));
                }
            }
            if (!hasData) {
                System.out.println("| Không có đơn ứng tuyển nào đã bị huỷ ở trang này.                                  |");
            }
            System.out.format("+------+--------------+--------------------+---------+---------------------+----------------------+\n");
            System.out.println("\n[1] Trang sau | [2] Trang trước | [3] Thoát");
            System.out.print("Chọn hành động: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    page++;
                    break;
                case "2":
                    if (page > 1) {
                        page--;
                    } else {
                        System.out.println("Bạn đang ở trang đầu tiên.");
                    }
                    break;
                case "3":
                    System.out.println("Thoát xem danh sách.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private static String truncateString(String str, int maxLength) {
        if (str == null) return "";
        return str.length() <= maxLength ? str : str.substring(0, maxLength - 3) + "...";
    }

    public static void filterByOption() {
        System.out.println("1. Lọc theo trạng thái");
        System.out.println("2. Lọc theo kết quả");
        System.out.println("3. Thoát");
        System.out.print("Nhập lựa chọn của bạn: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                filterByProcess();
                break;
            case 2:
                filterByResult();
                break;
            case 3:
                System.out.println("Thoát");
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ");
                break;
        }
    }
    public static void filterByProcess() {
        AdminServiceImp adminService = new AdminServiceImp();
        System.out.print("Nhập trạng thái tiến trình muốn lọc (pending, handling, interviewing, done): ");
        String process = scanner.nextLine().trim();
        List<Application> filteredList = adminService.listFilterByProcess(process);

        if (filteredList.isEmpty()) {
            System.out.println("Không tìm thấy đơn nào với trạng thái: " + process);
            return;
        }

        displayPaginatedApplications(filteredList, "TRẠNG THÁI: " + process);
    }

    public static void filterByResult() {
        AdminServiceImp adminService = new AdminServiceImp();
        System.out.print("Nhập kết quả muốn lọc (pass, fail, null): ");
        String result = scanner.nextLine().trim();
        List<Application> filteredList = adminService.listApplicationByResult(result);

        if (filteredList.isEmpty()) {
            System.out.println("Không tìm thấy đơn nào với kết quả: " + result);
            return;
        }

        displayPaginatedApplications(filteredList, "KẾT QUẢ: " + result);
    }
    private static void displayPaginatedApplications(List<Application> applications, String title) {
        int page = 1;
        int size = 5;
        Scanner sc = new Scanner(System.in);
        int totalPage = (int) Math.ceil((double) applications.size() / size);
        while (true) {
            int start = (page - 1) * size;
            int end = Math.min(start + size, applications.size());
            List<Application> pageList = applications.subList(start, end);
            System.out.println("\n===== DANH SÁCH ĐƠN ỨNG TUYỂN (" + title + ") - TRANG " + page + "/" + totalPage + " =====");
            String format = "| %-4s | %-12s | %-18s | %-11s | %-10s | %-20s |\n";
            System.out.format("+------+--------------+--------------------+-------------+------------+----------------------+\n");
            System.out.format("| ID   | Candidate ID | Recruitment Pos ID | Tiến trình  | Kết quả    | Lý do hủy            |\n");
            System.out.format("+------+--------------+--------------------+-------------+------------+----------------------+\n");
            for (Application app : pageList) {
                System.out.format(format,
                        app.getId(),
                        app.getCandidateId(),
                        app.getRecruitmentPositionId(),
                        app.getProgress(),
                        app.getInterviewResult() == null ? "null" : app.getInterviewResult(),
                        truncateString(app.getDestroyReason(), 20));
            }

            System.out.format("+------+--------------+--------------------+-------------+------------+----------------------+\n");
            System.out.println("\n[1] Trang sau | [2] Trang trước | [3] Thoát");
            System.out.print("Chọn hành động: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    if (page < totalPage) {
                        page++;
                    } else {
                        System.out.println("Đang ở trang cuối cùng.");
                    }
                    break;
                case "2":
                    if (page > 1) {
                        page--;
                    } else {
                        System.out.println("Đang ở trang đầu tiên.");
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
    public static void destroyInterview() {
        AdminServiceImp adminService = new AdminServiceImp();
        System.out.println("Nhập id muốn hủy phỏng vấn: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập lí do hủy phỏng vấn: ");
        String result = scanner.nextLine().trim();
        List<Application> list = adminService.listApplication();
        Optional<Application> isCheck = list.stream().filter(app -> app.getId() == id).findFirst();
        if (isCheck.isPresent()) {
            adminService.deleteResultInterview(id,result);
            System.out.println("Hủy lịch phỏng vấn thành công");
        }else {
            System.out.println("Không có id phỏng vấn");
        }
    }
}
