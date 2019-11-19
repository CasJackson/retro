/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.dao;

import com.mycompany.vendingmachine.Service.InvalidIdException;
import com.mycompany.vendingmachine.dto.VMItems;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cas
 */
public class VMDaoInMemo implements VMDao {

    private List<VMItems> itemList = new ArrayList<>();

    public VMDaoInMemo() {
        VMItems inStock = new VMItems();
        inStock.setId(8);
        inStock.setName("twix");
        inStock.setPrice(new BigDecimal("1.25"));
        inStock.setQuanity(20);

        itemList.add(inStock);

        VMItems outOfStock = new VMItems();
        outOfStock.setId(9);
        outOfStock.setName("Clarks");
        outOfStock.setPrice(new BigDecimal(1.00));
        outOfStock.setQuanity(0);

        itemList.add(outOfStock);

    }

    @Override
    public List<VMItems> getAllVMItems() throws VMDaoException {
        return itemList;
    }

    @Override
    public VMItems getById(int id) throws VMDaoException {
        VMItems toReturn = null;

        List<VMItems> allVMItems = getAllVMItems();

        for (VMItems toCheck : allVMItems) {
            if (toCheck.getId() == id) {
                toReturn = toCheck;
                break;

            }
            if (toReturn == null) {
                try {
                    throw new InvalidIdException("couldnt find id ");
                } catch (InvalidIdException ex) {

                }

            }
        }
        return toReturn;
    }

    @Override
    public void editVMItems( VMItems toEdit) throws VMDaoException {

    }

}
