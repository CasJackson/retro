/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

/**
 *
 * @author cas
 */
public class FileProductDaoException extends Exception {
   public FileProductDaoException(String message) {
        super (message);
        
    }
    public FileProductDaoException(String message, Throwable inner) {
        super (message,inner);
        
    }  
}
