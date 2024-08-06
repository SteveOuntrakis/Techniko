/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.services;

import com.team3.techniko.model.PropertyOwner;
import com.team3.techniko.repositories.Repository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicePropertyOwnerImpl extends ServiceImpl<PropertyOwner> {

    private Repository repository;
    // with this command he stopped reddening the log
    private static final Logger log = LoggerFactory.getLogger(ServicePropertyOwnerImpl.class);

    public ServicePropertyOwnerImpl(Repository repository) {
        super(repository);
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
    
    public Optional<PropertyOwner> getUserName(String userName ) {
        if (userName == null || !userName.chars().allMatch(Character::isLetter) ) 
              
                 {
            throw new NoSuchElementException("Invalid username or password");
        
    }
        if(repository.findAllByUsername(userName).isEmpty()) {
            return Optional.empty();
        }
         return Optional.empty();
        
    }
    
    
}

