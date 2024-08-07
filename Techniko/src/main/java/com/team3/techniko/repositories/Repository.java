package com.team3.techniko.repositories;

import com.team3.techniko.model.enums.Status;
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
    
    List<T> findPendingRepairs(Status status);
    
    List<T> findAllByPropertyId(Long propertyId);
    
    List<T> findPropertiesByUserID(Long userId);
    
    List<T> findPendingRepairsForID(Status status,Long id);
}
