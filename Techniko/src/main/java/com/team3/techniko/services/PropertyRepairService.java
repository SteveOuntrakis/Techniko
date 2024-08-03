 package com.team3.techniko.services;

 import com.team3.techniko.model.PropertyRepair;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PropertyRepairService  {

    Optional<PropertyRepair> findById(Long id);
    List<PropertyRepair> findAll();
    Optional<PropertyRepair> save(PropertyRepair propertyRepair);
    boolean deleteById(Long id);
    List<PropertyRepair> findByUserId(Long id);
     List<PropertyRepair> findByDateRange(Date startDate, Date endDate);
     
     
     
}
