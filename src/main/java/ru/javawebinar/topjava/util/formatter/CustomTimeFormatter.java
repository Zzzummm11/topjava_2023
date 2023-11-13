package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;
import org.springframework.lang.Nullable;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomTimeFormatter implements Formatter<LocalTime> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    @Override
    public LocalTime parse(@Nullable String text, @Nullable Locale locale) {
        assert text != null;
        return LocalTime.parse(text, FORMATTER);
    }

    @Override
    public String print(@Nullable LocalTime localTime, @Nullable Locale locale) {
        return (localTime != null) ? localTime.toString() : "";
    }
}
