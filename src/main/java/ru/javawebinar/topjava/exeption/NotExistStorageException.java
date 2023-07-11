package ru.javawebinar.topjava.exeption;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(final int id) {
        super("Meal " + id + " not exist", id);
    }
}
