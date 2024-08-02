package com.team3.techniko.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @param <T> the model class
 * @param <K> the key class
 */
public interface Repository<T, K> {

    Optional<T> findById(K id);

    List<T> findAll();

    Optional<T> save(T t);

    boolean deleteById(K id);

   List<T> findByUserId(K userId);

    List<T> findByDateRange(Date startDate, Date endDate);
}
