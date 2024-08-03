/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.services;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.Repository;
import lombok.extern.slf4j.Slf4j;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
public class PropertyOwnerServiceImpl implements PropertyOwnerService {

    private Repository<PropertyOwner, Long> repository;

    public PropertyOwnerServiceImpl(Repository<PropertyOwner, Long> repository) {
        this.repository = repository;
    }

    public PropertyOwnerServiceImpl() {

    }

    @Override
    public Optional<PropertyOwner> findById(Long id) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findAll().get(i).getOwnerId().equals(id)) {
                return repository.findById(id);

            }
        }
        throw new NoSuchElementException("Den vrethike  Property owner me id: " + id);
    }

    @Override
    public List<PropertyOwner> findAll() {

        // Validate : if List<PropertyOwner> is empty return one empty list
        if (repository.findAll().isEmpty()) {
            return List.of();
        }
        return repository.findAll();

    }

    @Override
    public Optional<PropertyOwner> save(PropertyOwner propertyOwner) {
        // Validations:  If the fields are empty  or nulls

        if (propertyOwner.getVatNumber() == null || propertyOwner.getVatNumber().isEmpty()
                || propertyOwner.getName() == null || propertyOwner.getName().isEmpty()
                || propertyOwner.getSurname() == null || propertyOwner.getSurname().isEmpty()
                || propertyOwner.getAddress() == null || propertyOwner.getAddress().isEmpty()
                || propertyOwner.getPhoneNumber() == null || propertyOwner.getPhoneNumber().isEmpty()
                || propertyOwner.getEmail() == null || propertyOwner.getEmail().isEmpty()
                || propertyOwner.getUsername() == null || propertyOwner.getUsername().isEmpty()
                || propertyOwner.getPassword() == null || propertyOwner.getPassword().isEmpty()) {

            throw new NoSuchElementException("The optional is empty, please add it");
        }

        // validate for if the property owner already exist
        if (propertyOwner.getOwnerId() != null && repository.findById(propertyOwner.getOwnerId()).isPresent()) {
            return Optional.empty();
        }

        // validate or if the VAT number is unique
        if (!propertyOwner.getVatNumber().isEmpty()) {
            return Optional.empty();
        }

        if (!propertyOwner.getEmail().isBlank()) {
            log.warn("Your email is empty please add your email");
            return Optional.empty();
        }

        // validate or if the user name is unique
        if (!propertyOwner.getUsername().isBlank()) {
            log.warn("You username is incorrect, please add the real username");
        }
        if (!propertyOwner.getPassword().isBlank()) {
            log.warn("You password is incorrect, please add the real password");
        }

        return repository.save(propertyOwner);
    }

    @Override
    public boolean deleteById(Long id) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findAll().get(i).getOwnerId().equals(id)) {
                repository.deleteById(id);
                break;
            }
        }

        throw new NoSuchElementException("Den vrethike department me id: " + id);
}

    @Override
    public List<PropertyOwner> findByUserId(Long userId) {

        for (PropertyOwner currentOwner : repository.findAll()) {
            if (currentOwner.getOwnerId().equals(userId)) {
                return repository.findByUserId(userId);
            }
        }

        throw new NoSuchElementException("Den vrethike  user  me id: " + userId);
    }

    @Override
    public List<PropertyOwner> findByDateRange(Date startDate, Date endDate) {
        return repository.findByDateRange(startDate, endDate);
    }
}
