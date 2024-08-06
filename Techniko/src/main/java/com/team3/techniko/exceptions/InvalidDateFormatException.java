/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.team3.techniko.exceptions;

/**
 *
 * @author Panagiotis
 */
public class InvalidDateFormatException extends RuntimeException{
    public InvalidDateFormatException(String message){
        super(message);
    }
}
