/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.Service;

import com.mycompany.vendingmachine.dao.VMDaoException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VMItems;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author cas
 */
public interface VMServiceLayer {
 
    public List<VMItems> getAllVMItems()throws VMDaoException;
    
    VMItems vend(int id)throws InsufficientFundsException,OutOfStockException, VMDaoException, InvalidIdException;
    

    public BigDecimal deposit(BigDecimal money) throws InsufficientFundsException ;

    public Change getUserChange();
    
}
