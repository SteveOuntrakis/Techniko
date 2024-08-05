/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

 

/**
 *
 * @author Panagiotis
 */
public  interface Service<T, K> {
    
    Optional<T> getById(K id);
    
     
    List<T> getAll();
    
     Optional<T> save(T t);
    
     boolean deleteById(K id);
    
  List<T> getByUserId(K userId);
     List<T> getByDaterange(Date startDate, Date endDate);
    
    
    
}
    

