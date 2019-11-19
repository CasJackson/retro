/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.ui;

import com.mycompany.flooring.dto.Order;
import java.time.LocalDate;
import java.util.List;
import org.springframework.cglib.core.Local;

/**
 *
 * @author cas
 */
public interface FlooringView {

    /**
     *
     * @return
     */
    public int displayMenuAndGetUserChoice();

    public void displayUnknownCommandError();


    public void displayExitMessage();



   public void displayByDate(List<Order> orderDate);

//    public LocalDate displayByDate(LocalDate date);

    public LocalDate getUserDate();

    public void displayErrorMessage(String message);

    public Order getNewOrderInfo();

    public void displayAddedOrder(Order addedOrder);

    public Order getEditInfo(Order editOrder);

    public void displayUpdatedOrder(Order editedOrder);


    public void displayDeleteMessage();

    public int getCustomerNum();

    public String getCustomerName();

   
    
}
