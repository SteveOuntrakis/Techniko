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
public class DublicatePropertyOwnerException extends NoSuchElementException{
    public DublicatePropertyOwnerException(String message) {
        super(message);
    }
}
