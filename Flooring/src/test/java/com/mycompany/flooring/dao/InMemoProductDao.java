/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cas
 */
public class InMemoProductDao implements ProductDao{
    
    List<Product>prodList = new ArrayList();
    
    Product P1 = new Product();
    Product P2 = new Product();
    Product P3 = new Product();
    Product P4 = new Product();
    
    public  InMemoProductDao () {
 

    P1.setProductType("Laminate");
    P1.setCostPsf(new BigDecimal("1.75"));
    P1.setLaborCostPsf(new BigDecimal("2.10"));
    prodList.add(P1);
    P2.setProductType("carpet");
    P2.setCostPsf(new BigDecimal("2.25"));
    P2.setLaborCostPsf(new BigDecimal("2.10"));
    prodList.add(P2);
    P3.setProductType("Wood");
    P3.setCostPsf(new BigDecimal("5.15"));
    P3.setLaborCostPsf(new BigDecimal("4.75"));
    prodList.add(P3);
    P4.setProductType("Tile");
    P4.setCostPsf(new BigDecimal("3.50"));
    P4.setLaborCostPsf(new BigDecimal("4.15"));
    prodList.add(P4);
}
    @Override
    public Product getProductByName(String name) {
  
        List<Product> allProductData = prodList;
        Product custChoice = null;

        for (int i = 0; i < allProductData.size(); i++) {
            Product toCheck = allProductData.get(i);

            if (toCheck.getProductType().equalsIgnoreCase(name)) {
                custChoice = toCheck;
            }
        }
        return custChoice;
    }
    }
    

