/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.controller;

import com.mycompany.flooring.dao.FileProductDaoException;
import com.mycompany.flooring.dao.FileTaxDaoException;
import com.mycompany.flooring.dao.OrderDaoException;
import com.mycompany.flooring.dto.Order;
import com.mycompany.flooring.service.FlooringService;
import com.mycompany.flooring.service.FlooringServiceException;
import com.mycompany.flooring.service.FlooringServiceImpl;
import com.mycompany.flooring.service.InvalidAreaException;
import com.mycompany.flooring.service.InvalidDateException;
import com.mycompany.flooring.service.InvalidOrderException;
import com.mycompany.flooring.service.InvalidProductException;
import com.mycompany.flooring.service.InvalidStateException;
import com.mycompany.flooring.ui.MenuChoice;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.flooring.ui.FlooringView;

/**
 *
 * @author cas
 */
public class FlooringController {

    FlooringView view;
    FlooringService service;

    //LocalDate userDate;
    public FlooringController(FlooringView view, FlooringService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean userExit = false;
        int menuSelection = 0;

        while (!userExit) {
            try {
                menuSelection = MenuAndGetUserChoice();
                switch (menuSelection) {

                    case 1:
                        displayAllByDate();
                        break;

                    case 2:
                        addOrderData();
                        break;

                    case 3:
                        edit();
                        break;

                    case 4:
                        delete();
                        break;

                    case 5:
                        userExit = true;
                        break;

                    default:
                        view.displayUnknownCommandError();

                }
            } catch (FlooringServiceException ex) {
                view.displayErrorMessage(ex.getMessage());
            } catch (InvalidStateException | InvalidProductException | InvalidAreaException | FileTaxDaoException | FileProductDaoException | OrderDaoException | InvalidOrderException | InvalidDateException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }
        view.displayExitMessage();
    }

    private int MenuAndGetUserChoice() throws FlooringServiceException {
//     List<Order> allOrders = service.getOrdersByDate(userDate);
//     view.displayByDate(allOrders);
//        //       view.displayMainMenu(); 
        return view.displayMenuAndGetUserChoice();
    }

    private void displayAllByDate() throws FlooringServiceException, InvalidDateException {

        LocalDate date = view.getUserDate();
        List<Order> allOrders = service.getOrdersByDate(date);
        view.displayByDate(allOrders);

    }

    private void addOrderData() throws InvalidStateException,InvalidDateException, InvalidProductException, FlooringServiceException, InvalidAreaException, FileTaxDaoException, FileProductDaoException {
        Order newOrderInfo = view.getNewOrderInfo();
        Order addedOrder = service.addOrder(newOrderInfo);
        view.displayAddedOrder(addedOrder);
    }

    private void edit() throws InvalidStateException, InvalidProductException, InvalidAreaException, FlooringServiceException, FileTaxDaoException, FileProductDaoException, InvalidOrderException,OrderDaoException, InvalidDateException{
        LocalDate date = view.getUserDate();
        
        int custNum = view.getCustomerNum();
        
        Order toEdit = service.getOrder(date, custNum);
        
        Order edited = view.getEditInfo(toEdit);
        Order editedOrder = service.editOrder(edited);
        view.displayUpdatedOrder(editedOrder);
        
    }

    private void delete() throws OrderDaoException, InvalidOrderException, InvalidDateException{
        LocalDate date = view.getUserDate();
        int custNum = view.getCustomerNum();
        Order deleteOrder = service.deleteOrder(date, custNum);
        view.displayDeleteMessage();
        
    }
}
