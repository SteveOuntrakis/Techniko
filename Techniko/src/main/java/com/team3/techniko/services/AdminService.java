package com.team3.techniko.services;

import com.team3.techniko.model.Admin;
import com.team3.techniko.model.PropertyRepair;
import com.team3.techniko.model.enums.Status;
import com.team3.techniko.repositories.Repository;
import java.util.List;
import java.util.Optional;

public class AdminService extends ServiceImpl<Admin> {

    private final Repository<PropertyRepair, Long> propertyRepairRepository;

    public AdminService(Repository<Admin, Long> adminRepository, Repository<PropertyRepair, Long> propertyRepairRepository) {
        super(adminRepository);
        this.propertyRepairRepository = propertyRepairRepository;
    }

    public Optional<Admin> findAdminByUsername(String username) {
        try {
            List<Admin> admins = repository.findAllByUsername(username);
            return admins.isEmpty() ? Optional.empty() : Optional.of(admins.get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean validateAdminsPassword(String password, Admin admin) {
        return password.equals(admin.getPassword());
    }

    public List<PropertyRepair> findPendingRepairs() {
        return propertyRepairRepository.findPendingRepairs(Status.PENDING);
    }

    public List<PropertyRepair> findScheduledRepairs() {
        return propertyRepairRepository.findPendingRepairs(Status.IN_PROGRESS);
    }

    public List<PropertyRepair> findCompletedRepairs() {
        return propertyRepairRepository.findPendingRepairs(Status.COMPLETED);
    }

}
