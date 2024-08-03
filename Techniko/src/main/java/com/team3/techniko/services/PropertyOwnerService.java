 
package com.team3.techniko.services;

 import com.team3.techniko.model.PropertyOwner;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Panagiotis
 */
 

public interface PropertyOwnerService   {


    Optional<PropertyOwner> findById(Long id);
    List<PropertyOwner> findAll();
    Optional<PropertyOwner> save(PropertyOwner propertyOwner);
    boolean deleteById(Long id);
     List<PropertyOwner> findByUserId(Long userId);
     List<PropertyOwner> findByDateRange(Date startDate, Date endDate);
     
    

}