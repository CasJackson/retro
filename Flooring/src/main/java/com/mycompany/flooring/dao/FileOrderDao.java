/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Order;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cas
 */
public class FileOrderDao implements OrderDao {

    String folder;

    public FileOrderDao(String folder) {
        this.folder = folder;
    }

    @Override
    public List<Order> displayAllByDate(LocalDate date) throws OrderDaoException {

        List<Order> allOrders = new ArrayList<>();
        FileReader reader = null;

        try {
            String path = buildPath(date);
            reader = new FileReader(path);

            Scanner scn = new Scanner(reader);
            scn.nextLine();
            while (scn.hasNextLine()) {
                String line = scn.nextLine();

                String[] cells = line.split(",");
                    
                Order toAdd = new Order();
                toAdd.setDate(date);

                // throw new UnsupportedOperationException( "TODO: set order number with cells[0]");
                toAdd.setOrderNumber(Integer.parseInt(cells[0]));
                toAdd.setCustomerName(cells[1]);
                toAdd.setState(cells[2]);
                toAdd.setTaxRate(new BigDecimal(cells[3]));
                toAdd.setProductType(cells[4]);
                toAdd.setArea(new BigDecimal(cells[5]));
                toAdd.setCostPsf(new BigDecimal(cells[6]));
                toAdd.setLaborCostPsf(new BigDecimal(cells[7]));

                allOrders.add(toAdd);
            }
        } catch (FileNotFoundException ex) {

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new OrderDaoException("could not close reader for " + folder, ex);
            }
        }
        return allOrders;

    }

    private void writeFile(List<Order> allOrders, LocalDate date) throws OrderDaoException {

        String path = buildPath(date);

        FileWriter writer = null;
        try {

            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);
            pw.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");

            for (Order toWrite : allOrders) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new OrderDaoException("ERROR: could not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new OrderDaoException("ERROR: could not close writer for " + path, ex);
            }
        }
    }

    private String convertToLine(Order toWrite) {

        String line
                = toWrite.getOrderNumber() + ","
                + toWrite.getCustomerName() + ","
                + toWrite.getState() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductType() + ","
                + toWrite.getArea() + ","
                + toWrite.getCostPsf() + ","
                + toWrite.getLaborCostPsf() + ","
                + toWrite.getTotalMaterialCost() + ","
                + toWrite.getTotalLaborCost() + ","
                + toWrite.getTax() + ","
                + toWrite.getTotalCost();

        return line;
    }

    private String buildPath(LocalDate date) {
        return Path.of(folder, "Order_" + date.format(DateTimeFormatter.ofPattern("MMddyyyy")) + ".txt").toString();
        //return folder ;
    }

    @Override
    public Order addOrder(Order toAdd) throws OrderDaoException {
        if( toAdd == null ){
            throw new OrderDaoException( "Cannot add null order.");
        }
        
        try {
            LocalDate date = toAdd.getDate();
            List<Order> orderList = displayAllByDate(date);
            
            int orderId = -1;
            
            for (Order toCheck : orderList) {
                
                if (toCheck.getOrderNumber() > orderId) {
                    orderId = toCheck.getOrderNumber();
                }
                
            }
            orderId++;
            toAdd.setOrderNumber(orderId);
            orderList.add(toAdd);
            
            writeFile(orderList, toAdd.getDate());
            return toAdd;
        } catch (OrderDaoException ex) {
            throw new OrderDaoException("Could not add new order.");
        }

    }

    @Override
    public Order editOrder(Order toEdit) throws OrderDaoException {

        if( toEdit == null ){
            throw new OrderDaoException( "Cannot edit null order." );
        }
        List<Order> orderList = displayAllByDate(toEdit.getDate());

        int editAt = -1;

        for (int i = 0; i < orderList.size(); i++) {
            Order toCheck = orderList.get(i);

            if (toCheck.getOrderNumber() == toEdit.getOrderNumber()) {
                editAt = i;
                break;
            }

        }
        if (editAt == -1) {
            throw new OrderDaoException("Invalid date or order number, could not edit.");

        }
        orderList.set(editAt, toEdit);
        writeFile(orderList, toEdit.getDate());

        return toEdit;

    }

    @Override
    public void removeOrder(LocalDate date, int orderNum) throws OrderDaoException {

        List<Order> orderList = displayAllByDate(date);

        int removeAt = -1;

        for (int i = 0; i < orderList.size(); i++) {
            Order toCheck = orderList.get(i);

            if (toCheck.getOrderNumber() == orderNum) {
                removeAt = i;
                break;
            }

        }
        if (removeAt == -1) {
            throw new OrderDaoException("Could not find order " + date + orderNum + " to remove.");

        }
        orderList.remove(removeAt);
        writeFile(orderList, date);

    }

    @Override
    public Order getOrder(LocalDate date, int custNum) throws OrderDaoException {
        Order sinOrder = null;
        List<Order> matchOrder = displayAllByDate(date);
        for (Order toCheck : matchOrder) {
            if (toCheck.getOrderNumber() == custNum) {
                sinOrder = toCheck;
                break;
            }
        }
        
        return sinOrder;
    }

}
