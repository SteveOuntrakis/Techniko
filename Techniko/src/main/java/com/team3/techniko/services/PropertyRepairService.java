package com.team3.techniko.services;

import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.Repository;
import java.util.List;

public class PropertyRepairService extends ServiceImpl<PropertyRepair> {

    public PropertyRepairService(Repository<PropertyRepair, Long> propertyRepairRepository) {
        super(propertyRepairRepository);
    }

    public List<PropertyRepair> findPendingRepairs(Status status) {
        try {
            List<PropertyRepair> owners = repository.findPendingRepairs(status);
            return owners;
        } catch (Exception e) {
            return null;
        }
    }

    public List<PropertyRepair> findAllByPropertyId(Long id) {
        try {
            List<PropertyRepair> owners = repository.findAllByPropertyId(id);
            return owners;
        } catch (Exception e) {
            return null;
        }
    }

    public List<PropertyRepair> findPendingRepairsForID(Status status, Long id) {
        try {
            List<PropertyRepair> owners = repository.findPendingRepairsForID(status, id);
            return owners;
        } catch (Exception e) {
            return null;
        }

    }

}
