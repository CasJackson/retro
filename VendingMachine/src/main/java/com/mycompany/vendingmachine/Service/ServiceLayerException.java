/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.Service;

/**
 *
 * @author cas
 */
public class ServiceLayerException extends Exception {
     
    public ServiceLayerException(String message) {
        super(message);
    }

    public ServiceLayerException(String message, Throwable inner) {
        super(message, inner);
    }
}
