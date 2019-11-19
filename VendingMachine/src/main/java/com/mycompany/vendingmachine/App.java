/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachine;

import com.mycompany.vendingmachine.Service.InvalidIdException;
import com.mycompany.vendingmachine.Service.OutOfStockException;
import com.mycompany.vendingmachine.Service.ServiceLayerException;
import com.mycompany.vendingmachine.controller.VMcontroller;
import com.mycompany.vendingmachine.dao.VMDaoException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author cas
 */
public class App {
    
    public static void main(String[] args) {
//        VMview view = new VMview();
//        VMDao dao = new VMDaoFileImpl ("VMIList.txt");
//        VMServiceLayer service = new VMServiceLayerImpl(dao);
//        VMcontroller controller = new VMcontroller (view, service);
//        controller.run();

            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
               VMcontroller controller = ctx.getBean("controller", VMcontroller.class);
               controller.run();

    }
}
