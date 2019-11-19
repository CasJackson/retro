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
public class OutOfStockException extends Exception{
    public OutOfStockException (String message) {
        super (message);
    }
    public OutOfStockException (String message, Throwable inner ) {
        super (message,inner);
    }
    
}
