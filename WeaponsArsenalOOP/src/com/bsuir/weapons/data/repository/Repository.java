package com.bsuir.weapons.data.repository;

import com.bsuir.weapons.model.Identifiable;

import java.util.Optional;

public interface Repository<T extends Identifiable> {
    void add(T... item);
    void remove(T item);
    void update(T item);
    void clear();
    Optional<T> findById(long id);
}
