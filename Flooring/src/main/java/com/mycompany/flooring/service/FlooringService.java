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
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author cas
 */
public interface FlooringService {

    public List<Order> getOrdersByDate(LocalDate date) throws FlooringServiceException, InvalidDateException;
    
    public Order addOrder(Order toAdd) throws InvalidStateException,InvalidDateException, InvalidProductException, InvalidAreaException, FlooringServiceException,FileTaxDaoException, FileProductDaoException;

    public Order editOrder(Order edited)throws InvalidStateException,InvalidDateException, InvalidProductException, InvalidAreaException, FlooringServiceException,FileTaxDaoException, FileProductDaoException;

    public Order deleteOrder(LocalDate date, int custNum)throws OrderDaoException,InvalidDateException,InvalidOrderException, InvalidDateException;

    public Order getOrder(LocalDate date, int custNum)throws OrderDaoException,InvalidOrderException,InvalidDateException;

}
