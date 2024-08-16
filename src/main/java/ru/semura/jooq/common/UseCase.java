package ru.semura.jooq.common;

public interface UseCase<T, U> {
    U execute(T request);
}
