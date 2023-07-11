package ru.javawebinar.topjava.exeption;

public class ExistStorageException extends StorageException {
    public ExistStorageException(final int id) {
        super("Meal " + id + " already exist", id);
    }
}
