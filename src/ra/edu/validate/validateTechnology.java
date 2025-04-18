package ra.edu.validate;

import ra.edu.business.model.Technology;
import ra.edu.business.service.admin.AdminServiceImp;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class validateTechnology {
    public static String validateTechnology(String message) {
        ra.edu.business.service.admin.AdminServiceImp adminService = new AdminServiceImp();
        List<Technology> technologies = adminService.listTechnology();
        Scanner scanner = new Scanner(System.in);
        String technology = "";
        System.out.println(message);
        while (technology.trim().isEmpty()) {
            System.out.print("Vui lòng nhập công nghệ: ");
            technology = scanner.nextLine();
            String finalTechnology = technology;
            Optional<Technology> isCheck = technologies.stream().filter(t->t.getName().equals(finalTechnology)).findFirst();
            if (technology.trim().isEmpty()) {
                System.out.println("Tên công nghệ không được để trống. Vui lòng thử lại.");
            }else if (isCheck.isPresent()) {
                System.out.println("Tên công nghệ đã tồn tại");
            }
        }
        return technology;
    }
}
