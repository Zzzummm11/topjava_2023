package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class Meal {
    private int id;
    private LocalDateTime dateTime;
    private String description;
    private int calories;
    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this.id = COUNTER.incrementAndGet();
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public synchronized void setId(final int id) {
        this.id = id;
    }

    public Meal() {
        this.id = COUNTER.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

}
