/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.services;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.Repository;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
public class ServiceImpl<T> implements Service {

    private Repository repository;
    // with this command he stopped reddening the log
    private static final Logger log = LoggerFactory.getLogger(ServiceImpl.class); 

    public ServiceImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Optional getById(Object id) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findAll().get(i).equals(id)) {
                return repository.findById(id);

            }
        }
        throw new NoSuchElementException("Den vrethike  Property owner me id: " + id);

    }

    @Override
    public List getAll() {

        // Validate : if List<PropertyOwner> is empty return one empty list
        if (repository.findAll().isEmpty()) {
            return List.of();
        }
        return repository.findAll();
    }

    @Override
    public Optional save(Object t) {

        PropertyOwner propertyOwner = new PropertyOwner();

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
        if (propertyOwner.getOwnerId() != null &&   repository.findById(propertyOwner.getOwnerId()).isPresent() ) {
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
    public boolean deleteById(Object id) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findAll().get(i).equals(id)) {
                repository.deleteById(id);
                break;
            }
        }
        throw new NoSuchElementException("Den vrethike department me id: " + id);
    }

    @Override
    public List getByUserId(Object userId) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findByUserId(userId).get(i).equals(userId)) {
                return repository.findByUserId(userId);
            }
        }

        throw new NoSuchElementException("Den vrethike  user  me id: " + userId);
    }

    public List getByDateRange(Date startDate, Date endDate) {

        return repository.findByDateRange(startDate, endDate);

    }

    @Override
    public List getByDaterange(Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

}



