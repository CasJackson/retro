/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.controller;

import com.mycompany.vendingmachine.Service.InsufficientFundsException;
import com.mycompany.vendingmachine.Service.InvalidIdException;
import com.mycompany.vendingmachine.Service.OutOfStockException;
import com.mycompany.vendingmachine.Service.ServiceLayerException;
import com.mycompany.vendingmachine.dao.VMDao;
import com.mycompany.vendingmachine.dao.VMDaoException;
import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VMItems;
import com.mycompany.vendingmachine.ui.VMview;
import java.math.BigDecimal;
import java.util.List;
import com.mycompany.vendingmachine.Service.VMServiceLayer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cas
 */
public class VMcontroller {

    VMview view;
    VMServiceLayer service;

    public VMcontroller(VMview view, VMServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {

            try {

                menuSelection = getMenuSelection();

                switch (menuSelection) {
                    case 1:
                        insertMoney();
                        break;
                    case 2:
                        vendItem();
                        break;
                    case 3:
                        returnChange();
                        break;

                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        view.displayUnknownCommand();
                }
            } catch (InsufficientFundsException | OutOfStockException | VMDaoException | InvalidIdException | ServiceLayerException ex) {
                  view.displayErrorMessage(ex.getMessage());

            }

        }
        view.displayExitMessage();
    }

    private int getMenuSelection() throws ServiceLayerException, VMDaoException {
        List<VMItems> allItems = service.getAllVMItems();
        view.displayVMItemsList(allItems);
        //       view.displayMainMenu(); 
        return view.printMenuAndGetSelection();

    }

    private BigDecimal insertMoney() throws InsufficientFundsException, OutOfStockException, VMDaoException {
        BigDecimal money = view.getUserMoney();
        BigDecimal total = service.deposit(money);
        view.displayTotalMoney(total);

        return total;
//      return view.InsertMoney();

        //       view.printMenuAndGetSelection();
//        int id = view.getById();
//                
// Change returnedChange = service.getUserChange();
//        view.returnUserChange(returnedChange);
    }

    private void vendItem() throws InsufficientFundsException, OutOfStockException, VMDaoException,InvalidIdException {
        int id = view.getById();
        VMItems item = service.vend(id);
        view.displayItem(item);
    }

    private void returnChange() {

        Change returnedChange = service.getUserChange();
        view.returnUserChange(returnedChange);
        view.displayExitMessage();
    }

}
