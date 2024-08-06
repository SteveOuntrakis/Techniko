package com.team3.techniko.services;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.Repository;
import java.util.List;
import java.util.Optional;

public class PropertyUserService extends ServiceImpl<PropertyOwner> {

    public PropertyUserService(Repository<PropertyOwner, Long> repository) {
        super(repository);
    }

    public Optional<PropertyOwner> findOwnerByUsername(String username) {
        try {
            List<PropertyOwner> owners = repository.findAllByUsername(username);
            return owners.isEmpty() ? Optional.empty() : Optional.of(owners.get(0));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public boolean validatePropertyOwnerPassword(String password, PropertyOwner owner) {
        return password.equals(owner.getPassword());
    }
}
