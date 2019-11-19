/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author cas
 */
public class InMemoOrderDao implements OrderDao {

    private List<Order> allOrders = new ArrayList<>();

    public InMemoOrderDao() {
        Order f1 = new Order();
        f1.setDate(LocalDate.of(2019, 9, 1));
        f1.setOrderNumber(1);
        f1.setCustomerName("Wise");
        f1.setState("OH");
        f1.setTaxRate(new BigDecimal("6.25"));
        f1.setProductType("Wood");
        f1.setArea(new BigDecimal("100.00"));
        f1.setCostPsf(new BigDecimal("5.15"));
        f1.setLaborCostPsf(new BigDecimal("4.75"));

        allOrders.add(f1);

        Order f2 = new Order();
        f2.setDate(LocalDate.of(2019, 9, 1));
        f2.setOrderNumber(2);
        f2.setCustomerName("Guy");
        f2.setState("IN");
        f2.setTaxRate(new BigDecimal("6.25"));
        f2.setProductType("Carpet");
        f2.setArea(new BigDecimal("100.00"));
        f2.setCostPsf(new BigDecimal("5.15"));
        f2.setLaborCostPsf(new BigDecimal("4.75"));

        allOrders.add(f2);

    }

    @Override
    public List<Order> displayAllByDate(LocalDate date) {
        return allOrders.stream().filter(o -> date.equals(o.getDate())).collect(Collectors.toList());
    }

    @Override
    public Order addOrder(Order toAdd) throws OrderDaoException {
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
        allOrders.add(toAdd);
        return toAdd;
        //1. get all other orders for the same date as toAdd
        //2. loop through all those orders to compute the max ordernumber
        //3. add one to the ordernumber
        //4. all to allOrders (NOT the list of orders just for the day)
    }

    @Override
    public Order editOrder(Order toEdit) throws OrderDaoException {
        //List<Order> orderList = displayAllByDate(toEdit.getDate());

        int editAt = -1;

        for (int i = 0; i < allOrders.size(); i++) {
            Order toCheck = allOrders.get(i);

            if (toCheck.getOrderNumber() == toEdit.getOrderNumber() && toCheck.getDate().equals(toEdit.getDate()));
            {
                editAt = i;
                break;
            }

        }
        if (editAt == -1) {
            throw new OrderDaoException("Invalid date or order number, could not edit.");

        }
        allOrders.set(editAt, toEdit);
        return toEdit;
    }

    @Override
    public void removeOrder(LocalDate date, int orderNum) throws OrderDaoException {
        //List<Order> orderList = displayAllByDate(date);

        int removeAt = -1;

        for (int i = 0; i < allOrders.size(); i++) {
            Order toCheck = allOrders.get(i);

            if (toCheck.getOrderNumber() == orderNum && toCheck.getDate().equals(toCheck.getDate())) {
                removeAt = i;
                break;
            }
        }

        allOrders.remove(removeAt);
    }

    @Override
    public Order getOrder(LocalDate date, int custNum) throws OrderDaoException {

        Order sinOrder = null;
        List<Order> matchOrder = displayAllByDate(date);
        for (Order toCheck : matchOrder) {
            if (toCheck.getOrderNumber() == custNum && toCheck.getDate().equals(toCheck.getDate())) {
                sinOrder = toCheck;
                break;
            }
        }

        return sinOrder;
    }
}


