/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.Service;

import com.mycompany.vendingmachine.dao.VMDao;
import com.mycompany.vendingmachine.dao.VMDaoException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VMItems;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cas
 */
public class VMServiceLayerImpl implements VMServiceLayer {

    Scanner scn = new Scanner(System.in);

    VMDao dao;
    BigDecimal totalMoney = BigDecimal.ZERO;

    public VMServiceLayerImpl(VMDao dao) {
        this.dao = dao;
    }

    @Override
    public List<VMItems> getAllVMItems() throws VMDaoException {

        try {
            List<VMItems> allItems = dao.getAllVMItems();
            return allItems;

        } catch (VMDaoException ex) {
            throw new VMDaoException("All items could not be found.");
        }

    }

    @Override
    public VMItems vend(int id) throws InsufficientFundsException, OutOfStockException, VMDaoException, InvalidIdException {
        VMItems item = dao.getById(id);
        if(item == null) {
            throw new InvalidIdException ("Id not found.");
        }
        
        int qaunity = item.getQuanity();

        if (totalMoney.compareTo(item.getPrice()) < 0) {

            throw new InsufficientFundsException("Error balance too low.");
        }
        if (item.getQuanity() < 1) {
            throw new OutOfStockException("Item is out of stock.");

        }
        item.setQuanity(qaunity - 1);
        dao.editVMItems( item);
        totalMoney = totalMoney.subtract(item.getPrice());
        //get item from dao by id
        // check if insert money is greater than price
        // check if item qaunity is greater than 1
        // write back to file to update the qaunity
        // update the total amount by subtracting price form total
        return item;
    }

    @Override
    public BigDecimal deposit(BigDecimal money) throws InsufficientFundsException {
        if (money.compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException("Please deposit 1 dollar or more.");
        }

        totalMoney = totalMoney.add(money);
        return totalMoney;
    }

    @Override
    public Change getUserChange() {

        Change currentChange = new Change(totalMoney);
        return currentChange;
    }
}
