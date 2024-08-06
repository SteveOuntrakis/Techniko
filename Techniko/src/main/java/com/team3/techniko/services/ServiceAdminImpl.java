/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.services;

import com.team3.techniko.exceptions.AdminNotFoundException;
import com.team3.techniko.exceptions.InvalidInputException;
import com.team3.techniko.model.Admin;
import com.team3.techniko.repositories.Repository;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class ServiceAdminImpl extends ServiceImpl<Admin> {
    
    private   Repository  repository;

    public ServiceAdminImpl(Repository repository) {
        super(repository);
        this.repository = repository;
    }
    
    
    public Optional<Admin> findByUsername(String username, String password) throws Exception {
        if  (username == null || !username.chars().allMatch(Character::isLetter) || 
            (password == null || !password.isBlank() )    
            ) {
            throw new InvalidInputException("Invalid username or password");
        }
        
        
        try 
        {List<Admin> admins = repository.findAllByUsername(username);
        if (admins.isEmpty()) {
            throw new AdminNotFoundException("Admin not found username: " + username);
        }return Optional.of(admins.get(0));
        } catch (AdminNotFoundException e) {
           log.debug("An exception occured");
            throw new AdminNotFoundException("An error occurred while fetching the admin: " + e.getMessage());
        }
    
    }
    
    
    
    
    
}
