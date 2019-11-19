/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

/**
 *
 * @author cas
 */
public class VMDaoException extends Exception  {
    
    public VMDaoException(String message) {
        super(message);
    }

    public VMDaoException(String message, Throwable inner) {
        super(message, inner);
    }
}


