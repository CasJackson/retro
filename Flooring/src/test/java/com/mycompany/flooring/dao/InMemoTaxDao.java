/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Tax;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author cas
 */
public class InMemoTaxDao implements TaxDao{

     List<Tax> stateTax = new ArrayList();
            
        Tax T1 = new Tax();
        Tax T2 = new Tax();
        Tax T3 = new Tax();
        
        
        
       public InMemoTaxDao () {

           
          T1.setState("OH");
          T1.setTaxRate(new BigDecimal ("6.25"));
          stateTax.add(T1);
          
          T2.setState("IN");
          T2.setTaxRate(new BigDecimal ("6.00"));
          stateTax.add(T2);
          
          T3.setState("MI");
          T3.setTaxRate(new BigDecimal ("5.75"));
          stateTax.add(T3);
       }
         
    
    @Override
    public Tax getTaxByState(String state) {
         List<Tax> allTaxData = stateTax;
        Tax custTax = null;

        for (int i = 0; i < allTaxData.size(); i++) {
            Tax toCheck = allTaxData.get(i);

            if (toCheck.getState().equalsIgnoreCase(state)) {
                custTax = toCheck;
            }
        }
        return custTax;
    }
}