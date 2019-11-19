/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flooring.dao;

import com.mycompany.flooring.dto.Product;
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
public class FileProductDao implements ProductDao {
//    ProductType,CostPerSquareFoot,LaborCostPerSquareFoot
//Carpet,2.25,2.10
//Laminate,1.75,2.10
//Tile,3.50,4.15
//Wood,5.15,4.75
     String file;
  public FileProductDao (String file) {
    this.file = file;
}

    @Override
    public Product getProductByName(String name) throws FileProductDaoException{
        List<Product> allProductData = getAllProduct();
        Product custChoice = null;

        for (int i = 0; i < allProductData.size(); i++) {
            Product toCheck = allProductData.get(i);

            if (toCheck.getProductType().equalsIgnoreCase(name)) {
                custChoice = toCheck;
            }
        }
        return custChoice;
    }

    private List<Product> getAllProduct() throws FileProductDaoException {
        List<Product> allProduct = new ArrayList<>();;
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            Scanner scn = new Scanner(reader);
            scn.nextLine();
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");

                    Product toAdd = new Product();

                    toAdd.setProductType(cells[0]);
                    toAdd.setCostPsf(new BigDecimal(cells[1]));
                    toAdd.setLaborCostPsf(new BigDecimal(cells[2]));
                    allProduct.add(toAdd);

                }

            }

        } catch (FileNotFoundException ex) {
            throw new FileProductDaoException("Could not find Product enetered.", ex);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new FileProductDaoException("could not close reader for " + file, ex);
            }
        }
        return allProduct;
    }
    }

