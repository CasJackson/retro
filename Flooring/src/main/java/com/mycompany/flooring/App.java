package com.mycompany.flooring;

import com.mycompany.flooring.controller.FlooringController;
import com.mycompany.flooring.ui.FlooringViewEnglish;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.mycompany.flooring.ui.FlooringView;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cas
 */
public class App {
    public static void main(String[] args) {
     
//        FlooringView view = new FlooringViewEnglish(); 
//    
//    FlooringController controller = new FlooringController(view);

    ApplicationContext ctx = new ClassPathXmlApplicationContext ("applicationContext.xml");
    FlooringController controller = ctx.getBean("controller", FlooringController.class);
 
    controller.run();
}
}