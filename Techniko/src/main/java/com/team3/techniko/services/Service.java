package com.team3.techniko.services;

import java.util.List;
import java.util.Optional;

/**
 *
 * @param <T>
 * @param <K>
 */
public interface Service<T, K> {

    Optional<T> getById(K id);

    List<T> getAll();

    Optional<T> save(T t);

    boolean deleteById(K id);

}
