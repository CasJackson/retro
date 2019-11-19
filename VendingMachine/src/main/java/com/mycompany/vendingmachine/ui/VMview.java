/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine.ui;

import com.mycompany.vendingmachine.dto.Change;
import com.mycompany.vendingmachine.dto.VMItems;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cas
 */
public class VMview {

    UserIO io = new VMUserIOConsoleImpl();
    Scanner scn = new Scanner(System.in);

    public void displayVMItemsList(List<VMItems> VMItemsInfoList) {
        io.print("=========  VENDING  ITEMS    =========");

        for (VMItems currentVMItemsInfo : VMItemsInfoList) {
            io.print("Item Name: " + currentVMItemsInfo.getName()
                    + "  Item Id: " + currentVMItemsInfo.getId());
            io.print("    Price: " + currentVMItemsInfo.getPrice());
            io.print("    Qaunity: " + currentVMItemsInfo.getQuanity());
        }

    }
//    public void displayMainMenu() {
//        io.print("Main Menu");
//        io.print("1. insert money");
//        io.print("2. select item ");
//        io.print("3. return change");
//        io.print("4. Exit");
//
//    }

//    public int InsertMoney() {
//        return io.readInt("Please insert money");
//          }
    public BigDecimal getUserMoney() {
        BigDecimal money = io.readBigDecimal("Please enter money", new BigDecimal("0.01"), new BigDecimal("10.00"));
        return money;
    }

    public void displayTotalMoney(BigDecimal total) {
        io.print("Your total balance is: $" + total);
    }

    public int printMenuAndGetSelection() {

        io.print("Main Menu");
        io.print("1. insert money");
        io.print("2. select item ");
        io.print("3. return change");
        io.print("4. Exit");
        return io.readInt("Please make a selection from the main menu above.");

//        return io.readInt("Please enter 2 to select an item .", 2, 2);
    }

    public int getById() {
        return io.readInt("Please select a number 1 thru 7 to choice a item from the list above.", 1, 7);

    }

//    public void vend(VMItems item) {
//        io.readInt("You selected " + item + ", please press 3 to return change.", 0, 3);
//
//    }
    public void displayUnknownCommand() {
        io.print("Unknown command:");
    }

    public void displayExitMessage() {
        io.print("Goodbye.");
    }

//     public void VMItemsList(List<VMItems> VMItemsList) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public void displayErrorMessage(String message) {
        io.print(message);
    }

    public void returnUserChange(Change returnedChange) {
        io.readInt("Your change is $" + returnedChange.getDollars() + " Dollars " 
                                      + returnedChange.getQuarters() + " Qaurters "
                                      + returnedChange.getDimes() + " Dimes "
                                      + returnedChange.getNickels() + " Nickels "
                                      + returnedChange.getPennies() + " Pennies "
               + ", please press 4 to exit.", 4, 4);
    }

    public void displayItem(VMItems item) {
        io.print("You selected " + item.getName());
    }

}
