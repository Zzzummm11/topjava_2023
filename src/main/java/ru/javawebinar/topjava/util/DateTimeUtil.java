package ru.javawebinar.topjava.util;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static <T extends Comparable<T>> boolean isBetweenHalfOpen(T value, T startValue, T endValue) {
        return (startValue == null || value.compareTo(startValue) >= 0) && (endValue == null || value.compareTo(endValue) < 0);
    }

    public static LocalDateTime convertStartDateToLocalDateTime(LocalDate startDate) {
        return startDate == null ? LocalDateTime.MIN : startDate.atStartOfDay();
    }

    public static LocalDateTime convertEndDateToLocalDateTime(LocalDate endDate) {
        return endDate == null ? LocalDateTime.MAX : endDate.plusDays(1).atStartOfDay();
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String localDate) {
        return StringUtils.isBlank(localDate) ? null : LocalDate.parse(localDate);
    }

    public static LocalTime parseLocalTime(String localTime) {
        return StringUtils.isBlank(localTime) ? null : LocalTime.parse(localTime);
    }
}

