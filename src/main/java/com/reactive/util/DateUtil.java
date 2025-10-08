package com.reactive.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTime format(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        String dateTimeString = dateTime.format(FORMATTER);
        return LocalDateTime.parse(dateTimeString, FORMATTER);
    }
}
