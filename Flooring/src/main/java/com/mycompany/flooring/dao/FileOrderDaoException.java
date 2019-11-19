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
class FileOrderDaoException extends Exception {
     public FileOrderDaoException(String message) {
        super (message);
        
    }
    public FileOrderDaoException(String message, Throwable inner) {
        super (message,inner);
        
    }
}
