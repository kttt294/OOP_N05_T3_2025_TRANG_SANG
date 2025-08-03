package com.example.servingwebcontent.util;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DateTimeUtils {
    public static final String VIET_FORMAT = "HH:mm dd/MM/yyyy";
    public static final DateTimeFormatter VIET_FORMATTER = DateTimeFormatter.ofPattern(VIET_FORMAT);

    public static LocalDateTime parseVietDateTime(String input) {
        try {
            return LocalDateTime.parse(input, VIET_FORMATTER);
        } catch (Exception e) {
            System.out.println("Vui lòng nhập đúng định dạng: HH:mm dd/MM/yyyy");
            return null;
        }
    }

    public static String formatVietDateTime(LocalDateTime dateTime) {
        return dateTime.format(VIET_FORMATTER);
    }

    public static LocalDateTime nhapThoiGian(Scanner sc, String message) {
        while (true) {
            System.out.print(message + " (HH:mm dd/MM/yyyy): ");
            String input = sc.nextLine();
            LocalDateTime dateTime = parseVietDateTime(input);
            if (dateTime != null) {
                return dateTime;
            }
        }
    }
}