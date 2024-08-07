package com.team3.techniko.services;

import com.team3.techniko.model.Property;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.Repository;
import java.util.List;

public class PropertyService extends ServiceImpl<Property> {

    public PropertyService(Repository<Property, Long> propertyServiceRepository) {
        super(propertyServiceRepository);
    }

    public List<Property> findPropertiesByUserID(Long id) {
        try {
            List<Property> properties = repository.findPropertiesByUserID(id);
            return properties;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Property> findPendingRepairsForID(Status status, Long id) {
        try {
            List<Property> properties = repository.findPendingRepairsForID(status, id);
            return properties;
        } catch (Exception e) {
            return null;
        }
    }

}
