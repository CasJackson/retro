/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.service;

import com.mycompany.flooring.dao.FileProductDaoException;
import com.mycompany.flooring.dao.FileTaxDaoException;
import com.mycompany.flooring.dao.OrderDaoException;
import com.mycompany.flooring.dto.Order;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.flooring.dao.TaxDao;
import com.mycompany.flooring.dao.OrderDao;
import com.mycompany.flooring.dao.ProductDao;
import com.mycompany.flooring.dto.Product;
import com.mycompany.flooring.dto.Tax;

/**
 *
 * @author cas
 */
public class FlooringServiceImpl implements FlooringService {

    OrderDao dao;
    TaxDao tdao;
    ProductDao pdao;

    public FlooringServiceImpl(OrderDao dao, TaxDao tdao, ProductDao pdao) {
        this.dao = dao;
        this.tdao = tdao;
        this.pdao = pdao;
    }

    public List<Order> getOrdersByDate(LocalDate date) throws FlooringServiceException, InvalidDateException {
//        if(toAdd.getDate().isBefore(LocalDate.now())){
//            throw new InvalidDateException("ERROR:Please enter a valid Date");
//        }
        try {
            List<Order> allOrders = dao.displayAllByDate(date);
            return allOrders;

        } catch (OrderDaoException ex) {
            throw new FlooringServiceException("Couldn't get all orders from dao", ex);
        }

    }

    @Override
    public Order addOrder(Order toAdd) throws InvalidStateException, InvalidProductException, InvalidAreaException, FlooringServiceException, FileTaxDaoException, InvalidDateException, FileProductDaoException {

        Tax userTax = tdao.getTaxByState(toAdd.getState());

        if (userTax == null) {
            throw new InvalidStateException("Invalid state.");
        }

        Product userProd = pdao.getProductByName(toAdd.getProductType());

        if (userProd == null) {
            throw new InvalidProductException("Invalid Product.");
        }

        toAdd.setTaxRate(userTax.getTaxRate());
        toAdd.setCostPsf(userProd.getCostPsf());
        toAdd.setLaborCostPsf(userProd.getLaborCostPsf());

        if (toAdd.getArea().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAreaException("InvalidAreaInput.");
        }
        if (toAdd.getDate().isBefore(LocalDate.now())) {
            throw new InvalidDateException("ERROR:Please enter a valid Date");
        }
        // if date enter in your getStatement".isBefore(LocalDate.now());
        try {
            dao.addOrder(toAdd);

            //return toAdd;
        } catch (OrderDaoException ex) {
            throw new FlooringServiceException("Could not add order");
        }
        return toAdd;
    }

    @Override
    public Order editOrder(Order edited) throws InvalidStateException, InvalidProductException, InvalidAreaException, FlooringServiceException, FileTaxDaoException, FileProductDaoException, InvalidDateException {
        Tax userTax = tdao.getTaxByState(edited.getState());

        if (userTax == null) {
            throw new InvalidStateException("Invalid state.");
        }

        Product userProd = pdao.getProductByName(edited.getProductType());

        if (userProd == null) {
            throw new InvalidProductException("Invalid Product.");
        }

        edited.setTaxRate(userTax.getTaxRate());
        edited.setCostPsf(userProd.getCostPsf());
        edited.setLaborCostPsf(userProd.getLaborCostPsf());

        if (edited.getArea().compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidAreaException("InvalidAreaInput.");

        }
        try {
            dao.editOrder(edited);

            //return toAdd;
        } catch (OrderDaoException ex) {
            throw new FlooringServiceException("Could not add order");
        }
        return edited;
    }

    @Override
    public Order deleteOrder(LocalDate date, int custNum) throws InvalidDateException, OrderDaoException, InvalidOrderException {
        Order removeOrder = dao.getOrder(date, custNum);

        if (removeOrder == null) {
            throw new InvalidDateException("ERROR:Please enter a valid Date");
        }

        dao.removeOrder(date, custNum);

        return removeOrder;
    }

    @Override
    public Order getOrder(LocalDate date, int custNum) throws OrderDaoException, InvalidOrderException, InvalidDateException {
//        if(edited.getDate().isBefore(LocalDate.now())){
//            throw new InvalidDateException("ERROR:Please enter a valid Date");
//        }
        Order getIt = dao.getOrder(date, custNum);
        if (getIt == null) {
            throw new InvalidDateException("ERROR:Please enter a valid Date");
        }
        return dao.getOrder(date, custNum);

    }

}
