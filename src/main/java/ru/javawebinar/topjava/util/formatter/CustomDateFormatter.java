package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.Formatter;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomDateFormatter implements Formatter<LocalDate> {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public LocalDate parse(@Nullable String text, @Nullable Locale locale) {
        assert text != null;
        return LocalDate.parse(text, FORMATTER);
    }

    @Override
    public String print(@Nullable LocalDate localDate, @Nullable Locale locale) {
        return (localDate != null) ? localDate.toString() : "";
    }
}
