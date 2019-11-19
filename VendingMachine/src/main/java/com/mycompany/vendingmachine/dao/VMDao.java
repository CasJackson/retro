/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.dto.VMItems;
import java.util.List;

/**
 *
 * @author cas
 */
public interface VMDao {
    
    List<VMItems> getAllVMItems()throws VMDaoException;
    
    VMItems getById(int id)throws VMDaoException;
    
        
    void editVMItems ( VMItems toEdit)throws VMDaoException;
    
}
