package com.mamuya.datrastocospringbootapi.dto;

public interface DTOValidation<T> {
    boolean hasAllValidMappings();

    boolean hasAnyValidMappings();

    T createEntity();

    T updateEntity(T entity);

    boolean hasValid(String item);

    boolean hasValid(Integer item);

}
