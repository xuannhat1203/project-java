package ra.edu.validate;

import ra.edu.business.model.RecruitmentPosition;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class JobPositionValidator {
    public static Scanner sc = new Scanner(System.in);

    public static String validatePositionName(String message) {
        List<RecruitmentPosition> listPosition = new ArrayList<>();
        System.out.println(message);
        String positionName;
        while (true) {
            positionName = sc.nextLine();
            if (positionName.equals("")) {
                System.out.println("Tên vị trí không được để trống");
            } else {
                String finalPositionName = positionName;
                if (listPosition.stream().anyMatch(p -> p.getName().equalsIgnoreCase(finalPositionName))) {
                    System.out.println("Tên đã tồn tại");
                } else {
                    return positionName;
                }
            }
        }
    }

    public static BigDecimal validatePositioMinSalary(String message) {
        System.out.println(message);
        BigDecimal positionPrice;
        while (true) {
            positionPrice = sc.nextBigDecimal();
            if (positionPrice.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Lương tối thiểu không hợp lệ");
            } else {
                return positionPrice;
            }
        }
    }

    public static BigDecimal validatePositionMaxSalary(String message, BigDecimal minSalary) {
        System.out.println(message);
        BigDecimal positionPrice;
        while (true) {
            positionPrice = sc.nextBigDecimal();
            if (positionPrice.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Lương tối thiểu không hợp lệ");
            } else if (positionPrice.compareTo(minSalary) < 0) {
                System.out.println("LƯơng tối đa phải lớn hơn lương tối thiểu");
            } else {
                return positionPrice;
            }
        }
    }

    public static LocalDate validateExpiredDate(String message, LocalDate createdDate) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate expiredDate = null;
        System.out.println(message);
        while (true) {
            String input = sc.nextLine();
            try {
                expiredDate = LocalDate.parse(input, formatter);
                if (expiredDate.isAfter(createdDate)) {
                    return expiredDate;
                } else {
                    System.out.println("Ngày hết hạn phải sau ngày tạo. Nhập lại (yyyy-MM-dd): ");
                }
            } catch (Exception e) {
                System.out.println("Sai định dạng! Nhập lại (yyyy-MM-dd): ");
            }
        }
    }

}
