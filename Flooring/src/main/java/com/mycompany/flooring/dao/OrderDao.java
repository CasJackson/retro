/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Order;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author cas
 */
public interface OrderDao {

    public List<Order>  displayAllByDate(LocalDate date)throws OrderDaoException;
   public Order addOrder(Order toAdd)throws OrderDaoException;
//   public Order addNewOrder(Order toAdd)
    public Order editOrder(Order toEdit) throws OrderDaoException;
    
    public void removeOrder(LocalDate date, int orderNum)throws OrderDaoException;

    public Order getOrder(LocalDate date, int custNum)throws OrderDaoException;
        
    
}
