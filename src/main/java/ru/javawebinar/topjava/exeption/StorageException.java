package ru.javawebinar.topjava.exeption;

public class StorageException extends RuntimeException {
    private final int id;

    public StorageException(final String message, final int id) {
        super(message);
        this.id = id;
    }

}
