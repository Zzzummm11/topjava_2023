package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;
import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomTimeFormatter implements Formatter<LocalTime> {
    @Override
    public LocalTime parse(@Nullable String text, @Nullable Locale locale) {
        return LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String print(@Nullable LocalTime localTime, @Nullable Locale locale) {
        return localTime.toString();
    }
}
