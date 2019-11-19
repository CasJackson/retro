/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.ui;

import com.mycompany.flooring.dto.Order;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 *
 * @author cas
 */
public class FlooringViewEnglish implements FlooringView {

    UserIO io;
    //private String allOrders;

    public FlooringViewEnglish(UserIO io) {
        this.io = io;
    }

    @Override
    public int displayMenuAndGetUserChoice() {
        MenuChoice userChoice;
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");

        return io.readInt("Please make a selection from the menu above", 1, 5);
//
//       userChoice = getuserChoice();

    }

    @Override
    public void displayUnknownCommandError() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayExitMessage() {
        io.print("Goodbye.");
    }

    private void displayMenu() {

    }

//    private MenuChoice getuserChoice() {
//       
//        return userChoice;
//    }
    @Override
    public void displayByDate(List<Order> OrderInfoList) {
        //loop through orders
        //print some properties for each order (id, name, total cost)
        for (Order OrderInfo : OrderInfoList) {
            io.print("Order Number:" + OrderInfo.getOrderNumber());
            io.print("Customer Name:" + OrderInfo.getCustomerName());
            io.print(" Cost per square ft:" + OrderInfo.getCostPsf());
        }

    }

    @Override
    public Order getNewOrderInfo() {

        String customerName = io.readString("Please enter your name.");
        String state = io.readString("Please enter the state you reside in.");
        LocalDate date = io.readDate("Please enter today's date", LocalDate.MIN, LocalDate.MAX);
        String productType = io.readString("Please enter the type of flooring product you would like to purchase.");
        BigDecimal area = io.readBigDecimal("Please enter the area of your floor to be serviced.", BigDecimal.ONE, new BigDecimal("10000"));

        Order newOrderInfo = new Order();
        newOrderInfo.setCustomerName(customerName);
        newOrderInfo.setState(state);
        newOrderInfo.setDate(date);
        newOrderInfo.setProductType(productType);
        newOrderInfo.setArea(area);

        return newOrderInfo;
    }
//    @Override
//    public LocalDate displayByDate(LocalDate date) {
//           return date;
//    }

    @Override
    public LocalDate getUserDate() {
        LocalDate toReturn = io.readDate("Please enter the day of your purchase.");

        return toReturn;

    }

    @Override
    public void displayErrorMessage(String message) {
        io.print("Error:" + message);
    }

    @Override
    public void displayAddedOrder(Order addedOrder) {

          io.print("Your order information is ");
        io.print("Order Number: " + addedOrder.getOrderNumber());
        io.print("Customer Name: " + addedOrder.getCustomerName());
        io.print(" Cost per square ft: " + addedOrder.getCostPsf());
        io.print("Date: " + addedOrder.getDate());
        io.print("Cost of Labor: " + addedOrder.getTotalLaborCost());
        io.print("Cost of material: " + addedOrder.getTotalMaterialCost());
        io.print("Tax " + addedOrder.getTax());
        io.print("Total Cost: " + addedOrder.getTotalCost());

    }

    @Override
    public Order getEditInfo(Order toEdit) {
//        io.readInt("Please enter your customer order number.");
//        io.print("1. Edit customer name ");
//        io.print("2. Edit State ");
//        io.print("3. Edit flooring ");
//        io.print("4. Edit area");
//
//        io.readInt("Please make a selection from the menu above", 1, 4);
        String customerName = io.editString("Please enter new customer name or current name.", toEdit.getCustomerName() );
        String state = io.editString("Please enter the new state or current state.", toEdit.getState());
        String productType = io.editString("Please enter the new type of flooring product or current flooring product.", toEdit.getProductType());
        BigDecimal area = io.editBigDecimal("Please enter the  new area of your floor to be serviced or current area.", toEdit.getArea());
       
        
        
//        Order editOrderInfo = new Order();
        toEdit.setCustomerName(customerName);
        toEdit.setState(state);
        toEdit.setProductType(productType);
        toEdit.setArea(area);
        return toEdit;
    }

    @Override
    public void displayUpdatedOrder(Order editedOrder) {
        
        io.print("Your order information is ");
        io.print("Order Number: " + editedOrder.getOrderNumber());
        io.print("Customer Name: " + editedOrder.getCustomerName());
        io.print("State: " + editedOrder.getState() );
        io.print("Product Type: " + editedOrder.getProductType());
        io.print("Area: " + editedOrder.getArea());
        io.print(" Cost per square ft: " + editedOrder.getCostPsf());
        io.print("Date: " + editedOrder.getDate());
        io.print("Cost of Labor: " + editedOrder.getTotalLaborCost());
        io.print("Cost of material: " + editedOrder.getTotalMaterialCost());
        io.print("Tax " + editedOrder.getTax());
        io.print("Total Cost: " + editedOrder.getTotalCost());

        
    }

    @Override
    public void displayDeleteMessage() {
        io.print("Your order has been successfully deleted.");

    }

    @Override
    public int getCustomerNum() {
       int custNam = io.readInt("Please enter your customer number.");
        return custNam;
    }

    @Override
    public String getCustomerName() {
      String custName =  io.readString("Please enter your name.");
        return custName;
    }

}
