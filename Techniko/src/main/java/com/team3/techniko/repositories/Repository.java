package com.team3.techniko.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {

    Optional<T> findById(K id);

    List<T> findAll();

    Optional<T> save(T t);

    boolean deleteById(K id);

    List<T> findByUserId(K userId);

    List<T> findByDateRange(Date startDate, Date endDate);

    List<T> findAllByUsername(String username);
}
