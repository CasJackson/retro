/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.service;

/**
 *
 * @author cas
 */
public class InvalidProductException extends Exception {

    public InvalidProductException(String message) {
        super(message);
    }
    public InvalidProductException(String message, Throwable inner) {
        super(message, inner);
    }
    
}
