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
public class AlwaysFailDao implements VMDao {

    @Override
    public List<VMItems> getAllVMItems() throws VMDaoException {
        throw new VMDaoException("Always fail.");
    }

    @Override
    public VMItems getById(int id) throws VMDaoException {
        throw new VMDaoException("Always fail.");

    }

    @Override
    public void editVMItems( VMItems toEdit) throws VMDaoException {
        throw new VMDaoException("Always fail.");

    }

}
