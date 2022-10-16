package ru.azbykamebeli.tracker.domain.model;

public interface IValidatable {
    // validate должен выбрасывать кастомные исключения
    boolean isValid();
}
