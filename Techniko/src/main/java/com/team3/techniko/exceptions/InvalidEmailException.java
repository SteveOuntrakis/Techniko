/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.exceptions;

import java.util.NoSuchElementException;

/**
 *
 * @author Panagiotis
 */
public class InvalidEmailException extends NoSuchElementException{
    
    public InvalidEmailException(String message) {
        super(message);
    }
}
